import styled from 'styled-components';
import { ReactComponent as CheckIcon } from '../../assets/icons/check.svg';
import { useNavigate } from 'react-router-dom';

const ModalWrapper = styled.div`
  position: absolute;
  width: 100%;
  height: 100%;
`;

const ModalBackDrop = styled.div`
  background-color: rgba(0, 0, 0, 0.3);
  position: fixed;
  width: 100%;
  height: 100%;
  z-index: 9999;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const ModalMain = styled.div`
  width: 30rem;
  height: 20rem;
  background-color: var(--white);
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  gap: 3rem;
`;

const ModalIconWrapper = styled.div`
  width: 6rem;
  height: 6rem;
  border: 3px solid green;
  border-radius: 100%;
  padding: 1rem;
  opacity: 0.5;
  color: ${(props) => props.color};

  svg {
    width: 100%;
    height: 100%;
  }

  svg > path {
    stroke: green;
    stroke-width: 0.1;
    fill: green;
  }
`;

const ModalText = styled.h3`
  font-weight: bold;
  font-size: 1.2rem;
`;

const ModalButton = styled.button`
  width: 5rem;
  height: 3rem;
  border: none;
  border-radius: 3px;
  color: var(--white);
  background-color: var(--blue-500);
  cursor: pointer;
`;

export default function signupModal() {
  const navigate = useNavigate();
  const handleOkClick = () => {
    navigate('/login');
  };
  return (
    <ModalWrapper>
      <ModalBackDrop>
        <ModalMain>
          <ModalIconWrapper>
            <CheckIcon />
          </ModalIconWrapper>
          <ModalText>회원가입이 완료 되었습니다!</ModalText>
          <ModalButton onClick={handleOkClick}>OK</ModalButton>
        </ModalMain>
      </ModalBackDrop>
    </ModalWrapper>
  );
}
