import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Button = styled(Link)`
  min-width: 100px;
  max-height: 36px;
  background-color: rgb(10, 149, 255);
  color: white;
  border-radius: 0.188rem;
  cursor: pointer;
  display: inline-block;
  font-weight: 25rem;
  font-size: 0.813rem;
  line-height: 0.938rem;
  padding: 0.65rem;
  position: relative;
  box-shadow: rgba(255, 255, 255, 0.4) 0rem 0.063rem 0rem 0rem inset;
  box-sizing: border-box;
  white-space: nowrap;

  &:hover {
    background-color: rgb(7, 112, 192);
  }
`;

export default function BlueButton({ children, onClick }) {
  return <Button onClick={onClick}>{children}</Button>;
}
