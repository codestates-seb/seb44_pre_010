import { useEffect } from 'react';
import { useParams } from 'react-router-dom';

export default function QuestionDetail() {
  const { id } = useParams();

  useEffect(() => {
    console.log(id);
  }, []);

  return <h2>Question Detail {id}번 페이지입니다.</h2>;
}
