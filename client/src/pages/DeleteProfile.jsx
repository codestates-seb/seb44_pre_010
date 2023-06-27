import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import useDeleteUser from '../hooks/useDeleteUser';
import { useDispatch } from 'react-redux';
import { open } from '../redux/reducers/modalSlice';
import { logout } from '../redux/reducers/loginSlice';

const MyInfoText = styled.div`
  border-bottom: 1px solid var(--bc-medium);

  & > h1 {
    margin: 1rem 0;
    display: flex;
    font-size: 2.125rem;
    color: var(--black-800);
  }
`;

const MyPageContainer = styled.div`
  width: 100%;
  margin: 0.5rem;
`;

const NotificationText = styled.p`
  margin-bottom: 1em;
`;
const NotificationContainer = styled.div`
  margin: 2rem 0;
  line-height: 150%;
`;

const NotificationList = styled.ul`
  margin-bottom: 1rem;
  list-style-type: disc;
  margin-left: 1.875rem;

  & > li {
    margin-bottom: 0.5rem;
  }
`;

const DeleteButton = styled.button`
  box-sizing: border-box;
  width: fit-content;
  height: 2.19rem;
  border: none;
  border-radius: 0.1875rem;
  margin-left: 0.225rem;
  color: var(--white);
  align-items: center;
  display: flex;
  padding: 0.6rem;
  font-size: 0.75rem;

  background-color: ${(props) =>
    props.disabled ? 'var(--red-200)' : 'var(--red-500)'};

  cursor: ${(props) => (props.disabled ? 'default' : 'pointer')};

  &:hover {
    background-color: ${(props) =>
      props.disabled ? 'none' : ' var(--red-600)'};
  }
`;

const DeleteTerms = styled.fieldset`
  margin-bottom: 2rem;
  display: flex;
  align-items: baseline;

  label {
    margin-left: 0.75rem;
    cursor: pointer;
    line-height: 150%;
  }

  input[type='checkbox'] {
    cursor: pointer;

    &:focus {
      border: solid var(--theme-secondary-400);
      border-radius: 0.1875rem;
      box-shadow: 0 0 0 0.25rem rgba(0, 116, 204, 0.15);
    }
  }
`;

const DeleteForm = styled.form``;

function DeleteProfile() {
  const [isChecked, setIsChecked] = useState(false);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const userId = localStorage.getItem('userId');

  const { deleteUser, isLoading } = useDeleteUser();

  const handleCheckboxChange = (event) => {
    setIsChecked(event.target.checked);
  };

  const handleDeleteProfile = async () => {
    const accessToken = localStorage.getItem('accessToken');

    try {
      const isSuccess = await deleteUser(userId, accessToken);
      if (isSuccess) {
        dispatch(logout());
        dispatch(
          open({
            modalType: 'deleteUserSuccess',
            isOpen: true,
          }),
        );
        navigate('/');
      } else {
        throw new Error('회원 탈퇴 실패');
      }
    } catch (error) {
      dispatch(open({ modalType: 'deleteUserfail', isOpen: true }));
    }
  };

  return (
    <MyPageContainer>
      <MyInfoText>
        <h1>Delete Profile</h1>
      </MyInfoText>
      <NotificationContainer>
        <NotificationText>
          Before confirming that you would like your profile deleted, we&apos;d
          like to take a moment to explain the implications of deletion:
        </NotificationText>
        <NotificationList>
          <li>
            Deletion is irreversible, and you will have no way to regain any of
            your original content, should this deletion be carried out and you
            change your mind later on.
          </li>
          <li>
            Your questions and answers will remain on the site, but will be
            disassociated and anonymized (the author will be listed as &quot;
            {userId}&quot;) and will not indicate your authorship even if you
            later return to the site.
          </li>
        </NotificationList>
        <NotificationText>
          Confirming deletion will only delete your profile on Stack Overflow -
          it will not affect any of your other profiles on the Stack Exchange
          network. If you want to delete multiple profiles, you&apos;ll need to
          visit each site separately and request deletion of those individual
          profiles.
        </NotificationText>
      </NotificationContainer>
      <DeleteForm>
        <DeleteTerms>
          <input
            type="checkbox"
            id="deleteCheckbox"
            checked={isChecked}
            onChange={handleCheckboxChange}
          />
          <label htmlFor="deleteCheckbox">
            I have read the information stated above and understand the
            implications of having my profile deleted. I wish to proceed with
            the deletion of my profile.
          </label>
        </DeleteTerms>
        <DeleteButton
          disabled={!isChecked || isLoading}
          onClick={handleDeleteProfile}
        >
          Delete Profile
        </DeleteButton>
      </DeleteForm>
    </MyPageContainer>
  );
}

export default DeleteProfile;
