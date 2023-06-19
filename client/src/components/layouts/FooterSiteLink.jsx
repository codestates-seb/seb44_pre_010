import styled from 'styled-components';

const FooterLink = styled.a`
  font-size: ${(props) => props.fontSize + 'px' || '13px'};
  color: var(--black-300);
  display: inline-block;
  text-decoration: none;
  line-height: calc((13 + 4) / 13);
  padding: 4px 0;
`;

export default function FooterSiteLink({ link, text, fontSize }) {
  return (
    <li>
      <FooterLink
        href={link}
        target="_blank"
        rel="noreferrer"
        fontSize={fontSize}
      >
        {text}
      </FooterLink>
    </li>
  );
}
