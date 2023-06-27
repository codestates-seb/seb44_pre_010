import styled, { createGlobalStyle } from 'styled-components';
import Pagination from '../components/pagenation/Pagenation';
import BlueButton from '../components/common/BlueButton';
import { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import profile from '../assets/imgs/profile.png';

const GlobalStyle = createGlobalStyle`
  *, *::before, *::after {
    box-sizing: border-box;
  }
`;

const Maincontainer = styled.div`
  max-width: 68.75rem;
  width: calc(100% - 10.25rem);
  background-color: white;
  border-left-width: 0.063rem;
  padding: 1.5rem;
  &::before {
    color: rgb(35, 38, 41);
    display: table;
    line-height: 1.063rem;
    vertical-align: baseline;
    font-family: 'Segoe UI Adjusted', 'Segoe UI', 'Liberation Sans', sans-serif;
    font-size: 0.813rem;
  }
`;
const Mainbar = styled.div`
  color: rgb(35, 38, 43);
  display: block;
  font-weight: 25rem;
  line-height: 1.063rem;
  width: 55rem;
`;
const TopQuestions = styled.div`
  color: rgb(35, 38, 41);
  display: flex;
  line-height: 1.063rem;
  font-family: 'Segoe UI Adjusted', 'Segoe UI', 'Liberation Sans', sans-serif;
  font-size: 0.813rem;
  font-weight: 25rem;
  justify-content: center;
  align-items: center;
`;
const H1 = styled.h1`
  color: rgb(35, 38, 42);
  display: block;
  flex-grow: 1;
  font-family: 'Segoe UI Adjusted', 'Segoe UI', 'Liberation Sans', sans-serif;
  font-size: 1.688rem;
  font-weight: 25rem;
  line-height: 2.194rem;
  margin-block-end: 1.688rem;
  margin-bottom: 1.688rem;
`;
const AQuecontainer = styled.div`
  color: rgb(35, 38, 41);
  display: block;
  line-height: 2.194rem;
  margin-left: 0.75rem;
`;

const Category = styled.div`
  display: flex;
  line-height: 1.062rem;
  margin-top: 1rem;
  align-items: center;
`;
const Categorylist = styled.div`
  font-size: 100%;
  display: block;
  text-align: left;
`;
const Categorylink = styled.div`
  line-height: 0.938rem;
`;
const Categoryitem = styled(Link)`
  margin-right: -0.063rem;
  background-color: #f5f4f4;
  border-radius: 0.313rem;
  margin-bottom: -0.063rem;
  white-space: nowrap;
  line-height: 0.938rem;
  padding: 0.65rem;
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
  margin: -0.125rem;

  a {
    box-sizing: border-box;
    color: rgb(0, 116, 204);
    cursor: pointer;
    line-height: 0.75rem;
    margin: 0.125rem;
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
    color: rgb(59, 64, 68);
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

function Search() {
  const location = useLocation();
  const { searchResults } = location.state || { searchResults: [] };
  const [limit, setLimit] = useState(15);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;
  const searchData = searchResults.data;

  const formatTime = (createdAt) => {
    const dateObj = new Date(createdAt);
    const hours = dateObj.getHours();
    return hours;
  };

  return (
    <>
      <GlobalStyle />
      <Maincontainer>
        <Mainbar>
          <TopQuestions>
            <H1>Search Results</H1>
            <AQuecontainer>
              <BlueButton link="/askquestions">Ask Question</BlueButton>
            </AQuecontainer>
          </TopQuestions>
          <Category>
            <Blockitem>
              {searchResults && searchData.length} questions
            </Blockitem>
            <Categorylist>
              <Categorylink>
                <Categoryitem1 to="https://stackoverflow.com/?tab=interesting">
                  Newset
                </Categoryitem1>
                <Categoryitem to="https://stackoverflow.com/?tab=month">
                  Active
                </Categoryitem>
                <Categoryitem to="https://stackoverflow.com/?tab=bounties">
                  <span>226</span> Bountied
                </Categoryitem>
                <Categoryitem to="https://stackoverflow.com/?tab=hot">
                  More
                </Categoryitem>
                <Categoryitem to="https://stackoverflow.com/?tab=week">
                  Filter &nbsp;
                  <FontAwesomeIcon icon={faBars} />
                </Categoryitem>
              </Categorylink>
            </Categorylist>
          </Category>

          <Qlistwrapper>
            <Questionminilist>
              <Questioncontainer>
                {Array.isArray(searchData) &&
                  searchData.slice(offset, offset + limit)?.map((data) => {
                    const createdMinutes = formatTime(data.createdAt);
                    return (
                      <Questionlist key={data.questionId}>
                        <Qinformation>
                          <Votes>
                            <span> {data.vote} </span> votes
                          </Votes>
                          <Answers>
                            <span> {data.answerCnt} </span> answerd
                          </Answers>
                          <Views>
                            <span> {data.view} </span> views
                          </Views>
                        </Qinformation>
                        <QuelistConatiner>
                          <QueTitle>
                            <Link to={`/questions/${data.questionId}`}>
                              {data.title}
                            </Link>
                          </QueTitle>
                          <Tag>
                            <Block2></Block2>
                            <Block2>
                              <UserImg>
                                <div>
                                  <img src={profile} alt="유저 이미지 사진" />
                                </div>
                              </UserImg>
                              <UserIdList>
                                <UserId>
                                  <span>{data.user.name}</span>
                                </UserId>
                                <UserCommit>
                                  <li>
                                    <span> {2} </span>
                                  </li>
                                </UserCommit>
                              </UserIdList>
                              <UserTime>
                                asked <span>{createdMinutes} mins ago</span>
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

          <Pagination
            total={searchResults && searchResults.length}
            limit={limit}
            page={page}
            setPage={setPage}
            setLimit={setLimit}
          />
        </Mainbar>
      </Maincontainer>
    </>
  );
}

export default Search;
