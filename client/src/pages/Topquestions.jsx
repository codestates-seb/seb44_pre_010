import styled from 'styled-components';
const Totalbtn = styled.div`
  padding: 50px;
  display: flex;
  justify-content: flex-end;
`;
const Choicebtn = styled.button`
  align-items: center;
  display: flex;
`;

const Bountiedbtn = styled.button`
  background-color: white;
  span {
    background-color: #0a95ff;
    color: white;
  }
`;

const Askedbtn = styled.button`
  background-color: #0a95ff;
  margin-left: 200px;
  a {
    justify-content: center;
    align-items: center;
    color: white;
  }
`;

const TitleWrapper = styled.div`
  display: flex;
  justify-content: center; /* 가로 중앙 정렬 */
  align-items: center; /* 세로 중앙 정렬 */
`;
function Topquestions() {
  return (
    <div className="container">
      <TitleWrapper>
        <h1>Top Questions</h1>
        <Askedbtn>
          <a href="https://stackoverflow.com/questions/ask">Ask Questions</a>
        </Askedbtn>
      </TitleWrapper>

      <Totalbtn>
        <Choicebtn>interesting</Choicebtn>
        <Bountiedbtn>
          <span>215</span> &nbsp; Bountied
        </Bountiedbtn>
        <Choicebtn>Hot</Choicebtn>
        <Choicebtn>Week</Choicebtn>
        <Choicebtn>Month</Choicebtn>
      </Totalbtn>
      <div>
        <div>
          <div>12 votes</div>
          <div>✔ 4 answers</div>
          <div>1k views</div>
        </div>
        <div>
          <h3>질문 제목</h3>
          <div>
            2-1
            <div>
              <ui>
                <li>C++</li>
                <li>C</li>
                <li>multithreading</li>
                <li>pointers</li>
              </ui>
            </div>
            <div>
              <a href="https://stackoverflow.com/questions/ask">
                <div>
                  <img src=".././1.jpg" alt="사용자 프로필 사진"></img>
                </div>
              </a>
              <div>
                <ul>
                  <li>Cort Ammon</li>
                  <li>10.3K</li>
                  <li>answered yesterday</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Topquestions;
