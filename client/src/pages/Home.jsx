import styled from 'styled-components';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
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
  color: rgb(35, 38, 41);
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
const Askquestion = styled.a`
  background-color: rgb(10, 149, 255);
  color: white;
  border-bottom-left-radius: 0.188rem;
  border-bottom-right-radius: 0.188rem;
  border-bottom-style: solid;
  border-bottom-width: 0.063rem;
  cursor: pointer;
  display: inline-block;
  font-weight: 25rem;
  line-height: 0.938rem;
  padding-bottom: 0.65rem;
  padding-left: 0.65rem;
  padding-right: 0.65rem;
  padding-top: 0.65rem;
  position: relative;
  text-align: center;
  box-shadow: rgba(255, 255, 255, 0.4) 0rem 0.063rem 0rem 0rem inset;
  box-sizing: border-box;
  text-decoration-color: rgb(255, 255, 255);
  text-decoration-line: none;
  text-decoration-style: solid;
  border-top-left-radius: 0.188rem;
  border-top-right-radius: 0.188rem;
  &:hover {
    background-color: rgb(7, 112, 192);
  }
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
const Categoryitem = styled.a`
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
const TagItems = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  column-gap: 0.25rem;
  display: block;
  flex-wrap: wrap;
  float: left;
  border-bottom-color: rgb(35, 38, 41);
  border-bottom-style: none;
  border-left-color: rgb(35, 38, 41);
  border-left-style: none;
  border-left-width: 0rem;
  border-right-color: rgb(35, 38, 41);
  border-right-style: none;
  border-right-width: rem;
  border-top-color: rgb(35, 38, 41);
  border-top-style: none;
  border-top-width: 0rem;
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  row-gap: 0.25rem;
  text-align: left;
  line-height: 1.125rem;
`;
const TagItemsList = styled.ul`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: inline;
  line-height: 1.125rem;
  text-align: left;
  vertical-align: baseline;
  margin-block-end: 0.813rem;
  margin-bottom: 0.813rem;
  li {
    box-sizing: border-box;
    color: rgb(35, 38, 41);
    display: inline;
    line-height: 1.125rem;
    margin-right: 0.25rem;
    text-align: left;
    vertical-align: baseline;
    a {
      color: rgb(57, 115, 157);
      cursor: pointer;
      display: inline-block;
      background-color: rgb(225, 236, 244);
      border-bottom-left-radius: 0.188rem;
      border-bottom-right-radius: 0.188rem;
      border-bottom-style: solid;
      border-left-color: rgba(0, 0, 0, 0);
      border-left-style: solid;
      font-size: 0.75rem;
      line-height: 0.75rem;
      list-style-position: outside;
      list-style-type: none;
      margin-bottom: 0.125rem;
      margin-right: 0.125rem;
      opacity: 1;
      padding-bottom: 0.3rem;
      padding-left: 0.375rem;
      padding-right: 0.375rem;
      padding-top: 0.3rem;
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
const UserImg = styled.a`
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

  useEffect(() => {
    const getAllQuestions = async () => {
      const response = await fetch('/data/questions.json');
      const jsonData = await response.json();

      return jsonData;
    };

    getAllQuestions().then((data) => {
      setQuestions(data.data);
    });
  }, []);
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
                {/* ⬇모든 Question Items를 포함하는 컴포넌트 최상위 */}
                {questions.map((question) => {
                  return (
                    <Questionlist key={question.questionId}>
                      <Qinformation>
                        <Votes>
                          <span> {question.score} </span> votes
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
                          <Link to={`${question.link}`}>{question.title}</Link>
                        </QueTitle>

                        <Tag>
                          {/* ⬇ 여기가  Tags 컴포넌트 최상위  */}
                          <TagItems>
                            <TagItemsList>
                              {question.tags.map((tag) => (
                                <li key={question.questionId}>
                                  <a
                                    href={`https://stackoverflow.com/questions/tagged/${tag}`}
                                  >
                                    {tag}
                                  </a>
                                </li>
                              ))}
                            </TagItemsList>
                          </TagItems>

                          <Block2>
                            <UserImg>
                              <div>
                                <a
                                  href={`https://stackoverflow.com/users/${question.user.userId}/${question.name}`}
                                >
                                  <img
                                    src={question.user.profile_image}
                                    alt="유저 이미지 사진"
                                  ></img>
                                </a>
                              </div>
                            </UserImg>
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
                              <a
                                href={`https://stackoverflow.com/questions/${question.questionId}/${question.title}`}
                              >
                                asked <span>2 mins ago</span>
                              </a>
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
