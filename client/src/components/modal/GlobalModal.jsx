import { useSelector } from 'react-redux';
import { ReactComponent as CheckIcon } from '../../assets/icons/check.svg';
import { ReactComponent as Exclamation } from '../../assets/icons/exclamation.svg';
import { selectModal } from '../../redux/reducers/modalSlice';
import Modal from './Modal';
import { icon } from '@fortawesome/fontawesome-svg-core';

const modals = [
  {
    type: 'success',
    component: (
      <Modal
        icon={<CheckIcon />}
        text={'회원가입이 성공적으로 완료되었습니다!'}
        color={'green'}
      />
    ),
  },
  {
    type: 'fail',
    component: <Modal icon={<Exclamation />} text={'실패!'} color={'red'} />,
  },
  {
    type: 'deleteUserSuccess',
    component: (
      <Modal
        icon={<CheckIcon />}
        text={'회원탈퇴가 성공적으로 완료되었습니다!'}
        color={'green'}
      />
    ),
  },
  {
    type: 'deleteUserfail',
    component: (
      <Modal
        icon={<Exclamation />}
        text={'회원탈퇴에 실패하였습니다.'}
        color={'red'}
      />
    ),
  },
];

export default function GlobalModal() {
  const { modalType, isOpen } = useSelector(selectModal);

  if (!isOpen) return;

  const renderModal = () => {
    const findModal = modals.find((modal) => {
      return modal.type === modalType;
    });

    return findModal.component;
  };

  return <>{renderModal()}</>;
}
