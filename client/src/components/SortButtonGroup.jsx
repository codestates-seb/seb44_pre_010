import styled, { css } from 'styled-components';

const StyledSortButtonGroup = styled.div`
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 0.063rem;
  box-sizing: inherit;

  & > button:first-child {
    border-top-left-radius: 0.1875rem;
    border-bottom-left-radius: 0.1875rem;
  }

  & > button:last-child {
    border-top-right-radius: 0.1875rem;
    border-bottom-right-radius: 0.1875rem;
    border: 0.0625rem solid var(--black-100);
  }
`;

const StyledSortButton = styled.button`
  display: flex;
  height: 1.687rem;
  font-size: 0.75rem;
  border: 1px solid;
  text-align: center;
  align-items: center;
  padding: 0 0.6rem;
  color: var(--black-500);
  border: 0.0625rem solid var(--black-100);
  border-right: none;
  background-color: var(--white);

  ${({ active }) =>
    active &&
    css`
      background-color: var(--black-075);
    `};

  ${({ active }) =>
    !active &&
    css`
      :hover {
        background-color: var(--black-025);
      }
    `};
`;

const SortButton = ({ active, onClick, text }) => (
  <StyledSortButton active={active} onClick={onClick}>
    {text}
  </StyledSortButton>
);

const SortButtonGroup = ({ buttonData, activeOption, onClick }) => (
  <StyledSortButtonGroup>
    {buttonData.map((button) => (
      <SortButton
        key={button.id}
        active={activeOption === button.id}
        onClick={() => onClick(button.id)}
        text={button.text}
      />
    ))}
  </StyledSortButtonGroup>
);

export default SortButtonGroup;
