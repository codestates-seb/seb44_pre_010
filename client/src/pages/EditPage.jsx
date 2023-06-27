import React, { useState, useEffect } from 'react';
import MarkDownEditor from '../components/MarkDownEditor';
import styled from 'styled-components';

import BlueButton from '../components/common/BlueButton';

import { Link, useNavigate } from 'react-router-dom';

const Title = styled.h2`
  font-size: 0.813rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
`;

const PostText = styled.div`
  width: 100%;
  line-height: 1.5;
  overflow-wrap: break-word;
  margin-bottom: 1rem;
`;

const Cancel = styled(Link)`
  margin-left: 1rem;
  color: var(--blue);
`;

export default function EditPage({ title, content, qid, aid }) {
  const [newText, setNewText] = useState('');
  const navigate = useNavigate();

  const storedAccessToken = localStorage.getItem('accessToken');

  const onPostEdit = () => {
    const url = title
      ? `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/questions/${qid}`
      : `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/questions/${qid}/answers/${aid}`;

    fetch(url, {
      method: 'PATCH',
      headers: {
        accept: '*/*',
        Authorization: `Bearer ${storedAccessToken}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        title: title,
        content: newText,
      }),
    })
      .then((res) => {
        if (res.status === 201) {
          navigate(`/questions/${qid}`);
        } else if (res.status >= 400) {
          new Error(
            `[ERROR] error is occured! th status code is ${res.status}`,
          );
        }
      })
      .catch((e) => console.error(e));
  };

  useEffect(() => {
    setNewText(content);
  }, [content]);

  return (
    <div>
      {title ? <Title>Body</Title> : <Title>Answer</Title>}
      <MarkDownEditor value={newText} onChange={setNewText} />
      <PostText>{content}</PostText>
      <div>
        <BlueButton onClick={onPostEdit}>Save Edits</BlueButton>
        <Cancel to={`/questions/${qid}`}>Cancel</Cancel>
      </div>
    </div>
  );
}
