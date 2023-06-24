import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { sideMenuList } from '../../constants/SideMenuContnats.js';

const LeftSideBarContainer = styled.div`
  width: 164px;
  flex-shrink: 0;
  z-index: 1000;
`;

const Inner = styled.div`
  position: sticky;
  max-height: calc(100vh - 56px);
  height: fit-content;
  width: auto;
  padding-top: 24px;
  margin-bottom: 8px;
  overflow-y: auto;
  top: 56px;
`;

const MenuItem = styled.li`
  width: 100%;
  background-color: ${(props) => props.isSelected && 'var(--black-050)'};
  border-right: ${(props) => props.isSelected && '3px solid var(--orange)'};
  box-sizing: border-box;
  a {
    display: block;
    padding: 4px 4px 4px 8px;
    color: ${(props) => props.isSelected && 'var(--black-900)'};
    font-weight: ${(props) => props.isSelected && 'bold'};
  }
`;

const MenuLink = styled(Link)`
  padding: 4px;
  line-height: 2;
  font-size: 13px;
`;

export default function SideMenu({ selected, handleSelected }) {
  // const navigate = useNavigate();

  return (
    <LeftSideBarContainer>
      <Inner>
        <ul>
          {sideMenuList.map((item, index) => (
            <MenuItem
              key={index}
              isSelected={index === selected}
              onClick={() => handleSelected(index)}
            >
              <MenuLink to={item.link}>{item.text}</MenuLink>
            </MenuItem>
          ))}
        </ul>
      </Inner>
    </LeftSideBarContainer>
  );
}
