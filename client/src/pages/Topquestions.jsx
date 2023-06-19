import styled from 'styled-components';
const Maincontainer = styled.div`
  max-width: 1100px;
  width: calc(100% - 164px);
  background-color: white;
  border-radius: 0;
  border-left-width: 1px;
  border-right-width: 0;
  padding: var(--su24);
  box-sizing: border-box;
  &::before {
    box-sizing: border-box;
    color: rgb(35, 38, 41);
    display: table;
    line-height: 1.063rem;
    vertical-align: baseline;
    font-family: 'Segoe UI Adjusted', 'Segoe UI', 'Liberation Sans', sans-serif;
    font-size: 0.813rem;
    font-stretch: 100%;
    text-align: left;
  }
`;
const Mainbar = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: block;
  font-weight: 25rem;
  line-height: 1.063rem;
  width: 37.25;
`;
const TopQuestions = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: flex;
  line-height: 1.063rem;
  vertical-align: baseline;
  font-family: 'Segoe UI Adjusted', 'Segoe UI', 'Liberation Sans', sans-serif;
  font-size: 0.813rem;
  font-stretch: 100%;
  text-align: left;
  font-weight: 25rem;
  justify-content: center;
  align-items: center;
`;
const H1 = styled.h1`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: block;
  flex-basis: auto;
  flex-grow: 1;
  flex-shrink: 1;
  font-family: 'Segoe UI Adjusted', 'Segoe UI', 'Liberation Sans', sans-serif;
  font-size: 1.688rem;
  font-weight: 25rem;
  line-height: 2.194rem;
  margin-block-end: 1.688rem;
  margin-bottom: 1.688rem;
  text-align: left;
`;
const AQuecontainer = styled.div`
  box-sizing: border-box;
  color: rgb(35, 38, 41);
  display: block;
  line-height: 2.194rem;
  margin-left: 0.75rem;
  text-align: left;
`;
const Askquestion = styled.a`
  background-color: rgb(10, 149, 255);
  color: white;
  border-bottom-left-radius: 3px;
  border-bottom-right-radius: 3px;
  border-bottom-style: solid;
  border-bottom-width: 1px;
  cursor: pointer;
  display: inline-block;
  font-weight: 25rem;
  line-height: 0.938rem;
  padding-bottom: 10.4px;
  padding-left: 10.4px;
  padding-right: 10.4px;
  padding-top: 10.4px;
  position: relative;
  text-align: center;
  box-shadow: rgba(255, 255, 255, 0.4) 0px 1px 0px 0px inset;
  box-sizing: border-box;
  text-decoration-color: rgb(255, 255, 255);
  text-decoration-line: none;
  text-decoration-style: solid;
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;
`;
const Category = styled.div`
  display: flex;
  margin-bottom: 16px;
  vertical-align: baseline;
  align-items: center;
  box-sizing: inherit;
  text-align: left;
`;
const Categorylist = styled.div`
  box-sizing: inherit;
  margin: 0;
  padding: 0;
  border: 0;
  font: inherit;
  font-size: 100%;
  vertical-align: baseline;
  display: block;
  text-align: left;
`;
const Categorylink = styled.div`
  vertical-align: baseline;
  text-align: left;
  line-height: 0.938rem;
`;
const Categoryitem = styled.a`
  margin-right: -1px;
  z-index: 25px;
  background-color: hsl(210, 8%, 90%);
  color: hsl(210, 8%, 25%);
  margin-bottom: -1px;
  white-space: nowrap;
`;
function Topquestions() {
  return (
    <>
      <Maincontainer className="Maincontainer">
        <Mainbar>
          <TopQuestions>
            <H1> Top Questions</H1>
            <AQuecontainer>
              <Askquestion> Ask Question </Askquestion>
            </AQuecontainer>
          </TopQuestions>
          <Category>
            <Categorylist>
              <Categorylink>
                <Categoryitem>Interesting</Categoryitem>
                <Categoryitem>226 Bountied</Categoryitem>
                <Categoryitem>Hot</Categoryitem>
                <Categoryitem>Week</Categoryitem>
                <Categoryitem>Month</Categoryitem>
              </Categorylink>
            </Categorylist>
          </Category>
        </Mainbar>
      </Maincontainer>
    </>
  );
}

export default Topquestions;
