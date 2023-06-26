import styled from 'styled-components';
import editIcon from '../assets/icons/edit_profile.svg';
import deleteIcon from '../assets/icons/delete_profile.svg';
import SortButtonGroup from '../components/SortButtonGroup.jsx';
import { buttonData, panelData } from '../constants/MyPageConstants';
import { useEffect, useState } from 'react';
import UserAvatar from '../components/UserAvatar.jsx';
import { Link, useOutletContext, useNavigate } from 'react-router-dom';
import { useUserData } from '../hooks/useUserData';
import cake from '../assets/icons/cake.svg';
import { calculateTimeSince } from '../utils/calculateTimeSince';
import { changeTimeFormat } from '../utils/changeTimeFormat.js';
import Pagination from '../components/pagenation/Pagenation';
import { useSelector, useDispatch } from 'react-redux';
import { setLoginStatus } from '../redux/reducers/loginSlice';

const MyPageContainer = styled.div`
  width: 100%;
`;

const MyPageInfoContainer = styled.div`
  display: flex;
  margin-bottom: 1rem;
  align-items: center;
  flex-wrap: wrap;
  margin: 0.5rem;
  justify-content: space-between;
  position: relative;
`;

const MyInfo = styled.div`
  display: flex;
  flex-direction: row;
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
    vertical-align: inherit;
  }

  img {
    margin-right: 0.25rem;
    vertical-align: sub;
  }
`;

const MyPageButtonContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  margin: 0.188rem;
  position: absolute;
  top: 0;
  right: 0;
`;

const MyPageButton = styled(Link)`
  box-sizing: border-box;
  width: fit-content;
  height: 2.19rem;
  border: 0.0625rem solid var(--black-200);
  border-radius: 0.1875rem;
  margin-left: 0.4rem;
  color: var(--black-500);
  background-color: var(--white);
  align-items: center;
  display: flex;
  padding: 0.6rem;
  cursor: pointer;
  font-size: 0.75rem;
  overflow-clip-margin: content-box;
  overflow: clip;

  & > img {
    display: inline-flex;
    margin: 0.225rem;
    width: 0.875rem;
    height: 0.875rem;
    object-fit: cover;
    width: 15px;
    height: 15px;
  }

  &:hover {
    background-color: var(--black-050);
  }
`;

const MyPageInfoPanelContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin: 3rem 1.5rem;
`;

const MyPageInfoPanelTitle = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 0.5rem;

  & > h3 {
    font-size: 1.61538462rem;
  }
`;

const MyPageInfoPanel = styled.div`
  border: 0.0625rem solid var(--black-100);
  margin-bottom: 0.5rem;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 0.1875rem;
  flex-direction: column;

  & > div:not(:last-child) {
    border-bottom: 0.0625rem solid var(--black-100);
  }
`;
const MyPageInfoPanelList = styled.div`
  width: 100%;
  padding: 0.4rem 0;
  display: flex;
  justify-content: space-between;
  align-items: center;

  & > span {
    color: var(--black-500);
    text-align: right;
    padding: 1rem;
    min-width: fit-content;
    font-size: 0.875rem;
  }
`;

const MyPageInfoPanelLink = styled(Link)`
  font-size: 0.938rem;
  color: var(--blue-600);
  padding: 1rem;
  text-decoration: none;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  align-items: baseline;

  & > img {
    margin-right: 1rem;
    vertical-align: middle;
  }
`;

const MyPageEmptyPanel = styled.span`
  font-size: 0.813rem;
  color: var(--black-500);
  padding: 2rem 0rem;
  width: 100%;
  text-align: center;
`;

function MyPage() {
  const dispatch = useDispatch();
  const userId = localStorage.getItem('userId');
  const isLoggedIn = useSelector((state) => state.login.isLoggedIn);
  const accessToken = localStorage.getItem('accessToken');
  const navigate = useNavigate();

  const { onHandleSelect } = useOutletContext();

  const [sortOptions, setSortOptions] = useState(() => {
    const initialSortOptions = {};
    panelData.forEach((panel) => {
      initialSortOptions[panel.id] = 'newest';
    });
    return initialSortOptions;
  });

  const [panelPage, setPanelPage] = useState(() => {
    const initialPanelPage = {};
    panelData.forEach((panel) => {
      initialPanelPage[panel.id] = 1;
    });
    return initialPanelPage;
  });

  const [panelLimits, setPanelLimits] = useState(() => {
    const initialLimits = {};
    panelData.forEach((panel) => {
      initialLimits[panel.id] = 5;
    });
    return initialLimits;
  });

  useEffect(() => {
    // 새로고침 시 로컬스토리지에서 토큰확인하고 상태변경
    if (accessToken) {
      dispatch(setLoginStatus({ isLoggedIn: true }));
    }
  }, [dispatch, accessToken]);

  useEffect(() => {
    if (!accessToken) {
      alert('로그인이 필요합니다.');
      navigate('/login');
    }
  }, [isLoggedIn, navigate]);

  useEffect(() => {
    onHandleSelect(3);
  }, []);

  useEffect(() => {
    const initialPanelPage = {};
    panelData.forEach((panel) => {
      initialPanelPage[panel.id] = 1;
    });
    setPanelPage(initialPanelPage);
  }, []);

  const {
    data: fetchedData,
    isLoading,
    error,
  } = useUserData(userId, accessToken);
  const userData = fetchedData?.data;
  console.log(userData);

  const getSortedPanelList = (panelId) => {
    const sortFunction = buttonData.find(
      (btn) => btn.id === sortOptions[panelId],
    ).sortFunction;
    return [...(userData[`${panelId}List`] || [])].sort(sortFunction);
  };

  const handleSortOption = (option, type) => {
    setSortOptions((prevOptions) => ({
      ...prevOptions,
      [type]: option,
    }));
  };

  const handlePanelLimitChange = (panelId, newLimit) => {
    setPanelLimits((prevLimits) => ({
      ...prevLimits,
      [panelId]: newLimit,
    }));
  };

  const handlePanelPageChange = (panelId, newPage) => {
    setPanelPage((prevPanelPage) => ({
      ...prevPanelPage,
      [panelId]: newPage,
    }));
  };

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <MyPageContainer>
      <MyPageInfoContainer>
        <MyInfo>
          <UserAvatar hasShadow={true} />
          <MyInfoText>
            <h1>{userData.name}</h1>
            <span>
              <img src={cake} alt="cake" />
              Member for {calculateTimeSince(userData.createdAt)}
            </span>
          </MyInfoText>
        </MyInfo>
        <MyPageButtonContainer>
          <div>
            <MyPageButton
              to={`/mypage/edit`}
              state={{ username: userData.name }}
            >
              <img src={editIcon} alt="pencil" />
              Edit Profile
            </MyPageButton>
          </div>
          <div>
            <MyPageButton to={`/mypage/delete`}>
              <img src={deleteIcon} alt="delete" />
              Delete Profile
            </MyPageButton>
          </div>
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
            {(userData[`${panel.id}List`] || []).length === 0 ? (
              <MyPageEmptyPanel>
                You have not {panel.emptyMessage}
              </MyPageEmptyPanel>
            ) : (
              getSortedPanelList(panel.id)
                .slice(
                  (panelPage[panel.id] - 1) * panelLimits[panel.id],
                  panelPage[panel.id] * panelLimits[panel.id],
                )
                .map((item) => (
                  <MyPageInfoPanelList key={item[`${panel.id}Id`]}>
                    <MyPageInfoPanelLink
                      to={`questions/${item[`${panel.id}Id`]}`}
                    >
                      <img src={panel.icon} alt={`${panel.id} icon`} />
                      {item.title || item.content}
                    </MyPageInfoPanelLink>
                    <span>{changeTimeFormat(item.createdAt)}</span>
                  </MyPageInfoPanelList>
                ))
            )}
          </MyPageInfoPanel>
          <Pagination
            total={(userData[`${panel.id}List`] || []).length}
            limit={panelLimits[panel.id]}
            page={panelPage[panel.id] || 1}
            setPage={(newPage) => handlePanelPageChange(panel.id, newPage)}
            setLimit={(newLimit) => handlePanelLimitChange(panel.id, newLimit)}
          />
        </MyPageInfoPanelContainer>
      ))}
    </MyPageContainer>
  );
}

export default MyPage;
