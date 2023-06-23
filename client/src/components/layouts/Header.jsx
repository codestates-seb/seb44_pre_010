import styled from 'styled-components';
import { ReactComponent as SearchIcon } from '../../assets/icons/search.svg';
import { ReactComponent as Logo } from '../../assets/imgs/mainLogo.svg';
import { ReactComponent as LogOutIcon } from '../../assets/icons/logout.svg';
import { Link } from 'react-router-dom';
import { useState } from 'react';
import UserAvatar from '../UserAvatar';

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

const SearchForm = styled.form`
  padding: 0 0.5rem;
  display: flex;
  align-items: center;
  flex-shrink: 1000;
  flex-grow: 1;
  box-sizing: border-box;
`;

const SearchFormInner = styled.div`
  position: relative;
  flex-grow: 1;
`;

const SearchInput = styled.input`
  border: 1px solid var(--black-200);
  background-color: white;
  color: var(--black-700);
  font-size: 13px;
  border-radius: 3px;
  line-height: calc((13 + 2) / 13);
  padding: 0.5rem 0.6rem 0.5rem 2rem;
  width: 100%;
  margin: 0;
  box-sizing: border-box;

  &::placeholder {
    color: var(--black-200);
    font-size: 12px;
  }
`;

const SearchInputIcon = styled(SearchIcon)`
  color: var(--black-400);
  margin-top: -9px;
  pointer-events: none;
  position: absolute;
  left: 0.7rem;
  top: 50%;
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
  const [searchKeyword, setSearchKeyword] = useState('');
  const isLogin = true;
  // const [isLogin, setIsLogin] = useState(false); // 임시 로그인 상태값입니다. 차후 로그인 세션 구현이 되면 리팩토링 예정
  // const dispatch = useDispatch();

  const onHandleChangeKeyword = (e) => {
    setSearchKeyword(e.target.value);
  };

  return (
    <HeaderContainer>
      <HeaderInner>
        <LogoLink to="/">
          <LogoIcon />
        </LogoLink>
        <SearchForm id="search" role="search" action="" autoComplete="off">
          <SearchFormInner>
            <SearchInput
              type="text"
              role="combobox"
              placeholder="Search..."
              autoComplete="off"
              maxLength="240"
              value={searchKeyword}
              onChange={onHandleChangeKeyword}
              aria-label="Search"
              aria-expanded="false"
              aria-controls="top-serach"
            />
            <SearchInputIcon />
          </SearchFormInner>
        </SearchForm>
        <Nav>
          {!isLogin ? (
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
                <Link to="/">
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
