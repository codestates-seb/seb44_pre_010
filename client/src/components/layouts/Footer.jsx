import styled from 'styled-components';
import { ReactComponent as Logo } from '../../assets/icons/logo.svg';
const FooterContainer = styled.footer``;

export default function Footer() {
  return (
    <FooterContainer>
      <Logo />
    </FooterContainer>
  );
}
