import Header from '../components/layouts/Header.jsx';
import styled, { createGlobalStyle } from 'styled-components';
import { ReactComponent as GoogleLogo } from '../assets/icons/logo_google.svg';
import { ReactComponent as GithubLogo } from '../assets/icons/logo_github.svg';
import { ReactComponent as FacebookLogo } from '../assets/icons/logo_facebook.svg';
import { ReactComponent as Logo } from '../assets/icons/logo.svg';
import join from '../assets/imgs/join.png';

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
  height: 90vh;
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
  margin: 0.125rem 0 0.125rem 0;
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
  margin-bottom: 0.8rem;

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
  min-height: auto;
  min-width: auto;
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
`;

const Guide_blue = styled(Guide)`
  color: hsl(206, 100%, 52%);
`;

export default function SignUp() {
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
          <FormContainer>
            <Label>Dispay name</Label>
            <Input type="text"></Input>
            <Label>Email</Label>
            <Input type="text"></Input>
            <Label>Password</Label>
            <Input type="text"></Input>
            <Guide>
              Passwords must contain at least eight characters, including at
              least 1 letter and 1 numner.
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
    </>
  );
}
