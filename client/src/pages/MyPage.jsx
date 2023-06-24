import styled from 'styled-components';
import pencilIcon from '../assets/icons/pencil.svg';
import deleteIcon from '../assets/icons/delete.svg';
import SortButtonGroup from '../components/SortButtonGroup.jsx';
import { buttonData, panelData } from '../constants/MyPageConstants';
import { useState } from 'react';
import UserAvatar from '../components/UserAvatar.jsx';

const MyPageContainer = styled.div`
  width: 100%;
`;

const MyPageInfoContainer = styled.div`
  display: flex;
  margin-bottom: 1rem;
  align-items: center;
  flex-wrap: wrap;
  margin: 0.5rem;
`;

const MyInfoText = styled.div`
  margin: 0.5rem;

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
  display: flex;
  flex-wrap: wrap;
  margin: 0.188rem;
`;

const MyPageButton = styled.div`
  box-sizing: border-box;
  width: fit-content;
  height: 2.19rem;
  border: 0.0625rem solid var(--black-300);
  border-radius: 0.1875rem;
  margin-left: 0.225rem;
  color: var(--black-500);
  background-color: var(--white);
  align-items: center;
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
  border-radius: 0.1875rem;

  & > li {
    font-size: 0.813rem;
    color: var(--black-500);
  }
`;

function MyPage() {
  const [sortOptions, setSortOptions] = useState(() => {
    const initialSortOptions = {};
    panelData.forEach((panel) => {
      initialSortOptions[panel.id] = 'newest';
    });
    return initialSortOptions;
  });

  const handleSortOption = (option, type) => {
    setSortOptions((prevOptions) => ({
      ...prevOptions,
      [type]: option,
    }));
  };

  return (
    <MyPageContainer>
      <MyPageInfoContainer>
        <UserAvatar hasShadow={true} />
        <MyInfoText>
          <h1>username</h1>
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
      {panelData.map((panel) => (
        <MyPageInfoPanelContainer key={panel.id}>
          <MyPageInfoPanelTitle>
            <h3>{panel.text}</h3>
            <SortButtonGroup
              buttonData={buttonData}
              activeOption={sortOptions[panel.id]}
              onClick={(option) => handleSortOption(option, panel.id)}
            />
          </MyPageInfoPanelTitle>
          <MyPageInfoPanel>
            <li>You have not {panel.emptyMessage}</li>
          </MyPageInfoPanel>
        </MyPageInfoPanelContainer>
      ))}
    </MyPageContainer>
  );
}

export default MyPage;
