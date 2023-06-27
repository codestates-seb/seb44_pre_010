import styled from 'styled-components';

const MainContentWrapper = styled.div`
  max-width: 1100px;
  width: calc(100% - 164px);
  border-left: 1px solid var(--black-100);
  padding: 24px;
  box-sizing: border-box;
  margin-top: 56px;
`;

export default function MainContent({ children }) {
  return <MainContentWrapper>{children}</MainContentWrapper>;
}
