import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import styled from 'styled-components';
import EditPage from './EditPage';

const Title = styled.h2`
  font-size: 0.813rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
`;

export default function AnswerEditPage() {
  const [question, setQuestion] = useState({});
  const [content, setContent] = useState('');
  const [title, setTitle] = useState('');
  const { id, aid } = useParams();

  useEffect(() => {
    fetch(
      `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/questions/${id}/answers/${aid}`,
    )
      .then((res) => res.json())
      .then((data) => {
        // setQuestion(data.data);
        // setContent(data.data.content);
        // setTitle(data.data.title);
        console.log(data.data);
      });
  }, []);

  return (
    <>
      <Title>Answer</Title>
      <EditPage title={title} content={content} qid={id} />
    </>
  );
}
