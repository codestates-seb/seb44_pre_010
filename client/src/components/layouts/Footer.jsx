import styled from 'styled-components';
import { ReactComponent as Logo } from '../../assets/icons/logo.svg';
import FooterSiteLink from './FooterSiteLink.jsx';

const FooterContainer = styled.footer`
  background-color: var(--black-700);
`;

const FooterInner = styled.div`
  max-width: 1264px;
  width: 100%;
  margin: 0 auto;
  padding: 32px 12px 12px 12px;
  display: flex;
  flex-flow: row wrap;
`;

const FooterLogo = styled.div`
  flex: 0 0 64px;
  margin: -12px 0 32px;
`;

const FooterNav = styled.nav`
  display: flex;
  flex: 2 1 auto;
  flex-wrap: wrap;
`;

const FooterNavItem = styled.div`
  padding: 0 12px 24px 0;
  font-size: 13px;
  flex: 1 0 auto;

  > h5 {
    text-transform: uppercase;
    color: var(--black-200);
    font-weight: bold;
    margin-bottom: 12px;
    line-height: calc((13 + 4) / 13);
  }
`;

const FooterCopyRight = styled.div`
  flex: 1 1 150px;
  display: flex;
  flex-direction: column;
`;

const FooterCopyRightSolcialWarrper = styled.ul`
  display: flex;

  > li + li {
    margin-left: 12px;
  }
`;

const FooterCopyRightParagraph = styled.p`
  margin-top: auto;
  margin-bottom: 24px;
  font-size: 11px;
  color: var(--black-300);
  line-height: calc((13 + 4) / 13);
`;

export default function Footer() {
  return (
    <FooterContainer>
      <FooterInner>
        <FooterLogo>
          <a href="/">
            <Logo />
          </a>
        </FooterLogo>
        <FooterNav>
          <FooterNavItem>
            <h5>stack overflow</h5>
            <ul>
              <FooterSiteLink
                link={'https://stackoverflow.com/questions'}
                text={'Questions'}
              />
              <FooterSiteLink
                link={'https://stackoverflow.com/help'}
                text={'Help'}
              />
            </ul>
          </FooterNavItem>
          <FooterNavItem>
            <h5>products</h5>
            <ul>
              <FooterSiteLink
                link={'https://stackoverflow.co/teams/'}
                text={'Teams'}
              />

              <FooterSiteLink
                link={'https://stackoverflow.co/advertising/'}
                text={'Advertising'}
              />
              <FooterSiteLink
                link={'https://stackoverflow.co/collectives/'}
                text={'Collectives'}
              />
              <FooterSiteLink
                link={'https://stackoverflow.co/talent/'}
                text={'Talent'}
              />
            </ul>
          </FooterNavItem>
          <FooterNavItem>
            <h5>company</h5>
            <ul>
              <FooterSiteLink
                link={'https://stackoverflow.co/'}
                text={'About'}
              />
              <FooterSiteLink
                link={'https://stackoverflow.co/company/press'}
                text={'Press'}
              />
              <FooterSiteLink
                link={'https://stackoverflow.co/company/work-here'}
                text={'Work Here'}
              />
              <FooterSiteLink
                link={'https://stackoverflow.com/legal/terms-of-service'}
                text={'Legal'}
              />
              <FooterSiteLink
                link={'https://stackoverflow.com/legal/privacy-policy'}
                text={'Privacy Policy'}
              />
              <FooterSiteLink
                link={'https://stackoverflow.com/legal/terms-of-service'}
                text={'Terms of Service'}
              />
              <FooterSiteLink
                link={'https://stackoverflow.co/company/contact'}
                text={'Contact Us'}
              />
              <FooterSiteLink link={'#'} text={'Cookie Settings'} />
              <FooterSiteLink
                link={'https://stackoverflow.com/legal/cookie-policy'}
                text={'Cookie Policy'}
              />
            </ul>
          </FooterNavItem>
          <FooterNavItem>
            <h5>stack exchange network</h5>
            <ul>
              <FooterSiteLink
                link={'https://stackexchange.com/sites#technology'}
                text={'Technology'}
              />
              <FooterSiteLink
                link={'https://stackexchange.com/sites#culturerecreation'}
                text={'Culture & recreation'}
              />
              <FooterSiteLink
                link={'https://stackexchange.com/sites#lifearts'}
                text={'Life & arts'}
              />
              <FooterSiteLink
                link={'https://stackexchange.com/sites#science'}
                text={'Science'}
              />
              <FooterSiteLink
                link={'https://stackexchange.com/sites#professional'}
                text={'Professional'}
              />
              <FooterSiteLink
                link={'https://stackexchange.com/sites#business'}
                text={'Busssiness'}
              />
              <li style={{ marginTop: '16px' }}></li>
              <FooterSiteLink
                link={'https://api.stackexchange.com/'}
                text={'API'}
              />
              <FooterSiteLink
                link={'https://data.stackexchange.com/'}
                text={'Data'}
              />
            </ul>
          </FooterNavItem>
        </FooterNav>
        <FooterCopyRight>
          <FooterCopyRightSolcialWarrper>
            <FooterSiteLink
              link={
                'https://stackoverflow.blog/?blb=1&_ga=2.77527509.1518137145.1686705797-1914716660.1686705790'
              }
              text={'Blog'}
              fontSize={11}
            />
            <FooterSiteLink
              link={'https://www.facebook.com/officialstackoverflow/'}
              text={'Facebook'}
              fontSize={11}
            />
            <FooterSiteLink
              link={'https://twitter.com/stackoverflow'}
              text={'Twitter'}
              fontSize={11}
            />
            <FooterSiteLink
              link={'https://www.linkedin.com/company/stack-overflow'}
              text={'LinkedIn'}
              fontSize={11}
            />
            <FooterSiteLink
              link={'https://www.instagram.com/thestackoverflow/'}
              text={'Instagram'}
              fontSize={11}
            />
          </FooterCopyRightSolcialWarrper>
          <FooterCopyRightParagraph>
            Site design / logo &copy; {new Date().getFullYear()} Stack Exchange
            Inc; user contributions licensed under&nbsp;
            <span>
              <a
                href="https://stackoverflow.com/help/licensing"
                style={{ color: 'var(--black-300)' }}
              >
                CC BY-SA
              </a>
            </span>
            .&nbsp;
            <span>rev&nbsp;2023.6.15.43499</span>
          </FooterCopyRightParagraph>
        </FooterCopyRight>
      </FooterInner>
    </FooterContainer>
  );
}
