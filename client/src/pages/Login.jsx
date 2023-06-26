import { useState } from 'react';
import { useDispatch } from 'react-redux';
import styled, { createGlobalStyle } from 'styled-components';
import { ReactComponent as Logo } from '../assets/icons/logo.svg';
import Header from '../components/layouts/Header';
import { ReactComponent as GoogleLogo } from '../assets/icons/logo_google.svg';
import { ReactComponent as GithubLogo } from '../assets/icons/logo_github.svg';
import { ReactComponent as FacebookLogo } from '../assets/icons/logo_facebook.svg';
import { useNavigate } from 'react-router-dom';
import { login } from '../redux/reducers/loginSlice';

const GlobalStyle = createGlobalStyle`
  *, *::before, *::after {
    box-sizing: border-box;
  }
`;

const LoginContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: #f1f2f3;
  height: 100vh;
`;

const FormContainer = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 17.5rem;
  height: auto;
  padding: 1.5rem;
  margin: 1.5rem 0;
  background-color: #ffffff;
  line-height: 1.0625rem;
  border-radius: 0.4375rem;
  box-shadow: rgba(0, 0, 0, 0.05) 0px 0.625rem 1.5rem;
`;

const LoginForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  height: auto;
  width: 17.5rem;
  font-size: 0.8125rem;
  line-height: 1.0625rem;
  text-align: left;
  margin: -0.375rem 0 -0.375rem 0;
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
`;

const ForgotPassword = styled.span`
  font-size: 0.75rem;
  color: #0a95ff;
  cursor: pointer;
`;

const Input = styled.input`
  font-size: 0.8125rem;
  text-decoration: none solid rgb(12, 13, 14);
  height: 2.1094rem;
  width: 15rem;
  border: 0.0625rem solid #babfc4;
  padding: 0.5rem 0.5625rem 0.5rem 0.5625rem;
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
  margin: 0.75rem 0;
  padding: 0.625rem;
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

const ErrorMessage = styled.p`
  color: red;
  font-size: 0.75rem;
  margin: 0.25rem 0 0 0;
`;

const GoogleLogin = styled.div`
  display: flex;
  align-items: flex-end;
  justify-content: center;
  background-color: #ffffff;
  width: 17.5rem;
  height: 2.361rem;
  padding: 0.625rem;
  border-radius: 0.3125rem;
  border: 0.0625rem solid #d6d9dc;
  margin-top: 1.25rem;
  cursor: pointer;
`;

const GithubLogin = styled(GoogleLogin)`
  background-color: #232629;
  color: #ffffff;
  margin-top: 0.3125rem;
`;

const FacebookLogin = styled(GithubLogin)`
  background-color: #314a86;
`;

const Text = styled.span`
  font-size: 0.875rem;
  margin-left: 0.25rem;
`;

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState([]);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();

    // 오류 메시지 초기화
    setErrors([]);

    if (!email) {
      setErrors((prevErrors) => [...prevErrors, 'Email_empty']);
    } else if (!email.includes('@')) {
      setErrors((prevErrors) => [...prevErrors, 'Email_invalid']);
    }

    if (!password) {
      setErrors((prevErrors) => [...prevErrors, 'Password_empty']);
    } else {
      // 유효성 검사를 통과한 경우에만 로그인 시도
      fetch(
        'http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/login',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ username: email, password: password }),
        },
      )
        .then((response) => {
          if (
            // 헤더에 토큰이 포함된다면 로그인 성공
            response.headers.get('Authorization') &&
            response.headers.get('Refresh')
          ) {
            const accessToken = response.headers
              .get('Authorization')
              .split(' ')[1]; // Bearer를 건너뛰고 실제 토큰 부분을 추출
            const refreshToken = response.headers.get('Refresh');

            response.json().then((data) => {
              const userId = data.userId;
              console.log(userId);

              // 토큰 저장
              localStorage.setItem('accessToken', accessToken);
              localStorage.setItem('refreshToken', refreshToken);
              localStorage.setItem('userId', userId);
              // 상태 변경
              dispatch(login({ accessToken, refreshToken, userId }));

              // 메인페지이로 이동
              navigate('/');
            });
            return;
          } else if (response.status === 401) {
            // 로그인 실패 했을 경우
            return response.json().then((data) => {
              if (data.message === 'Member not found : Unauthorized') {
                setErrors((prevErrors) => [...prevErrors, 'NotMember']);
                throw new Error('등록된 이메일이 아닙니다.');
              } else if (data.message === 'Unauthorized') {
                setErrors((prevErrors) => [...prevErrors, 'WrongPassword']);
                throw new Error('비밀번호가 잘못되었습니다.');
              } else {
                throw new Error('로그인에 실패했습니다.');
              }
            });
          }
        })

        .catch((error) => {
          console.error('로그인 요청 중 오류가 발생했습니다.', error);
        });
    }
  };

  return (
    <>
      <GlobalStyle />
      <Header />
      <LoginContainer>
        <Logo />
        <GoogleLogin>
          <GoogleLogo /> <Text>Log in with Google</Text>
        </GoogleLogin>
        <GithubLogin>
          <GithubLogo /> <Text>Log in with Github</Text>
        </GithubLogin>
        <FacebookLogin>
          <FacebookLogo /> <Text>Log in with Facebook</Text>
        </FacebookLogin>
        <FormContainer>
          <LoginForm onSubmit={handleLogin}>
            <Label>Email</Label>
            <Input
              type="text"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              error={
                errors.includes('Email_empty') ||
                errors.includes('Email_invalid')
              }
            />
            {errors.includes('Email_empty') && (
              <ErrorMessage>Email cannot be empty.</ErrorMessage>
            )}
            {errors.includes('Email_invalid') && (
              <ErrorMessage>
                This email is not a valid email address.
              </ErrorMessage>
            )}
            {errors.includes('NotMember') && (
              <ErrorMessage>This is not a registered email.</ErrorMessage>
            )}

            <Label>
              Password
              <ForgotPassword>Forgot password?</ForgotPassword>
            </Label>
            <Input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              error={errors.includes('Password_empty')}
            />
            {errors.includes('Password_empty') && (
              <ErrorMessage>Password cannot be empty.</ErrorMessage>
            )}
            {errors.includes('WrongPassword') && (
              <ErrorMessage>Passwords do not match.</ErrorMessage>
            )}
            <Button type="submit" onClick={handleLogin}>
              Log In
            </Button>
          </LoginForm>
        </FormContainer>
      </LoginContainer>
    </>
  );
};

export default Login;
