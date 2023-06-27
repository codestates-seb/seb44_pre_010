import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import EditPage from './EditPage';

export default function AnswerEditPage() {
  const [answer, setAnswer] = useState({});
  const [content, setContent] = useState('');
  const { id, aid } = useParams();

  console.log(typeof aid);

  useEffect(() => {
    fetch(
      `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/questions/${id}`,
    )
      .then((res) => res.json())
      .then((data) => {
        setAnswer(
          data.data.answers.find((answer) => answer.answerId === parseInt(aid)),
        );
        setContent(answer.content);
      });
  }, [content]);

  return (
    <>
      <EditPage content={content} qid={id} aid={aid} />
    </>
  );
}
