import { Outlet } from 'react-router-dom';

import styled from 'styled-components';
import Header from './components/layouts/Header.jsx';
import Footer from './components/layouts/Footer.jsx';
import SideMenu from './components/layouts/SideMenu.jsx';
import MainContent from './components/layouts/MainContent.jsx';

// import MyPage from './pages/Mypage/MyPage.jsx';

const MainWrapper = styled.main`
  max-width: 1264px;
  width: 100%;
  display: flex;
  margin: 0 auto;
  flex: 1;
`;

function App() {
  return (
    <>
      <Header />
      <MainWrapper>
        <SideMenu />
        <MainContent>
          <Outlet />
        </MainContent>
      </MainWrapper>
      <Footer />
    </>
  );
}

export default App;
