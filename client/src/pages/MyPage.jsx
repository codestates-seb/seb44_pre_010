import styled from 'styled-components';
import pencilIcon from '../assets/icons/pencil.svg';
import deleteIcon from '../assets/icons/delete.svg';
import profile from '../assets/imgs/profile.png';
import { useState } from 'react';
import SortButtonGroup from '../components/SortButtonGroup.jsx';

const MyPageContainer = styled.div`
  width: 100%;
  padding: 0;
  box-sizing: inherit;
  margin: 0;
  font-size: 100%;
  vertical-align: baseline;
  text-align: left;
`;

const MyPageInfoContainer = styled.div`
  display: flex;
  position: relative;
  margin-bottom: 1rem;
  box-sizing: inherit;
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  vertical-align: baseline;
  text-align: left;
  align-items: center;
  flex-wrap: wrap;
  margin: 0.5rem;
`;

const UserAvatarContainer = styled.div`
  box-sizing: inherit;
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  vertical-align: baseline;
  box-shadow: var(--bs-sm);
  border-radius: 0.313rem;

  & > img {
    display: block;
    border-radius: 0.313rem;
    box-sizing: inherit;
    width: 128px;
    aspect-ratio: auto 128 / 128;
    height: 128px;
    overflow-clip-margin: content-box;
    overflow: clip;
  }
`;

const MyInfoText = styled.div`
  margin: 0.5rem;
  box-sizing: inherit;
  padding: 0;
  border: 0;
  font-size: 100%;
  vertical-align: baseline;
  text-align: left;

  & > h1 {
    margin: 0.72rem;
    display: flex;
    font-size: 2.125rem;
    color: var(--black-800);
  }

  & > span {
    font-size: 0.813rem;
    margin: 0.72rem;
    color: var(--black-500);
  }
`;

const MyPageButtonContainer = styled.div`
  margin-left: auto;
  display: flex;
  right: 0;
  top: 0;
  flex-wrap: wrap;
  margin: 0.188rem;
`;

const MyPageButton = styled.div`
  box-sizing: border-box;
  width: fit-content;
  height: 2.19rem;
  top: 0px;
  border: 0.0625rem solid var(--black-300);
  border-radius: 0.1875rem;
  margin-left: 0.225rem;
  color: var(--black-500);
  background-color: var(--white);
  align-items: center;
  text-align: center;
  display: flex;
  padding: 0.6rem;
  cursor: pointer;
  font-size: 0.75rem;

  & > img {
    display: inline-flex;
    margin: 0.225rem;
    width: 0.875rem;
    height: 0.875rem;
  }

  &:hover {
    background-color: var(--black-050);
  }
`;

const MyPageInfoPanelContainer = styled.div`
  display: flex;
  flex-direction: column;
  box-sizing: inherit;
  margin: 1.5rem;
`;

const MyPageInfoPanelTitle = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 0.5rem;

  & > h3 {
    font-size: 1.61538462rem;
  }
`;

const MyPageInfoPanel = styled.ul`
  border: 0.0625rem solid var(--black-100);
  height: 100%;
  display: flex;
  padding: 1.5rem;
  justify-content: center;
  align-items: center;
  text-align: center;
  box-sizing: inherit;
  border-radius: 0.1875rem;

  & > li {
    font-size: 0.813rem;
    color: var(--black-500);
  }
`;

const buttonData = [
  { id: 'newest', text: 'Newest' },
  { id: 'oldest', text: 'Oldest' },
  { id: 'viewes', text: 'Viewes' },
];

function MyPage() {
  const [answersSortOption, setAnswersSortOption] = useState('newest');
  const [questionsSortOption, setQuestionsSortOption] = useState('newest');
  const [tagsSortOption, setTagsSortOption] = useState('newest');

  const handleSortOption = (option, type) => {
    if (type === 'answers') {
      setAnswersSortOption(option);
    } else if (type === 'questions') {
      setQuestionsSortOption(option);
    } else if (type === 'tags') {
      setTagsSortOption(option);
    }
  };

  return (
    <MyPageContainer>
      <MyPageInfoContainer>
        <UserAvatarContainer>
          <img src={profile} alt="user avatar" />
        </UserAvatarContainer>
        <MyInfoText>
          <h1>Username</h1>
          <span>Registration date</span>
        </MyInfoText>
        <MyPageButtonContainer>
          <MyPageButton>
            <img src={pencilIcon} alt="pencil" />
            Edit Profile
          </MyPageButton>
          <MyPageButton>
            <img src={deleteIcon} alt="delete" />
            Delete Profile
          </MyPageButton>
        </MyPageButtonContainer>
      </MyPageInfoContainer>
      <MyPageInfoPanelContainer>
        <MyPageInfoPanelTitle>
          <h3>Answers</h3>
          <SortButtonGroup
            buttonData={buttonData}
            activeOption={answersSortOption}
            onClick={(option) => handleSortOption(option, 'answers')}
          />
        </MyPageInfoPanelTitle>
        <MyPageInfoPanel>
          {/* {answers.length > 0 ? (
            answers.map((item) => <li key={?}>{answers}</li>)
          ) : (
            <li>You have not answered any questions</li>
          )} */}
          <li>You have not answered any questions</li>
        </MyPageInfoPanel>
      </MyPageInfoPanelContainer>
      <MyPageInfoPanelContainer>
        <MyPageInfoPanelTitle>
          <h3>Questions</h3>
          <SortButtonGroup
            buttonData={buttonData}
            activeOption={questionsSortOption}
            onClick={(option) => handleSortOption(option, 'questions')}
          />
        </MyPageInfoPanelTitle>
        <MyPageInfoPanel>
          {/* {questions.length > 0 ? (
            questions.map((item) => <li key={?}>{questions}</li>)
          ) : (
            <li>You have not answered any questions</li>
          )} */}
          <li>You have not asked any questions</li>
        </MyPageInfoPanel>
      </MyPageInfoPanelContainer>
      <MyPageInfoPanelContainer>
        <MyPageInfoPanelTitle>
          <h3>Tags</h3>
          <SortButtonGroup
            buttonData={buttonData}
            activeOption={tagsSortOption}
            onClick={(option) => handleSortOption(option, 'tags')}
          />
        </MyPageInfoPanelTitle>
        <MyPageInfoPanel>
          {/* {tags.length > 0 ? (
            tags.map((tag) => <li key={?}>{tag}</li>)
          ) : (
            <li>You have not participated in any tags</li>
          )} */}
          <li>You have not participated in any tags</li>
        </MyPageInfoPanel>
      </MyPageInfoPanelContainer>
    </MyPageContainer>
  );
}

export default MyPage;
