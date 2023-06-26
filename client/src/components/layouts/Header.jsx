import styled from 'styled-components';
import { ReactComponent as Logo } from '../../assets/imgs/mainLogo.svg';
import { ReactComponent as LogOutIcon } from '../../assets/icons/logout.svg';
import { Link } from 'react-router-dom';
import UserAvatar from '../UserAvatar';
import SearchBar from './SearchBar';
import { useDispatch, useSelector } from 'react-redux';
import { logout, setLoginStatus } from '../../redux/reducers/loginSlice';
import { useEffect } from 'react';

const HeaderContainer = styled.header`
  position: fixed;
  min-width: auto;
  width: 100%;
  height: 3.5rem;
  min-height: 56px;
  display: flex;
  align-items: center;
  border-top: 3px solid var(--orange);
  border-bottom: 1px solid var(--black-100);
  box-sizing: border-box;
  top: 0;
  background-color: var(--white);
  z-index: 9999;
`;

const HeaderInner = styled.div`
  width: 1264px;
  max-width: 100%;
  height: 100%;
  display: flex;
  margin: 0 auto;
  align-items: center;
`;

const LogoIcon = styled(Logo)`
  margin: -4px 0 0;
`;

const LogoLink = styled(Link)`
  padding: 0 0.5rem;
  height: 100%;
  display: flex;
  align-items: center;
  background-color: transparent;
`;

const Nav = styled.nav`
  height: 100%;
  margin-left: auto;
  padding-right: 12px;
  overflow-x: auto;
`;

const NavContainer = styled.ol`
  display: flex;
  height: 100%;
  margin: 0;
  padding: 0;
  overflow-x: auto;
  margin-left: auto;
  align-items: center;

  > li {
    display: inline-flex;
    padding: 0 10px;
    align-items: center;

    > span {
      font-size: 12px;
      font-weight: 700;
      margin-left: 5px;
    }
  }
`;

const Button = styled(Link)`
  align-self: center;
  font-size: 13px;
  padding: 8px 0.8em;
  cursor: pointer;
  display: inline-block;
  position: relative;
  text-align: center;
  border: 1px solid var(--powder-500);
  border-radius: 3px;
  background-color: var(--powder-100);
  color: var(--powser-700);
  text-decoration: none;
  outline: none;
  box-sizing: border-box;
`;

const SignUpButton = styled(Button)`
  color: white;
  background-color: var(--blue-500);
`;

const IconListItem = styled(Link)`
  cursor: pointer;
  transition: all 500s ease-in;
  margin: 0 0.5rem;

  &:hover {
    transform: scale(1.5);
  }
`;

export default function Header() {
  const { isLoggedIn } = useSelector((state) => state.login);
  const dispatch = useDispatch();

  // 로그아웃 시 토큰 삭제
  const handleLogout = () => {
    dispatch(logout());
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
  };

  useEffect(() => {
    // 페이지 로드 시 로컬 스토리지에서 로그인 상태 확인하여 Redux 상태 설정
    const storedLoginStatus = localStorage.getItem('isLoggedIn');
    if (storedLoginStatus === 'true') {
      dispatch(setLoginStatus({ isLoggedIn: true }));
    }
  }, []);

  return (
    <HeaderContainer>
      <HeaderInner>
        <LogoLink to="/">
          <LogoIcon />
        </LogoLink>
        <SearchBar></SearchBar>
        <Nav>
          {!isLoggedIn ? (
            <NavContainer>
              <li>
                <Button to="/login">Log in</Button>
              </li>{' '}
              <li>
                <SignUpButton to="/signup">Sign up</SignUpButton>
              </li>
            </NavContainer>
          ) : (
            <NavContainer>
              <IconListItem>
                <Link to="/mypage">
                  <UserAvatar size={24} hasShadow={true} />
                </Link>
              </IconListItem>
              <IconListItem>
                <Link to="/" onClick={handleLogout}>
                  <LogOutIcon fill="var(--orange)" />
                </Link>
              </IconListItem>
            </NavContainer>
          )}
        </Nav>
      </HeaderInner>
    </HeaderContainer>
  );
}
