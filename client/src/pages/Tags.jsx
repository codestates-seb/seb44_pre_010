import { useEffect } from 'react';
import { useOutletContext } from 'react-router-dom';
import styled from 'styled-components';

const TagsContainer = styled.div`
  width: 100%;
  margin: 0 0.72rem;
`;

const Text = styled.div`
  & > h1 {
    margin: 0.72rem 0;
    display: flex;
    font-size: 2.125rem;
    color: var(--black-800);
  }

  & > div {
    font-size: 1rem;
    margin: 2rem 0;
    color: var(--black-800);
    vertical-align: inherit;
    max-width: 620px;
    line-height: 1.3rem;
  }
`;

const TagsList = styled.div`
  display: flex;
  flex-wrap: wrap;
`;

const TagCard = styled.div`
  display: flex;
  flex-direction: column;
`;

const TagButton = styled.div``;

export default function Tags() {
  const { onHandleSelect } = useOutletContext();

  useEffect(() => {
    onHandleSelect(2);
  }, []);

  return (
    <TagsContainer>
      <Text>
        <h1>Tags</h1>
        <div>
          A tag is a keyword or label that categorizes your question with other,
          similar questions. Using the right tags makes it easier for others to
          find and answer your question.
        </div>
      </Text>
      <TagsList>
        <TagCard>
          <div id="button">
            <div id="row">
              <span id="questions" />
              <span id="today" />
            </div>
          </div>
        </TagCard>
      </TagsList>
    </TagsContainer>
  );
}
