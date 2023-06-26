import styled from 'styled-components';
import { useState, useRef, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import profile from '../assets/imgs/profile.png';
import UserAvatar from '../components/UserAvatar';
import { useSelector } from 'react-redux';
import { useEditUser } from '../hooks/useEditUser';

const MyPageContainer = styled.div`
  width: 100%;
`;

const MyInfoText = styled.div`
  border-bottom: 1px solid var(--bc-medium);

  & > h1 {
    margin: 1rem 0;
    display: flex;
    font-size: 2.125rem;
    color: var(--black-800);
  }
`;

const ProfileForm = styled.form`
  gap: 1rem;
  margin-top: 2rem;
  flex-direction: column;
  display: flex;
`;

const ProfileInput = styled.input`
  padding: 0.5rem;
  border: 1px solid var(--bc-medium);
  border-radius: 0.313rem;

  &[type='file'] {
    width: fit-content;
  }

  &[type='text'] {
    max-width: 253px;
    height: 20px;
  }
`;

const EditButton = styled.button`
  box-sizing: border-box;
  width: fit-content;
  height: 2.19rem;
  border: 0.0625rem solid var(--black-200);
  border-radius: 0.1875rem;
  margin-right: 0.4rem;
  color: var(--black-500);
  background-color: var(--white);
  align-items: center;
  display: flex;
  padding: 0.6rem;
  cursor: pointer;
  font-size: 0.75rem;
  overflow-clip-margin: content-box;
  overflow: clip;

  &:hover {
    background-color: var(--black-050);
  }
`;

const MyPageButtonContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
`;

const Label = styled.label`
  font-weight: bold;
  margin-bottom: 0.5rem;
`;

const ErrorMessage = styled.p`
  color: red;
  font-size: 0.75rem;
  margin: 0.25rem 0 0 0;
`;

function EditProfile() {
  const navigate = useNavigate();
  const location = useLocation();
  const username = location.state.username;
  const [name, setName] = useState(username);
  const { editUser, isLoading } = useEditUser();

  useEffect(() => {
    setName(username);
  }, [username]);

  const userId = useSelector((state) => state.login.userId);
  console.log(userId);
  const [Image, setImage] = useState(profile);
  const fileInput = useRef(null);
  const [errors, setErrors] = useState('');
  const [isSuccess, setIsSuccess] = useState(false);

  const handleAvatarChange = (e) => {
    e.preventDefault();
    if (e.target.files.length > 0) {
      const file = e.target.files[0];
      const imageUrl = URL.createObjectURL(file);
      setImage(imageUrl);
    }
  };

  const onClickAvatar = () => {
    if (fileInput.current && fileInput.current !== document.activeElement) {
      fileInput.current.click();
    }
  };

  const handleNameChange = (e) => {
    setName(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const accessToken = localStorage.getItem('accessToken');
    console.log('accessToken:', accessToken);

    if (!name) {
      setErrors('Displayname_empty');
    } else if (!/^[a-zA-Z0-9]+$/.test(name)) {
      setErrors('Displayname_specialChars');
    } else if (name.length < 3) {
      setErrors('Displayname_short');
    } else {
      try {
        await editUser(userId, name, accessToken);
        setIsSuccess(true);
        navigate('/mypage');
      } catch (error) {
        console.error('프로필 업데이트 오류:', error);
      }
    }
  };

  return (
    <MyPageContainer>
      <MyInfoText>
        <h1>Edit Profile</h1>
      </MyInfoText>
      <ProfileForm onSubmit={handleSubmit}>
        <Label htmlFor="avatar">Profile Image</Label>
        <UserAvatar size={270} profileUrl={Image} onClick={onClickAvatar} />
        <ProfileInput
          type="file"
          id="avatar"
          accept="image/*"
          onChange={handleAvatarChange}
          ref={fileInput}
        />
        <Label htmlFor="username">Display Name</Label>
        <ProfileInput
          id="username"
          type="text"
          value={name}
          onChange={handleNameChange}
          placeholder="username"
        />
        {errors.includes('Displayname_empty') && (
          <ErrorMessage>Display name cannot be empty.</ErrorMessage>
        )}
        {errors.includes('Displayname_short') && (
          <ErrorMessage>Must be 3 characters or more.</ErrorMessage>
        )}
        {errors.includes('Displayname_specialChars') && (
          <ErrorMessage>English letters and numbers only.</ErrorMessage>
        )}
        {errors.includes('Update_failed') && (
          <ErrorMessage>Failed to update profile.</ErrorMessage>
        )}
        <MyPageButtonContainer>
          <EditButton type="submit">Save Profile</EditButton>
          <EditButton
            type="button"
            onClick={() => {
              navigate('/mypage');
            }}
          >
            Cancel
          </EditButton>
        </MyPageButtonContainer>
      </ProfileForm>
    </MyPageContainer>
  );
}

export default EditProfile;
