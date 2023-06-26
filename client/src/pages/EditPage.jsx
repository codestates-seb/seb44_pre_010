import React, { useState, useEffect } from 'react';
import MarkDownEditor from '../components/MarkDownEditor';
import styled from 'styled-components';

import BlueButton from '../components/common/BlueButton';

import { Link } from 'react-router-dom';

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

export default function EditPage({ title, content, qid }) {
  const [newText, setNewText] = useState('');

  const storedAccessToken = localStorage.getItem('accessToken');

  const onPostEdit = () => {
    fetch(
      `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/questions/${qid}`,
      {
        method: 'PATCH',
        headers: {
          accept: '*/*',
          Authorization: `Bearer ${storedAccessToken}`,
          'Content-Type': 'application/json',
        },
        body: {
          title: title,
          content: newText,
        },
      },
    );
  };

  useEffect(() => {
    setNewText(content);
  }, [content]);

  return (
    <div>
      <Title>Body</Title>
      <MarkDownEditor value={newText} onChange={setNewText} />
      <PostText>{content}</PostText>
      <div>
        <BlueButton onClick={onPostEdit}>Save Edits</BlueButton>
        <Cancel to={`/questions/${qid}`}>Cancel</Cancel>
      </div>
    </div>
  );
}
