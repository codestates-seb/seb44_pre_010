import styled from 'styled-components';
import profile from '../assets/imgs/profile.png';

const UserAvatarContainer = styled.div`
  border-radius: 0.313rem;
  width: ${({ size }) => size}px;
  aspect-ratio: auto 1 / 1;
  height: ${({ size }) => size}px;
  overflow-clip-margin: content-box;
  overflow: clip;

  box-shadow: ${({ hasShadow }) => (hasShadow ? 'var(--bs-sm)' : 'none')};
  cursor: ${({ onClick }) => (onClick ? 'pointer' : 'default')};
`;

const UserAvatarImage = styled.img`
  width: 100%;
  height: 100%;
  object-fit: cover;
`;

function UserAvatar({
  profileUrl = profile,
  size = 128,
  hasShadow = false,
  onClick,
}) {
  return (
    <UserAvatarContainer size={size} hasShadow={hasShadow} onClick={onClick}>
      <UserAvatarImage src={profileUrl} alt="user avatar" />
    </UserAvatarContainer>
  );
}

export default UserAvatar;
