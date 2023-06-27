import React from 'react';
import styled from 'styled-components';

import { ReactComponent as LoadingIcon } from '../assets/icons/spinner.svg';

const LoadingWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default function Loading() {
  return (
    <LoadingWrapper>
      <LoadingIcon />
    </LoadingWrapper>
  );
}
