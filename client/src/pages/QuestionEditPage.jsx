import React, { useEffect, useState } from 'react';
import EditPage from './EditPage';
import styled from 'styled-components';
import { useParams } from 'react-router-dom';

const Title = styled.h2`
  font-size: 0.813rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
`;

export default function QuestionEditPage() {
  const [question, setQuestion] = useState({});
  const [content, setContent] = useState('');
  const [title, setTitle] = useState('');
  const { id } = useParams();

  useEffect(() => {
    fetch(
      `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/questions/${id}`,
    )
      .then((res) => res.json())
      .then((data) => {
        setQuestion(data.data);
        setContent(data.data.content);
        setTitle(data.data.title);
      });
  }, []);

  return (
    <>
      <Title>Title</Title>
      <input
        type="text"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <EditPage title={title} content={content} qid={id} />
    </>
  );
}
