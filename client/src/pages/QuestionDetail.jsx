import styled from 'styled-components';
import emptyImage from '../assets/imgs/empty.png';
import UserCard from '../components/UserCard';
import BlueButton from '../components/common/BlueButton';

import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { ReactComponent as VoteUp } from '../assets/icons/voteup.svg';
import { ReactComponent as VoteDown } from '../assets/icons/votedown.svg';
import { formatAgo } from '../utils/date';
import MarkDownEditor from '../components/MarkDownEditor';
import { useDispatch, useSelector } from 'react-redux';

const QuestionDetailWrapper = styled.div`
  width: 100%;
  height: 100%;
`;

const QuestionHeader = styled.div`
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
`;

const QuestionHeaderTitle = styled.h1`
  font-size: 1.688rem;
  margin-bottom: 0.5rem;
`;

const QuestionsInfoContainer = styled.div`
  display: flex;
  flex-flow: row nowrap;
  padding-bottom: 0.5rem;
  margin-bottom: 1rem;
  border-bottom: 1px solid var(--black-075);
`;

const QuestionsInfoItem = styled.div`
  white-space: nowrap;
  margin-bottom: 0.5rem;
  margin-right: 1rem;
  font-size: 0.813rem;

  & > span {
    color: var(--fc-light);
    margin-right: 0.125rem;
  }
`;

const QuestionContentContainer = styled.div`
  display: grid;
  grid-template-columns: max-content 1fr;
`;

const QuestionVoteContainer = styled.div`
  padding-right: 1rem;
  vertical-align: top;
  grid-column: 1;
`;

const QuestionVoteItem = styled.div`
  margin: 0.125rem;
  cursor: pointer;
  color: var(--black-700);
`;

const QuestionVoteButtonItem = styled(QuestionVoteItem)`
  border: 1px solid var(--black-100);
  border-radius: 100%;
  padding: 0.625rem;
`;

const QuestionVoteCountItem = styled(QuestionVoteItem)`
  font-weight: 600;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 1.5;
  padding: 0.625rem;
`;

const QuestionPostContainer = styled.div`
  vertical-align: top;
  grid-column: 2;
  width: auto;
  min-width: 0;
`;

const QuestionPostText = styled.div`
  width: 100%;
  line-height: 1.5;
  overflow-wrap: break-word;
`;

const QuestionBottom = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  padding-top: 0.25rem;
  margin-bottom: 1rem;
  margin-top: 1rem;
`;

const QuestionBottomLeft = styled.div`
  margin-right: 1rem;
  flex: 1 auto;
  display: flex;

  & > div {
    margin: 0.5rem;
    font-size: 0.813rem;
  }
`;

const QuestionBottomRight = styled.div`
  margin: 0.25rem;
  text-align: left;
  vertical-align: top;
  width: 200px;
  border-radius: 3px;
  background-color: var(--powder-200);
`;

const AnswersContainer = styled.div`
  padding-top: 1rem;
  padding-bottom: 1rem;
`;

const AnswerItem = styled(QuestionContentContainer)`
  padding-top: 1rem;
  padding-bottom: 1rem;
  border-bottom: 1px solid var(--black-075);
`;

const AnswerHeader = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 0.5rem;
`;

const AnswerHeaderTitle = styled.h2`
  font-size: 1.188rem;
  font-weight: 400;
  flex: 1 auto;
`;

const AnswerHeaderSortContainer = styled.div`
  display: flex;
  gap: 0.25rem;
  align-items: center;
`;

const AnswerHeaderSortLeft = styled.div`
  display: flex;
  flex-direction: column;
  font-size: 0.75rem;
  text-align: right;

  a {
    color: var(--blue);
    font-size: 0.688rem;
  }
`;

const AnswerHeaderSortRight = styled.div`
  font-size: 0.813rem;

  & > select {
    background-color: var(--white);
    border: 1px solid var(--bc-darker);
    border-radius: 0.188rem;
    box-sizing: border-box;
    font-size: 0.8rem;
    padding: 0.6em 0.7em;
    height: 100%;
    line-height: calc((13 + 2) / 13);
    padding-right: 2rem;
    position: relative;
    width: 100%;
    outline: 0;
  }
`;

const AnswerEmptyImageWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const AnswerWriteHeader = styled.div`
  padding-top: 1.25rem;
  margin-bottom: 1em;
`;

export default function QuestionDetail() {
  const [question, setQuestion] = useState({});
  const [isFetching, setIsFetching] = useState(true);
  const [answerValue, setAnswerValue] = useState(`type your answer...`);
  const navigation = useNavigate();

  const storedAccessToken = localStorage.getItem('accessToken');

  const { isLoggedIn } = useSelector((state) => state.login);

  const { id } = useParams();

  useEffect(() => {
    console.log(id);
    setIsFetching(true);
    fetch(
      `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/questions/${id}`,
    )
      .then((res) => res.json())
      .then((data) => {
        setQuestion(data.data);
        setIsFetching(false);
      });
  }, []);

  const onPostEditor = (content) => {
    fetch(
      `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/questions/${id}/answers`,
      {
        method: 'post',
        headers: {
          accept: '*/*',
          Authorization: `Bearer ${storedAccessToken}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          content: content,
        }),
      },
    )
      .then((res) => {
        if (res.status === 201) {
          navigation(0);
        }
      })
      .catch((e) => console.log(e));
  };

  const onDeleteQuestion = () => {
    fetch(
      `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/questions/${id}`,
      {
        method: 'delete',
        headers: {
          accept: '*/*',
          Authorization: `Bearer ${storedAccessToken}`,
        },
      },
    )
      .then((res) => {
        if (res.status === 204) {
          alert('게시글이 삭제되었습니당!!');
          navigation('/questions');
        }
      })
      .catch((e) => console.log(e));
  };

  const onDeleteAnswer = (answerId) => {
    fetch(
      `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/questions/${id}/answers/${answerId}`,
      {
        method: 'delete',
        headers: {
          accept: '*/*',
          Authorization: `Bearer ${storedAccessToken}`,
        },
      },
    )
      .then((res) => {
        if (res.status === 204) {
          alert('게시글이 삭제되었습니당!!');
          navigation(0);
        }
      })
      .catch((e) => console.log(e));
  };

  return (
    <>
      {!isFetching && (
        <QuestionDetailWrapper>
          <QuestionHeader>
            <QuestionHeaderTitle>{question.title}</QuestionHeaderTitle>
            <BlueButton>Ask Question</BlueButton>
          </QuestionHeader>
          <QuestionsInfoContainer>
            <QuestionsInfoItem>
              <span>Asked</span>
              <span>{formatAgo(question.createdAt)}</span>
            </QuestionsInfoItem>
            <QuestionsInfoItem>
              <span>Modified</span>
              <span>{formatAgo(question.modifiedAt)}</span>
            </QuestionsInfoItem>
            <QuestionsInfoItem>
              <span>Viewed</span>
              <span>{question.view} times</span>
            </QuestionsInfoItem>
          </QuestionsInfoContainer>
          <QuestionContentContainer>
            <QuestionVoteContainer>
              <QuestionVoteButtonItem>
                <VoteUp />
              </QuestionVoteButtonItem>
              <QuestionVoteCountItem>{question.vote}</QuestionVoteCountItem>
              <QuestionVoteButtonItem>
                <VoteDown />
              </QuestionVoteButtonItem>
            </QuestionVoteContainer>
            <QuestionPostContainer>
              <QuestionPostText>{question.content}</QuestionPostText>
              <QuestionBottom>
                <QuestionBottomLeft>
                  <div>
                    <Link>Share</Link>
                  </div>
                  <div>
                    <Link to={`/questions/${id}/edit`}>Edit</Link>
                  </div>
                  <div>
                    <Link onClick={onDeleteQuestion}>Delete</Link>
                  </div>
                </QuestionBottomLeft>
                <QuestionBottomRight>
                  <UserCard
                    type="asked"
                    created={question.createdAt}
                    name={question.user.name}
                  />
                </QuestionBottomRight>
              </QuestionBottom>
            </QuestionPostContainer>
          </QuestionContentContainer>
          <AnswersContainer>
            <AnswerHeader>
              <AnswerHeaderTitle>{question.answerCnt} Answer</AnswerHeaderTitle>
              <AnswerHeaderSortContainer>
                <AnswerHeaderSortLeft>
                  <label htmlFor="answer-sort-menus"> Sorted by:</label>
                </AnswerHeaderSortLeft>
                <AnswerHeaderSortRight>
                  <select id="answer-sort-menus">
                    <option> Highest score (default) </option>
                    <option> Trending (recent votes count more) </option>
                    <option> Date modified (newest first) </option>
                    <option> Date created (oldest first) </option>
                  </select>
                </AnswerHeaderSortRight>
              </AnswerHeaderSortContainer>
            </AnswerHeader>
            {question.answerCnt > 0 ? (
              <>
                {question.answers.map((answer) => (
                  <AnswerItem key={answer.answerId}>
                    <QuestionVoteContainer>
                      <QuestionVoteButtonItem>
                        <VoteUp />
                      </QuestionVoteButtonItem>
                      <QuestionVoteCountItem>1</QuestionVoteCountItem>
                      <QuestionVoteButtonItem>
                        <VoteDown />
                      </QuestionVoteButtonItem>
                    </QuestionVoteContainer>
                    <QuestionPostContainer>
                      <QuestionPostText>{answer.content}</QuestionPostText>
                      <QuestionBottom>
                        <QuestionBottomLeft>
                          <div>
                            <Link>Share</Link>
                          </div>
                          <div>
                            <Link
                              to={`/questions/${id}/answers/${answer.answerId}/edit`}
                            >
                              Edit
                            </Link>
                          </div>
                          <div>
                            <Link
                              onClick={() => onDeleteAnswer(answer.answerId)}
                            >
                              Delete
                            </Link>
                          </div>
                        </QuestionBottomLeft>
                        <QuestionBottomRight>
                          <UserCard
                            type="answerd"
                            created={answer.createdAt}
                            name={answer.name}
                          />
                        </QuestionBottomRight>
                      </QuestionBottom>
                    </QuestionPostContainer>
                  </AnswerItem>
                ))}
              </>
            ) : (
              <AnswerEmptyImageWrapper>
                <img src={emptyImage} alt="empty answer" />
              </AnswerEmptyImageWrapper>
            )}
          </AnswersContainer>
          {isLoggedIn && (
            <>
              <AnswerWriteHeader>
                <AnswerHeaderTitle>Your Answer</AnswerHeaderTitle>
              </AnswerWriteHeader>
              <MarkDownEditor value={answerValue} onChange={setAnswerValue} />
              <BlueButton onClick={() => onPostEditor(answerValue)}>
                Post Your Answer
              </BlueButton>
            </>
          )}
        </QuestionDetailWrapper>
      )}
    </>
  );
}
