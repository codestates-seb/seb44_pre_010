import styled from 'styled-components';
const Maincontainer = styled.div`
  max-width: 1100px;
  width: calc(100% - 164px);
  background-color: white;
  border-radius: 0;
  border-left-width: 1px;
  border-right-width: 0;
  padding: var(--su24);
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
  color: rgb(35, 38, 41);
  display: block;
  font-weight: 25rem;
  line-height: 1.063rem;
  width: 37.25;
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
  color: rgb(35, 38, 41);
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
const Askquestion = styled.a`
  background-color: rgb(10, 149, 255);
  color: white;
  border-bottom-left-radius: 3px;
  border-bottom-right-radius: 3px;
  border-bottom-style: solid;
  border-bottom-width: 1px;
  cursor: pointer;
  display: inline-block;
  font-weight: 25rem;
  line-height: 0.938rem;
  padding-bottom: 10.4px;
  padding-left: 10.4px;
  padding-right: 10.4px;
  padding-top: 10.4px;
  position: relative;
  text-align: center;
  box-shadow: rgba(255, 255, 255, 0.4) 0px 1px 0px 0px inset;
  box-sizing: border-box;
  text-decoration-color: rgb(255, 255, 255);
  text-decoration-line: none;
  text-decoration-style: solid;
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;
  &:hover {
    background-color: rgb(7, 112, 192);
  }
`;
const Category = styled.div`
  display: flex;
  line-height: 17px;
  margin-top: 16px;
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
const Categoryitem = styled.a`
  margin-right: -1px;
  z-index: 25px;
  background-color: #f5f4f4;
  border-radius: 5px 5px 5px 5px;

  margin-bottom: -1px;
  white-space: nowrap;
  line-height: 15px;
  padding-bottom: 10.4px;
  padding-left: 10.4px;
  padding-right: 10.4px;
  padding-top: 10.4px;
  position: relative;
  text-align: center;
  span {
    background-color: rgb(10, 149, 255);
    color: white;
    border-radius: 5px 5px 5px 5px;
    margin: 2px;
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
  line-height: 22.2308px;
  text-align: left;
  box-sizing: border-box;
`;
const Qlistwrapper = styled.div`
  box-sizing: border-box;
  clear: both;
  color: rgb(35, 38, 41);
  display: block;
  line-height: 17px;
  margin-left: -16px;
  margin-right: -16px;
  text-align: left;
  width: 758px;
  margin-top: 30px;
`;
const Questionminilist = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: block;
  line-height: 17px;
  margin-bottom: 30px;
  text-align: left;
`;
const Questioncontainer = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: block;
  line-height: 17px;
  text-align: left;
`;
const Questionlist = styled.div`
  background-color: rgba(0, 0, 0, 0);
  border-bottom-color: rgb(227, 230, 232);
  border-bottom-style: solid;
  border-bottom-width: 1px;
  border-left-color: rgb(35, 38, 41);
  border-left-style: none;
  border-left-width: 0px;
  border-right-color: rgb(35, 38, 41);
  border-right-style: none;
  border-right-width: 0px;
  border-top-color: rgb(35, 38, 41);
  border-top-style: none;
  border-top-width: 0px;
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: flex;
  flex-direction: column;
  line-height: 17px;
  padding-bottom: 16px;
  padding-left: 16px;
  padding-right: 16px;
  padding-top: 16px;
  position: relative;
  text-align: left;
  vertical-align: baseline;
`;
const Qinformation = styled.div`
  align-items: center;
  border-bottom-color: rgb(106, 115, 124);
  border-left-color: rgb(106, 115, 124);
  border-left-style: none;
  border-left-width: 0px;
  border-right-color: rgb(106, 115, 124);
  border-right-style: none;
  border-right-width: 0px;
  border-top-color: rgb(106, 115, 124);
  border-top-style: none;
  border-top-width: 0px;
  box-sizing: border-box;
  color: rgb(106, 115, 124);
  column-gap: 6px;
  display: flex;
  flex-direction: row;
  flex-shrink: 0;
  flex-wrap: wrap;
  line-height: 17px;
  margin-bottom: 4px;
  margin-left: 0px;
  margin-right: 16px;
  row-gap: 6px;
  text-align: left;
  vertical-align: baseline;
  width: 710px;
`;
const Votes = styled.div`
  align-items: center;
  color: rgb(12, 13, 14);
  column-gap: 3.9px;
  display: flex;
  justify-content: center;
  line-height: 17px;
  opacity: 1;
  row-gap: 3.9px;
  text-align: left;
  vertical-align: baseline;
  span {
    box-sizing: border-box;
    color: rgb(12, 13, 14);
    line-height: 17px;
    text-align: left;
  }
`;
const Answers = styled.div`
  align-items: center;
  color: gray;
  column-gap: 3.9px;
  display: flex;
  justify-content: center;
  line-height: 17px;
  opacity: 1;
  row-gap: 3.9px;
  text-align: left;
  vertical-align: baseline;
  span {
    box-sizing: border-box;
    color: gray;
    line-height: 17px;
    text-align: left;
  }
`;
const Views = styled.div`
  align-items: center;
  color: gray;
  column-gap: 3.9px;
  display: flex;
  justify-content: center;
  line-height: 17px;
  opacity: 1;
  row-gap: 3.9px;
  text-align: left;
  vertical-align: baseline;
  span {
    box-sizing: border-box;
    color: gray;
    line-height: 17px;
    text-align: left;
  }
`;
const QuelistConatiner = styled.div`
  display: block;
  flex-grow: 1;
  line-height: 17px;
  max-width: 100%;
  text-align: left;
`;
const QueTitle = styled.h3`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: block;
  line-height: 22.2308px;
  margin-block-end: 4.9998px;
  margin-block-start: -1.95px;
  margin-bottom: 4.9998px;
  margin-top: -1.95px;
  padding-right: 24px;
  text-align: left;

  a {
    box-sizing: border-box;
    color: rgb(0, 116, 204);
    cursor: pointer;
    line-height: 22.2308px;
    text-decoration-color: rgb(0, 116, 204);
    text-decoration-style: solid;
    vertical-align: baseline;
  }
`;
const Tag = styled.div`
  align-items: center;
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  column-gap: 6px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  line-height: 17px;
  row-gap: 8px;
  text-align: left;
  vertical-align: baseline;
`;
const TagItems = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  column-gap: 4px;
  display: block;
  flex-wrap: wrap;
  float: left;
  border-bottom-color: rgb(35, 38, 41);
  border-bottom-style: none;
  border-left-color: rgb(35, 38, 41);
  border-left-style: none;
  border-left-width: 0px;
  border-right-color: rgb(35, 38, 41);
  border-right-style: none;
  border-right-width: 0px;
  border-top-color: rgb(35, 38, 41);
  border-top-style: none;
  border-top-width: 0px;
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  row-gap: 4px;
  text-align: left;
  line-height: 18px;
`;
const TagItemsList = styled.ul`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: inline;
  line-height: 18px;
  text-align: left;
  vertical-align: baseline;
  margin-block-end: 13px;
  margin-bottom: 13px;
  li {
    box-sizing: border-box;
    color: rgb(35, 38, 41);
    display: inline;
    line-height: 18px;
    margin-right: 4px;
    text-align: left;
    vertical-align: baseline;
    a {
      color: rgb(57, 115, 157);
      cursor: pointer;
      display: inline-block;
      background-color: rgb(225, 236, 244);
      border-bottom-left-radius: 3px;
      border-bottom-right-radius: 3px;
      border-bottom-style: solid;
      border-left-color: rgba(0, 0, 0, 0);
      border-left-style: solid;
      font-size: 12px;
      line-height: 12px;
      list-style-position: outside;
      list-style-type: none;
      margin-bottom: 2px;
      margin-right: 2px;
      opacity: 1;
      padding-bottom: 4.8px;
      padding-left: 6px;
      padding-right: 6px;
      padding-top: 4.8px;
      text-align: center;
      text-decoration-color: rgb(57, 115, 157);
      text-decoration-line: none;
      text-decoration-style: solid;
    }
  }
`;
const Block2 = styled.div`
  align-items: center;
  background-color: rgba(0, 0, 0, 0);
  box-sizing: border-box;
  color: content-box rgb(35, 38, 41);
  column-gap: 4px;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-end;
  line-height: 13px;
  margin-bottom: 0px;
  margin-left: 256.25px;
  row-gap: 4px;
  text-align: left;
`;
const UserImg = styled.a`
  div {
    box-sizing: border-box;
    color: rgb(0, 116, 204);
    cursor: pointer;
    display: block;
    height: 16px;
    line-height: 13px;
    position: relative;
    text-align: left;
    width: 16px;
    img {
      aspect-ratio: auto 16 / 16;
      color: rgb(0, 116, 204);
      cursor: pointer;
      display: block;
      height: 16px;
      line-height: 13px;
      text-align: left;
      width: 16px;
    }
  }
`;
const UserCommit = styled.ul`
  align-items: center;
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  column-gap: 6px;
  display: flex;
  line-height: 13px;
  row-gap: 6px;
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
  column-gap: 4px;
  display: flex;
  flex-direction: row;
  line-height: 13px;
`;
const UserId = styled.div`
  align-items: center;
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: flex;
  flex-wrap: wrap;
  line-height: 12px;
  margin-bottom: -2px;
  margin-left: -2px;
  margin-right: -2px;
  margin-top: -2px;
  a {
    box-sizing: border-box;
    color: rgb(0, 116, 204);
    cursor: pointer;
    line-height: 12px;
    margin-bottom: 2px;
    margin-left: 2px;
    margin-right: 2px;
    margin-top: 2px;
  }
`;
const UserTime = styled.time`
  box-sizing: border-box;
  color: rgb(106, 115, 124);
  grid-column-end: 3;
  grid-column-start: 1;
  grid-row-end: 2;
  grid-row-start: 1;
  line-height: 12px;
  a {
    box-sizing: border-box;
    color: rgb(59, 64, 69);
    cursor: pointer;
    line-height: 12px;
  }
  span {
    box-sizing: border-box;
    color: rgb(59, 64, 69);
    cursor: pointer;
    line-height: 12px;
  }
`;
function Home() {
  return (
    <>
      <Maincontainer className="Maincontainer">
        <Mainbar>
          <TopQuestions>
            <H1> Top Questions</H1>
            <AQuecontainer>
              <Askquestion> Ask Question </Askquestion>
            </AQuecontainer>
          </TopQuestions>
          <Category>
            <Blockitem></Blockitem>
            <Categorylist>
              <Categorylink>
                <Categoryitem1 href="https://stackoverflow.com/?tab=interesting">
                  Interesting
                </Categoryitem1>
                <Categoryitem href="https://stackoverflow.com/?tab=bounties">
                  <span>226</span> Bountied
                </Categoryitem>
                <Categoryitem href="https://stackoverflow.com/?tab=hot">
                  Hot
                </Categoryitem>
                <Categoryitem href="https://stackoverflow.com/?tab=week">
                  Week
                </Categoryitem>
                <Categoryitem href="https://stackoverflow.com/?tab=month">
                  Month
                </Categoryitem>
              </Categorylink>
            </Categorylist>
          </Category>

          <Qlistwrapper>
            <Questionminilist>
              <Questioncontainer>
                <Questionlist>
                  <Qinformation>
                    <Votes>
                      <span> 0 </span> votes
                    </Votes>
                    <Answers>
                      <span> 1 </span> answerd
                    </Answers>
                    <Views>
                      <span> 3 </span> views
                    </Views>
                  </Qinformation>

                  <QuelistConatiner>
                    <QueTitle>
                      <a href="https://stackoverflow.com/questions/76504926/how-to-deploy-project-in-cross-account-ec2-instances-using-codepipeline-in-aws">
                        How to Deploy Project in Cross Account EC2 Instances
                        Using CodePipeline in AWS
                      </a>
                    </QueTitle>
                    <Tag>
                      <TagItems>
                        <TagItemsList>
                          <li>
                            <a href="https://stackoverflow.com/questions/tagged/go">
                              go
                            </a>
                          </li>
                          <li>
                            <a href="https://stackoverflow.com/questions/tagged/go-echo">
                              go-echo
                            </a>
                          </li>
                        </TagItemsList>
                      </TagItems>
                      <Block2>
                        <UserImg>
                          <div>
                            <img
                              src="../icons/UserImg.svg"
                              alt="유저 이미지 사진"
                            ></img>
                          </div>
                        </UserImg>
                        <UserIdList>
                          <UserId>
                            <a href="https://stackoverflow.com/users/2879272/mohamed-ahmed-taher-mohamed">
                              Kid_Learning_C
                            </a>
                          </UserId>
                          <UserCommit>
                            <li>
                              <span> 246</span>
                            </li>
                          </UserCommit>
                        </UserIdList>
                        <UserTime>
                          <a href="https://stackoverflow.com/questions/76504915/using-ksqldb-query-consumer-has-a-transient-consumer-group-id-no-offset-is-stor">
                            asked <span>2 mins ago</span>
                          </a>
                        </UserTime>
                      </Block2>
                    </Tag>
                  </QuelistConatiner>
                </Questionlist>
              </Questioncontainer>
            </Questionminilist>
          </Qlistwrapper>
        </Mainbar>
      </Maincontainer>
    </>
  );
}

export default Home;
