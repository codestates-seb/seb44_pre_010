import styled from 'styled-components';
import { Content } from './Content.jsx';
import profileImage from '../../assets/imgs/profile.png';
import pencilIcon from '../../assets/icons/pencil.svg';
import deleteIcon from '../../assets/icons/delete.svg';

const MyPageContainer = styled.div`
  width: 100%;
  padding: 0;
  box-sizing: inherit;
  margin: 0;
  border: 0;
  font: inherit;
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
  font: inherit;
  font-size: 100%;
  vertical-align: baseline;
  text-align: left;
`;

const MyPageInfo = styled.div`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  margin: 0.5rem;
  box-sizing: inherit;
  text-align: left;
  padding: 0;
  border: 0;
  font: inherit;
  font-size: 100%;
  vertical-align: baseline;
`;

const UserAvartarContainer = styled.div`
  box-sizing: inherit;
  margin: 0;
  padding: 0;
  border: 0;
  font: inherit;
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
  font: inherit;
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

const MypageButtonContainer = styled.div`
  position: absolute;
  display: flex;
  right: 0;
  top: 0;
  flex-wrap: wrap;
  margin: 0.188rem;
`;
const MypageButton = styled.div`
  box-sizing: border-box;
  width: fit-content;
  height: 2.19rem;
  top: 0px;
  border: 0.0625rem solid #9fa6ad;
  border-radius: 0.1875rem;
  margin-left: 0.225rem;
  color: var(--black-500);
  background-color: var(--white);
  align-items: center;
  text-align: center;
  display: flex;
  padding: 0.6rem;
  cursor: pointer;

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

function MyPage() {
  return (
    <Content>
      <MyPageContainer>
        <MyPageInfoContainer>
          <MyPageInfo>
            <UserAvartarContainer>
              <img src={profileImage} alt="user avatar" />
            </UserAvartarContainer>
            <MyInfoText>
              <h1>Username</h1>
              <span>Registration date</span>
            </MyInfoText>
            <MypageButtonContainer>
              <MypageButton>
                <img src={pencilIcon} alt="pencile" />
                Edit Profile
              </MypageButton>
              <MypageButton>
                <img src={deleteIcon} alt="delete" />
                Delete Profile
              </MypageButton>
            </MypageButtonContainer>
          </MyPageInfo>
        </MyPageInfoContainer>
      </MyPageContainer>
    </Content>
  );
}

export default MyPage;
