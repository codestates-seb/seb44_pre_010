import { Outlet } from 'react-router-dom';
import { useEffect, useState } from 'react';

import styled from 'styled-components';
import Header from './components/layouts/Header.jsx';
import Footer from './components/layouts/Footer.jsx';
import SideMenu from './components/layouts/SideMenu.jsx';
import MainContent from './components/layouts/MainContent.jsx';
import GlobalModal from './components/modal/GlobalModal.jsx';
import { sideMenuList } from './constants/SideMenuContnats.js';

const MainWrapper = styled.main`
  max-width: 1264px;
  width: 100%;
  display: flex;
  margin: 0 auto;
  flex: 1;
`;

function App() {
  const [isSelect, setIsSelect] = useState(0);

  const onHandleSelect = (index) => {
    setIsSelect(index);
  };

  useEffect(() => {
    setIsSelect(0);
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
