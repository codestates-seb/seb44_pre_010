import styled, { css } from 'styled-components';

const Nav = styled.nav`
  display: flex;
  justify-content: left;
  align-items: center;
  gap: 4px;
`;

const Button = styled.button`
  display: flex;
  border: none;
  padding: 8px;
  border-radius: 3px;
  background: hsl(210, 8%, 85%);
  color: black;
  width: 25px;
  height: 25px;
  justify-content: center;
  align-items: center;
  font-size: 0%.5;

  ${(props) =>
    props.active &&
    css`
      background: #f48225;
    `}
`;
// total : 전체 질문의 갯수
// limit : 페이지 당 게시물 수
// setPage :  현재 페이지의 번호 상태
function Pagination({ total, limit, setPage, page }) {
  // Pagenation 알고리즘 -> 전체 질문의 갯수/ 페이지 당 게시물 수
  const numPages = Math.ceil(total / limit);

  return (
    <>
      <Nav>
        <Button onClick={() => setPage(page - 1)} disabled={page === 1}>
          Pre
        </Button>
        {Array(numPages)
          .fill() // 렌더링 후 상태를 변경하기 위해서 사용
          .map((_, i) => (
            <Button
              key={i + 1}
              active={i + 1 === page}
              onClick={() => setPage(i + 1)}
            >
              {i + 1}
            </Button>
          ))}
        <Button onClick={() => setPage(page + 1)} disabled={page === numPages}>
          Next
        </Button>
      </Nav>
    </>
  );
}

export default Pagination;
