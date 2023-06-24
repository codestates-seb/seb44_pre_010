import Header from '../components/layouts/Header.jsx';
import styled, { createGlobalStyle } from 'styled-components';
import { ReactComponent as GoogleLogo } from '../assets/icons/logo_google.svg';
import { ReactComponent as GithubLogo } from '../assets/icons/logo_github.svg';
import { ReactComponent as FacebookLogo } from '../assets/icons/logo_facebook.svg';
import { ReactComponent as Logo } from '../assets/icons/logo.svg';
import join from '../assets/imgs/join.png';
import { useState } from 'react';
import SignupModal from '../components/modal/SignupModal.jsx';

const GlobalStyle = createGlobalStyle`
  *, *::before, *::after {
    box-sizing: border-box;
  }
`;

const Container = styled.div`
  display: flex;
  flex-direction: row;
  height: 100vh;
  width: 100vw;
  background-color: #f1f2f3;
  align-items: center;
  justify-content: center;
`;

const SignupContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #f1f2f3;
`;

const GuideContainer = styled(SignupContainer)`
  margin-right: 2rem;
  @media (max-width: 768px) {
    display: none;
  }
`;

const GoogleSignup = styled.div`
  display: flex;
  align-items: flex-end;
  justify-content: center;
  background-color: #ffffff;
  width: 17.5rem;
  height: 2.361rem;
  border: 0.25rem 0;
  padding: 0.625rem;
  min-height: auto;
  min-width: auto;
  border-radius: 0.3125rem;
  border: 0.0625rem solid #d6d9dc;
  margin-top: 1.25rem;
  cursor: pointer;
`;

const GithubSignup = styled(GoogleSignup)`
  background-color: #232629;
  color: #ffffff;
  margin-top: 0.3125rem;
`;

const FacebookSignup = styled(GithubSignup)`
  background-color: #314a86;
`;

const Text = styled.span`
  font-size: 0.875rem;
  margin-left: 0.25rem;
`;
const FormContainer = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 17.5rem;
  height: auto;
  padding: 1.5rem;
  max-width: 19.75rem;
  margin: 1.5rem 0;
  background-color: #ffffff;
  line-height: 1.0625rem;
  border-radius: 0.4375rem;
  box-shadow: rgba(0, 0, 0, 0.05) 0px 0.625rem 1.5rem;
`;

const Label = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 0.9375rem;
  font-weight: 600;
  line-height: 1.1875rem;
  text-align: left;
  height: 1.1719rem;
  width: 15rem;
  margin: 0.925rem 0 0.125rem 0;
  padding: 0.0625rem 0.125rem 0.0625rem 0.125rem;
  min-height: auto;
  min-width: auto;
`;

const Input = styled.input`
  font-size: 0.8125rem;
  text-decoration: none solid rgb(12, 13, 14);
  height: 2.1094rem;
  width: 15rem;
  border: 0.0625rem solid #babfc4;
  padding: 0.5rem 0.5625rem 0.5rem 0.5625rem;
  min-height: auto;
  min-width: auto;
  display: block;
  border-radius: 0.1875rem;

  &:focus {
    outline: none;
    border-color: #59a4de;
    box-shadow: 0 0 0 0.25rem rgba(0, 116, 204, 0.15);
  }
  ${({ error }) =>
    error &&
    `
    border-color: red;
    box-shadow: 0 0 0 0.25rem rgba(255, 0, 0, 0.15);
  `}
`;

const Button = styled.button`
  height: 2.361rem;
  width: 15rem;
  border: 0.0625rem solid #ffffff;
  background-color: hsl(206, 100%, 52%);
  color: #ffffff;
  padding: 0.625rem;
  min-height: auto;
  min-width: auto;
  margin-top: 0.8rem;
  position: relative;
  cursor: pointer;
  box-shadow: 0 0.0625rem 0 0 inset rgba(255, 255, 255, 0.4);
  border-radius: 0.1875rem;

  &:hover {
    background-color: hsl(209, 100%, 37.5%);
  }

  &:active {
    box-shadow: 0 0 0 0.25rem rgba(0, 116, 204, 0.15);
  }
`;

const Guide = styled.span`
  font-size: 12px;
  color: #6a737c;
  margin-top: 0.8rem;
`;

const Guide_blue = styled(Guide)`
  color: hsl(206, 100%, 52%);
`;

const ErrorMessage = styled.p`
  color: red;
  font-size: 0.75rem;
  margin: 0.25rem 0 0 0;
`;

export default function SignUp() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [displayName, setDisplayName] = useState('');
  const [errors, setErrors] = useState([]);
  const [modalOpen, setModalOpen] = useState(false);

  const handleSignup = (e) => {
    e.preventDefault();
    const userData = {
      name: displayName,
      email: email,
      password: password,
    };

    setErrors([]);

    if (!displayName) {
      setErrors((prevErrors) => [...prevErrors, 'Displayname_empty']);
    } else if (!/^[a-zA-Z0-9]+$/.test(displayName)) {
      setErrors((prevErrors) => [...prevErrors, 'Displayname_specialChars']);
    } else if (displayName.length < 3) {
      setErrors((prevErrors) => [...prevErrors, 'Displayname_short']);
    }

    if (!email) {
      setErrors((prevErrors) => [...prevErrors, 'Email_empty']);
    } else if (!email.includes('@')) {
      setErrors((prevErrors) => [...prevErrors, 'Email_invalid']);
    }

    if (!password) {
      setErrors((prevErrors) => [...prevErrors, 'Password_empty']);
    } else if (!/^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*]*$/.test(password)) {
      setErrors((prevErrors) => [...prevErrors, 'Password_invalid']);
    } else if (password.length < 8) {
      setErrors((prevErrors) => [...prevErrors, 'Password_short']);
    } else {
      fetch(
        'http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/signup',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(userData),
        },
      )
        .then((response) => {
          if (response.status === 201) {
            return response.json().then((data) => {
              // 요청 성공 시!
              const { userId } = data;
              const userData = { userId };

              localStorage.setItem('user', JSON.stringify(userData));
              setModalOpen(true);
              console.log('회원가입에 성공했습니다!', userData);
            });
          } else if (response.status === 409) {
            // 로그인 실패 했을 경우
            return response.json().then((data) => {
              if (data.message === 'USER EXISTS') {
                setErrors((prevErrors) => [...prevErrors, 'User_exists']);
                throw new Error('이미 등록된 이메일입니다.');
              } else {
                throw new Error('회원가입에 실패했습니다.');
              }
            });
          }
        })

        .catch((error) => {
          console.error('회원가입 요청 중 오류가 발생했습니다.', error);
        });
    }
  };

  return (
    <>
      <GlobalStyle />
      <Header />
      <Container>
        <GuideContainer>
          <img src={join} alt="Join the Stack Overflow community" />
        </GuideContainer>
        <SignupContainer>
          <Logo />
          <GoogleSignup>
            <GoogleLogo /> <Text>Sign up with Google</Text>
          </GoogleSignup>
          <GithubSignup>
            <GithubLogo /> <Text>Sign up with Github</Text>
          </GithubSignup>
          <FacebookSignup>
            <FacebookLogo /> <Text>Sign up with Facebook</Text>
          </FacebookSignup>
          <FormContainer onSubmit={handleSignup}>
            <Label>Display name</Label>
            <Input
              type="text"
              value={displayName}
              onChange={(e) => setDisplayName(e.target.value)}
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
            <Label>Email</Label>
            <Input
              type="text"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {errors.includes('Email_empty') && (
              <ErrorMessage>Email cannot be empty.</ErrorMessage>
            )}

            {errors.includes('Email_invalid') && (
              <ErrorMessage>This email is not valid.</ErrorMessage>
            )}
            {errors.includes('User_exists') && (
              <ErrorMessage>이미 가입된 이메일 입니다.</ErrorMessage>
            )}
            <Label>Password</Label>
            <Input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {errors.includes('Password_empty') && (
              <ErrorMessage>Password cannot be empty.</ErrorMessage>
            )}
            {errors.includes('Password_invalid') && (
              <ErrorMessage>
                Must contain at least 1 letter and 1 number.
              </ErrorMessage>
            )}
            {errors.includes('Password_short') && (
              <ErrorMessage>Must be 8 characters or more.</ErrorMessage>
            )}

            <Guide>
              Passwords must contain at least eight characters, including at
              least 1 letter and 1 number.
            </Guide>
            <Button type="submit">Sign up</Button>
            <Guide>
              By clicking “Sign up”, you agree to our{' '}
              <Guide_blue>terms of service</Guide_blue> and acknowledge that you
              have read and understand our{' '}
              <Guide_blue>privacy policy</Guide_blue> and{' '}
              <Guide_blue>code of conduct.</Guide_blue>
            </Guide>
          </FormContainer>
        </SignupContainer>
      </Container>
      {modalOpen && <SignupModal />}
    </>
  );
}
