import styled, { css } from 'styled-components';

const Nav = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 0.25rem;
`;

const Button = styled.button`
  display: flex;
  border: none;
  padding: 0.5rem;
  border-radius: 0.188rem;
  background: hsl(210, 8%, 85%);
  color: black;
  width: 1.563rem;
  height: 1.563rem;
  justify-content: center;
  align-items: center;
  font-size: 0.8rem;

  ${(props) =>
    props.active &&
    css`
      background: #f48225;
    `}
`;
const PageBtn = styled.button`
  display: flex;
  border: none;
  padding: 0.5rem;
  border-radius: 0.188rem;
  background: ${(props) => (props.active ? '#f48225' : 'hsl(210, 8%, 85%)')};
  color: black;
  width: 1.563rem;
  height: 1.563rem;
  justify-content: center;
  align-items: center;
  font-size: 0.8rem;
`;
const LeftButtons = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.25rem;
`;

const RightButtons = styled.div`
  display: flex;
  align-items: center;
  gap: 0.25rem;
`;
// total : 전체 질문의 갯수
// limit : 페이지 당 게시물 수
// setPage :  현재 페이지의 번호 상태

function Pagination({ total, limit, setPage, page, setLimit }) {
  // Pagenation 알고리즘 -> 전체 질문의 갯수/ 페이지 당 게시물 수
  const numPages = Math.ceil(total / limit);
  const handleLimitChange = (value) => {
    setLimit(value);
  };
  return (
    <>
      <Nav>
        <LeftButtons>
          <Button onClick={() => setPage(page - 1)} disabled={page === 1}>
            Pre
          </Button>
          {Array(numPages)
            .fill()
            .map((_, i) => (
              <Button
                key={i + 1}
                active={i + 1 === page}
                onClick={() => setPage(i + 1)}
              >
                {i + 1}
              </Button>
            ))}
          <Button
            onClick={() => setPage(page + 1)}
            disabled={page === numPages}
          >
            Next
          </Button>
        </LeftButtons>

        <RightButtons>
          <PageBtn onClick={() => handleLimitChange(5)} active={limit === 5}>
            5
          </PageBtn>
          <PageBtn onClick={() => handleLimitChange(30)} active={limit === 30}>
            30
          </PageBtn>
          <PageBtn onClick={() => handleLimitChange(30)} active={limit === 50}>
            50
          </PageBtn>
        </RightButtons>
      </Nav>
    </>
  );
}

export default Pagination;
