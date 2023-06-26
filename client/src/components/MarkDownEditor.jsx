import styled from 'styled-components';
import MDEditor from '@uiw/react-md-editor';

const EditorWrapper = styled.div`
  margin-bottom: 1rem;
`;

export default function MarkDownEditor(props) {
  return (
    <EditorWrapper data-color-mode={props.theme || 'light'}>
      <MDEditor
        value={props.value}
        onChange={props.onChange}
        height={props.height || 200}
      />
    </EditorWrapper>
  );
}
