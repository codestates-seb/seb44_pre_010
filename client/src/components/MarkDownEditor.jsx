import '@toast-ui/editor/dist/toastui-editor.css'; // Editor 스타일
import { Editor } from '@toast-ui/react-editor';

export default function MarkDownEditor({ content }) {
  return (
    <>
      <Editor
        initialValue={content || ' '} // 글 수정 시 사용
      />
    </>
  );
}
