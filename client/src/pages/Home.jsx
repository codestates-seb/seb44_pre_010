import styled from 'styled-components';
import BlueButton from '../components/common/BlueButton';

import { useEffect, useState } from 'react';
import { Link, useOutletContext } from 'react-router-dom';
import UserAvatar from '../components/UserAvatar';

const Maincontainer = styled.div`
  max-width: 68.75rem;
  width: calc(100% - 10.25rem);
  background-color: white;
  border-radius: 0;
  border-left-width: 0.063rem;
  border-right-width: 0;
  padding: 1.5rem;
  box-sizing: border-box;
  &::before {
    box-sizing: border-box;
    color: rgb(35, 38, 41);
    display: table;
    line-height: 1.063rem;
    vertical-align: baseline;
    font-family: 'Segoe UI Adjusted', 'Segoe UI', 'Liberation Sans', sans-serif;
    font-size: 0.813rem;
    font-stretch: 100%;
    text-align: left;
  }
`;
const Mainbar = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 43);
  display: block;
  font-weight: 25rem;
  line-height: 1.063rem;
  width: 55rem;
`;
const TopQuestions = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: flex;
  line-height: 1.063rem;
  vertical-align: baseline;
  font-family: 'Segoe UI Adjusted', 'Segoe UI', 'Liberation Sans', sans-serif;
  font-size: 0.813rem;
  font-stretch: 100%;
  text-align: left;
  font-weight: 25rem;
  justify-content: center;
  align-items: center;
`;
const H1 = styled.h1`
  box-sizing: border-box;
  color: rgb(35, 38, 42);
  display: block;
  flex-basis: auto;
  flex-grow: 1;
  flex-shrink: 1;
  font-family: 'Segoe UI Adjusted', 'Segoe UI', 'Liberation Sans', sans-serif;
  font-size: 1.688rem;
  font-weight: 25rem;
  line-height: 2.194rem;
  margin-block-end: 1.688rem;
  margin-bottom: 1.688rem;
  text-align: left;
`;
const AQuecontainer = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: block;
  line-height: 2.194rem;
  margin-left: 0.75rem;
  text-align: left;
`;
const Category = styled.div`
  display: flex;
  line-height: 1.063rem;
  margin-top: 1rem;
  vertical-align: baseline;
  align-items: center;
  box-sizing: inherit;
  text-align: left;
`;
const Categorylist = styled.div`
  box-sizing: inherit;
  margin: 0;
  padding: 0;
  border: 0;
  font: inherit;
  font-size: 100%;
  display: block;
  text-align: left;
`;
const Categorylink = styled.div`
  vertical-align: baseline;
  text-align: left;
  line-height: 0.938rem;
`;
const Categoryitem = styled(Link)`
  margin-right: -0.063rem;
  z-index: 1.563rem;
  background-color: #f5f4f4;
  border-radius: 0.313rem 0.313rem 0.313rem 0.313rem;

  margin-bottom: -0.063rem;
  white-space: nowrap;
  line-height: 0.938rem;
  padding-bottom: 0.65rem;
  padding-left: 0.65rem;
  padding-right: 0.65rem;
  padding-top: 0.65rem;
  position: relative;
  text-align: center;
  span {
    background-color: rgb(10, 149, 255);
    color: white;
    border-radius: 0.313rem 0.313rem 0.313rem 0.313rem;
    margin: 0.125rem;
  }
  &:hover {
    background-color: gray;
  }
`;
const Categoryitem1 = styled(Categoryitem)`
  background-color: hsl(210, 10.416666666666693%, 81.17647058823529%);
`;
const Blockitem = styled.div`
  color: rgb(35, 38, 41);
  display: block;
  flex-basis: auto;
  flex-grow: 1;
  flex-shrink: 1;
  line-height: 1.389rem;
  text-align: left;
  box-sizing: border-box;
`;
const Qlistwrapper = styled.div`
  box-sizing: border-box;
  clear: both;
  color: rgb(35, 38, 41);
  display: block;
  line-height: 1.063rem;
  margin-left: -1rem;
  margin-right: -1rem;
  text-align: left;
  width: 56.25rem;
  margin-top: 1.875rem;
`;
const Questionminilist = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: block;
  line-height: 1.063rem;
  margin-bottom: 1.875rem;
  text-align: left;
`;
const Questioncontainer = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: block;
  line-height: 1.063rem;
  text-align: left;
`;
const Questionlist = styled.div`
  // 이게 Question list 1칸
  background-color: rgba(0, 0, 0, 0);
  border-bottom-color: rgb(227, 230, 232);
  border-bottom-style: solid;
  border-bottom-width: 0.063rem;
  border-left-color: rgb(227, 230, 232);
  border-left-style: solid;
  border-left-width: 0.063rem;
  border-right-color: rgb(227, 230, 232);
  border-right-style: solid;
  border-right-width: 0.063rem;
  border-top-color: rgb(227, 230, 232);
  border-top-style: solid;
  border-top-width: 0.063rem;
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: flex;
  flex-direction: column;
  line-height: 1.063rem;
  padding-bottom: 1rem;
  padding-left: 1rem;
  padding-right: 1rem;
  padding-top: 1rem;
  position: relative;
  text-align: left;
  vertical-align: baseline;
`;
const Qinformation = styled.div`
  align-items: center;
  border-top-style: none;
  border-top-width: 0rem;
  box-sizing: border-box;
  color: rgb(106, 115, 124);
  column-gap: 0.375rem;
  display: flex;
  flex-direction: row;
  flex-shrink: 0;
  flex-wrap: wrap;
  line-height: 1.063rem;
  margin-bottom: 0.25rem;
  margin-left: 0rem;
  margin-right: 1rem;
  row-gap: 0.375rem;
  text-align: left;
  vertical-align: baseline;
  width: 44.375rem;
`;
const Votes = styled.div`
  align-items: center;
  color: rgb(12, 13, 14);
  column-gap: 0.244rem;
  display: flex;
  justify-content: center;
  line-height: 1.063rem;
  opacity: 1;
  row-gap: 0.244rem;
  text-align: left;
  vertical-align: baseline;
  span {
    box-sizing: border-box;
    color: rgb(12, 13, 14);
    line-height: 1.063rem;
    text-align: left;
  }
`;
const Answers = styled.div`
  align-items: center;
  color: gray;
  column-gap: 0.244rem;
  display: flex;
  justify-content: center;
  line-height: 1.063rem;
  opacity: 1;
  row-gap: 0.244rem;
  text-align: left;
  vertical-align: baseline;
  span {
    box-sizing: border-box;
    color: gray;
    line-height: 1.063rem;
    text-align: left;
  }
`;
const Views = styled.div`
  align-items: center;
  color: gray;
  column-gap: 0.244rem;
  display: flex;
  justify-content: center;
  line-height: 1.063rem;
  opacity: 1;
  row-gap: 0.244rem;
  text-align: left;
  vertical-align: baseline;
  span {
    box-sizing: border-box;
    color: gray;
    line-height: 1.063rem;
    text-align: left;
  }
`;
const QuelistConatiner = styled.div`
  display: block;
  flex-grow: 1;
  line-height: 1.063rem;
  max-width: 100%;
  text-align: left;
`;
const QueTitle = styled.h3`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: block;
  line-height: 1.389rem;
  margin-block-end: 0.312rem;
  margin-block-start: -0.122rem;
  margin-bottom: 0.312rem;
  margin-top: -0.122rem;
  padding-right: 1.5rem;
  text-align: left;

  a {
    box-sizing: border-box;
    color: rgb(0, 116, 204);
    cursor: pointer;
    line-height: 1.389rem;
    text-decoration-color: rgb(0, 116, 204);
    text-decoration-style: solid;
    vertical-align: baseline;
  }
`;
const Tag = styled.div`
  align-items: center;
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  column-gap: 0.375rem;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  line-height: 1.063rem;
  row-gap: 0.5rem;
  text-align: left;
  vertical-align: baseline;
  word-wrap: break-word;
`;
const Block2 = styled.div`
  align-items: center;
  background-color: rgba(0, 0, 0, 0);
  box-sizing: border-box;
  color: content-box rgb(35, 38, 41);
  column-gap: 0.25rem;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-end;
  line-height: 0.813rem;
  margin-bottom: 0px;
  margin-left: 4.375rem;
  row-gap: 0.25rem;
  text-align: left;
`;
const UserImg = styled(Link)`
  div {
    box-sizing: border-box;
    color: rgb(0, 116, 204);
    cursor: pointer;
    display: block;
    height: 1rem;
    line-height: 0.813rem;
    position: relative;
    text-align: left;
    width: 1rem;
    img {
      aspect-ratio: auto 16 / 16;
      color: rgb(0, 116, 204);
      cursor: pointer;
      display: block;
      height: 1rem;
      line-height: 0.813rem;
      text-align: left;
      width: 1rem;
    }
  }
`;
const UserCommit = styled.ul`
  align-items: center;
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  column-gap: 0.375rem;
  display: flex;
  line-height: 0.813rem;
  row-gap: 0.375rem;
  text-align: left;
  vertical-align: baseline;
  li {
    box-sizing: border-box;
    color: rgb(82, 89, 96);
    display: list-item;
    span {
      box-sizing: border-box;
      color: rgb(82, 89, 96);
      direction: ltr;
      text-align: left;
    }
  }
`;
const UserIdList = styled.div`
  align-items: center;
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  column-gap: 0.25rem;
  display: flex;
  flex-direction: row;
  line-height: 0.813rem;
`;
const UserId = styled.div`
  align-items: center;
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: flex;
  flex-wrap: wrap;
  line-height: 0.75rem;
  margin-bottom: -0.125rem;
  margin-left: -0.125rem;
  margin-right: -0.125rem;
  margin-top: -0.125rem;
  a {
    box-sizing: border-box;
    color: rgb(0, 116, 204);
    cursor: pointer;
    line-height: 0.75rem;
    margin-bottom: 0.125rem;
    margin-left: 0.125rem;
    margin-right: 0.125rem;
    margin-top: 0.125rem;
  }
`;
const UserTime = styled.time`
  box-sizing: border-box;
  color: rgb(106, 115, 124);
  grid-column-end: 3;
  grid-column-start: 1;
  grid-row-end: 2;
  grid-row-start: 1;
  line-height: 0.75rem;
  a {
    box-sizing: border-box;
    color: rgb(59, 64, 69);
    cursor: pointer;
    line-height: 0.75rem;
  }
  span {
    box-sizing: border-box;
    color: rgb(59, 64, 69);
    cursor: pointer;
    line-height: 0.75rem;
  }
`;

function Home() {
  const [questions, setQuestions] = useState([]);
  const [isFetching, setIsFetching] = useState(true);
  const { onHandleSelect } = useOutletContext();

  useEffect(() => {
    onHandleSelect(0);
    const getAllQuestions = async () => {
      setIsFetching(true);
      // const response = await fetch('/data/questions.json');
      const response = await fetch(
        'http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/questions/top',
      );
      const jsonData = await response.json();

      setQuestions(jsonData.data);
      setIsFetching(false);
    };

    getAllQuestions();
  }, []);
  return (
    <>
      <Maincontainer className="Maincontainer">
        <Mainbar>
          <TopQuestions>
            <H1> Top Questions</H1>
            <AQuecontainer>
              <BlueButton link="/askquestions"> Ask Question </BlueButton>
            </AQuecontainer>
          </TopQuestions>
          <Category>
            <Blockitem></Blockitem>
            <Categorylist>
              <Categorylink>
                <Categoryitem1 to="https://stackoverflow.com/?tab=interesting">
                  Interesting
                </Categoryitem1>
                <Categoryitem to="https://stackoverflow.com/?tab=bounties">
                  <span>226</span> Bountied
                </Categoryitem>
                <Categoryitem to="https://stackoverflow.com/?tab=hot">
                  Hot
                </Categoryitem>
                <Categoryitem to="https://stackoverflow.com/?tab=week">
                  Week
                </Categoryitem>
                <Categoryitem to="https://stackoverflow.com/?tab=month">
                  Month
                </Categoryitem>
              </Categorylink>
            </Categorylist>
          </Category>

          <Qlistwrapper>
            <Questionminilist>
              <Questioncontainer>
                {/* ⬇모든 Question Items를 포함하는 컴포넌트 최상위 */}
                {Array.isArray(questions) &&
                  questions?.map((question) => {
                    return (
                      <Questionlist key={question.questionId}>
                        <Qinformation>
                          <Votes>
                            <span> {question.vote} </span> votes
                          </Votes>
                          <Answers>
                            <span> {question.answerCount} </span> answerd
                          </Answers>
                          <Views>
                            <span> {question.view} </span> views
                          </Views>
                        </Qinformation>
                        <QuelistConatiner>
                          <QueTitle>
                            <Link to={`/questions/${question.questionId}`}>
                              {question.title}
                            </Link>
                          </QueTitle>

                          <Tag>
                            <Block2></Block2>
                            <Block2>
                              <UserAvatar size={16} hasShadow={true} />
                              <UserIdList>
                                <UserId>
                                  <Link to={`${question.user.link}`}>
                                    <span>{question.user.name}</span>
                                  </Link>
                                </UserId>
                                <UserCommit>
                                  <li>
                                    <span> {question.ask} </span>
                                  </li>
                                </UserCommit>
                              </UserIdList>
                              <UserTime>
                                <Link
                                  to={`https://stackoverflow.com/questions/${question.questionId}/${question.title}`}
                                >
                                  asked <span>2 mins ago</span>
                                </Link>
                              </UserTime>
                            </Block2>
                          </Tag>
                        </QuelistConatiner>
                      </Questionlist>
                    );
                  })}
              </Questioncontainer>
            </Questionminilist>
          </Qlistwrapper>
        </Mainbar>
      </Maincontainer>
    </>
  );
}

export default Home;
