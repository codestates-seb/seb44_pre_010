import { useEffect } from 'react';
import { useOutletContext } from 'react-router-dom';

export default function Tags() {
  const { onHandleSelect } = useOutletContext();

  useEffect(() => {
    onHandleSelect(2);
  }, []);

  return <h2>Tags 페이지 입니다.</h2>;
}
