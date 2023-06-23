#!/bin/bash


BUILD_JAR=$(ls /home/jenkins/workspace/pre-project/server/build/libs/sof-0.0.1-SNAPSHOT.jar)
CLIENT_BUILD_FOLDER=/home/jenkins/workspace/pre-project/client/build
JAR_NAME=$(basename $BUILD_JAR)

echo "> 현재 시간: $(date)" >> /home/ec2-user/log/deploy.log

echo "> build 파일명: $JAR_NAME" >> /home/ec2-user/log/deploy.log

echo "> Server build 파일 복사" >> /home/ec2-user/log/deploy.log
DEPLOY_PATH=/home/ec2-user/build/server/
cp $BUILD_JAR $DEPLOY_PATH

echo "> Client build 파일 복사" >> /home/ec2-user/log/deploy.log
CLIENT_DEPLOY_PATH=/home/ec2-user/build/client/
cp -r $CLIENT_BUILD_FOLDER $CLIENT_DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/ec2-user/log/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >>  /home/ec2-user/log/deploy.log
else
  echo "> kill -9 $CURRENT_PID" >> /home/ec2-user/log/deploy.log
  sudo kill -9 $CURRENT_PID
  sleep 5
fi


DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "> DEPLOY_JAR 배포"    >> /home/ec2-user/log/deploy.log
su - ec2-user -c "java -jar $DEPLOY_JAR" >> /home/ec2-user/log/deploy.log 2>/home/ec2-user/log/deploy_err.log &

sleep 60

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 다시한번 실행합니다." >>  /home/ec2-user/log/deploy.log
  echo "> DEPLOY_JAR 배포"    >> /home/ec2-user/log/deploy.log
  su - ec2-user -c "java -jar $DEPLOY_JAR" >> /home/ec2-user/log/deploy.log 2>/home/ec2-user/log/deploy_err.log &
else
  echo "> 실행이 완료됐습니다." >> /home/ec2-user/log/deploy.log
fi
