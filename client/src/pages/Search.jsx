import styled, { createGlobalStyle } from 'styled-components';
import Pagination from '../components/pagenation/Pagenation';
import BlueButton from '../components/common/BlueButton';
import { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import profile from '../assets/imgs/profile.png';
import SortButtonGroup from '../components/SortButtonGroup';
import { buttonData } from '../constants/MyPageConstants';
import Loading from '../components/Loading';

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

const Blockitem = styled.div`
  color: rgb(35, 38, 41);
  display: block;
  flex-grow: 1;
  line-height: 1.389rem;
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

  span {
    color: rgb(12, 13, 14);
    line-height: 1.063rem;
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
  span {
    color: gray;
    line-height: 1.063rem;
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
  span {
    color: gray;
    line-height: 1.063rem;
  }
`;
const QuelistConatiner = styled.div`
  display: block;
  flex-grow: 1;
  line-height: 1.063rem;
  max-width: 100%;
`;
const QueTitle = styled.h3`
  color: rgb(35, 38, 41);
  display: block;
  line-height: 1.389rem;
  margin-block-end: 0.312rem;
  margin-block-start: -0.122rem;
  margin-bottom: 0.312rem;
  margin-top: -0.122rem;
  padding-right: 1.5rem;

  a {
    color: rgb(0, 116, 204);
    cursor: pointer;
    line-height: 1.389rem;
    text-decoration-color: rgb(0, 116, 204);
    text-decoration-style: solid;
  }
`;
const Tag = styled.div`
  align-items: center;
  color: rgb(35, 38, 41);
  column-gap: 0.375rem;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  line-height: 1.063rem;
  row-gap: 0.5rem;
  word-wrap: break-word;
`;

const Block2 = styled.div`
  align-items: center;
  background-color: rgba(0, 0, 0, 0);
  color: content-box rgb(35, 38, 41);
  column-gap: 0.25rem;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-end;
  line-height: 0.813rem;
  margin-left: 4.375rem;
  row-gap: 0.25rem;
`;
const UserImg = styled(Link)`
  div {
    color: rgb(0, 116, 204);
    cursor: pointer;
    display: block;
    height: 1rem;
    line-height: 0.813rem;
    position: relative;
    width: 1rem;
    img {
      aspect-ratio: auto 16 / 16;
      color: rgb(0, 116, 204);
      cursor: pointer;
      display: block;
      height: 1rem;
      line-height: 0.813rem;
      width: 1rem;
    }
  }
`;
const UserCommit = styled.ul`
  align-items: center;
  color: rgb(35, 38, 41);
  column-gap: 0.375rem;
  display: flex;
  line-height: 0.813rem;
  row-gap: 0.375rem;
  li {
    color: rgb(82, 89, 96);
    display: list-item;
    span {
      color: rgb(82, 89, 96);
      direction: ltr;
    }
  }
`;
const UserIdList = styled.div`
  align-items: center;
  color: rgb(35, 38, 41);
  column-gap: 0.25rem;
  display: flex;
  flex-direction: row;
  line-height: 0.813rem;
`;
const UserId = styled.div`
  align-items: center;
  color: rgb(35, 38, 41);
  display: flex;
  flex-wrap: wrap;
  line-height: 0.75rem;
  margin: -0.125rem;

  a {
    color: rgb(0, 116, 204);
    cursor: pointer;
    line-height: 0.75rem;
    margin: 0.125rem;
  }
`;
const UserTime = styled.time`
  color: rgb(106, 115, 124);
  grid-column-end: 3;
  grid-column-start: 1;
  grid-row-end: 2;
  grid-row-start: 1;
  line-height: 0.75rem;
  a,
  span {
    color: rgb(59, 64, 68);
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
  const [sortOptions, setSortOptions] = useState('newest');
  const [isFetching, setIsFetching] = useState(true);

  useEffect(() => {
    // 2초 후에 isFetching 값을 false로 업데이트
    const timer = setTimeout(() => {
      setIsFetching(false);
    }, 2000);

    return () => clearTimeout(timer); // 컴포넌트 언마운트 시 타이머 제거
  }, []); // 컴포넌트가 처음 렌더링될 때만 실행

  const handleSortOption = (type) => {
    setSortOptions(type);
  };

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
              <BlueButton link="/askquestions"> Ask Question </BlueButton>
            </AQuecontainer>
          </TopQuestions>
          <Category>
            <Blockitem>{searchData.length} questions</Blockitem>
            <SortButtonGroup
              buttonData={buttonData}
              activeOption={sortOptions}
              onClick={handleSortOption}
            />
          </Category>

          <Qlistwrapper>
            <Questionminilist>
              <Questioncontainer>
                {isFetching ? (
                  <Loading /> // 로딩 중에는 로딩 컴포넌트를 렌더링합니다.
                ) : (
                  Array.isArray(searchData) &&
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
                                <UserCommit></UserCommit>
                              </UserIdList>
                              <UserTime>
                                asked <span>{createdMinutes} mins ago</span>
                              </UserTime>
                            </Block2>
                          </Tag>
                        </QuelistConatiner>
                      </Questionlist>
                    );
                  })
                )}
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
