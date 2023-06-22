import styled from 'styled-components';
import { close } from '../../redux/reducers/modalSlice';
import { useDispatch } from 'react-redux';

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
  width: 3rem;
  height: 3rem;
  border: 3px solid ${(props) => props.color};
  border-radius: 100%;
  padding: 1rem;
  opacity: 0.5;
  color: ${(props) => props.color};

  svg {
    width: 100%;
    height: 100%;
  }

  svg > path {
    stroke: ${(props) => props.color};
    stroke-width: 0.1;
    fill: ${(props) => props.color};
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

export default function Modal({ icon, text, color }) {
  const dispatch = useDispatch();
  const closeModal = () => {
    dispatch(
      close({
        isOpen: false,
      }),
    );
  };

  return (
    <ModalWrapper>
      <ModalBackDrop onClick={closeModal}>
        <ModalMain onClick={(e) => e.stopPropagation()}>
          <ModalIconWrapper color={color}>{icon}</ModalIconWrapper>
          <ModalText>{text}</ModalText>
          <ModalButton onClick={closeModal}>OK</ModalButton>
        </ModalMain>
      </ModalBackDrop>
    </ModalWrapper>
  );
}
