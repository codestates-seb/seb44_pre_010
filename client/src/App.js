import { Outlet } from 'react-router-dom';
import { useEffect, useState } from 'react';

import styled from 'styled-components';
import Header from './components/layouts/Header.jsx';
import Footer from './components/layouts/Footer.jsx';
import SideMenu from './components/layouts/SideMenu.jsx';
import MainContent from './components/layouts/MainContent.jsx';
import GlobalModal from './components/modal/GlobalModal.jsx';
import { useDispatch } from 'react-redux';
import { setLoginStatus } from './redux/reducers/loginSlice';

const MainWrapper = styled.main`
  max-width: 1264px;
  width: 100%;
  display: flex;
  margin: 0 auto;
  flex: 1;
`;

function App() {
  const [isSelect, setIsSelect] = useState(0);

  const dispatch = useDispatch();

  const onHandleSelect = (index) => {
    setIsSelect(index);
  };

  useEffect(() => {
    setIsSelect(0);
    // 새로고침 시 로컬스토리지에서 토큰확인하고 상태변경
    const storedAccessToken = localStorage.getItem('accessToken');
    if (storedAccessToken) {
      dispatch(setLoginStatus({ isLoggedIn: true }));
    } else {
      dispatch(setLoginStatus({ isLoggedIn: false }));
    }
  }, []);

  return (
    <>
      <Header />
      <MainWrapper>
        <SideMenu selected={isSelect} handleSelected={onHandleSelect} />
        <MainContent>
          <Outlet context={{ onHandleSelect }} />
        </MainContent>
      </MainWrapper>
      <Footer />
      <GlobalModal />
    </>
  );
}

export default App;
