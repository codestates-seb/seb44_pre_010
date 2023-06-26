import styled from 'styled-components';

import UserAvatar from './UserAvatar';
import { formatAgo } from '../utils/date';

const UserInfo = styled.div`
  box-sizing: border-box;
  padding: 5px 6px 7px 7px;
  color: var(--black-500);
`;

const UserInfoTop = styled.div`
  font-size: 13px;
  margin-top: 1px;
  margin-bottom: 4px;
`;

const UserInfoBottom = styled.div`
  display: flex;
  gap: '4px';
  align-items: center;
`;

export default function UserCard({ type, created, name }) {
  return (
    <UserInfo>
      <UserInfoTop>
        {type} <span> {formatAgo(created)} </span>
      </UserInfoTop>
      <UserInfoBottom>
        <UserAvatar size={32} />
        <div>{name}</div>
      </UserInfoBottom>
    </UserInfo>
  );
}
