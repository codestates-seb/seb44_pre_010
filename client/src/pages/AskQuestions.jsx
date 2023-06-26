import styled from 'styled-components';
import { useState } from 'react';
import Header from '../components/layouts/Header';
import Footer from '../components/layouts/Footer';
const Maincontainer = styled.div`
  min-height: 750px;
  overflow: visible;
  background-color: transparent;
  border: 1px solid hsl(210, 8%, 85%);
  padding: 24px;
  box-sizing: border-box;
  display: block;
`;

const TitleContainer = styled.div`
  width: 100%;
  background-color: white;
  border-color: hsl(210, 8%, 90%);
  border-radius: 3px;
  border-style: solid;
  border-width: 1px;
`;

const TitlePadding = styled.div`
  padding: 24px;
  box-sizing: inherit;
  display: block;
  text-align: left;
`;

const Title = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0.5px;
  box-sizing: inherit;
  text-align: left;
`;
const TitleItems = styled.div`
  display: flex;
  flex-direction: column;
  margin: 1.5px;
  box-sizing: inherit;
  text-align: left;
`;
const TitleItem = styled.h2`
  color: hsl(210, 8%, 5%);
  text-align: left;
`;
const TitleExplain = styled.div`
  color: hsl(210, 8%, 25%);
  text-align: left;
  font-size: small;
  padding: 10px 10px 10px 1px;
`;
const InputConstainer = styled.div`
  margin: 1.5px;
  position: relative;
  display: flex;
  text-align: left;
  width: 967px;
`;
const Input = styled.input`
  margin: 1.5px;
  position: relative;
  display: flex;
  text-align: left;
  background-color: rgb(255, 255, 255);
  border-bottom-color: rgb(186, 191, 196);
  border-bottom-left-radius: 3px;
  border-bottom-right-radius: 3px;
  border-bottom-style: solid;
  border-bottom-width: 1px;
  box-sizing: border-box;
  color: rgb(12, 13, 14);
  padding-bottom: 7.8px;
  padding-left: 9.1px;
  padding-right: 9.1px;
  padding-top: 7.8px;
  text-align: start;
  width: 967px;
  border-left-color: rgb(186, 191, 196);
  border-left-style: solid;
  border-left-width: 1px;
  border-right-color: rgb(186, 191, 196);
  border-right-style: solid;
  border-right-width: 1px;
  border-top-color: rgb(186, 191, 196);
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;
  border-top-style: solid;
  border-top-width: 1px;

  ::placeholder {
    color: rgb(186, 191, 196);
  }
`;
const ReviewButton = styled.button`
  background-color: rgb(10, 149, 255);
  border-bottom-left-radius: 0.188rem;
  border-bottom-right-radius: 0.188rem;
  border-bottom-style: solid;
  border-bottom-width: 0.063rem;
  cursor: pointer;
  display: inline-block;
  font-weight: 25rem;
  line-height: 0.938rem;
  padding-bottom: 0.65rem;
  padding-left: 0.65rem;
  padding-right: 0.65rem;
  padding-top: 0.65rem;
  position: relative;
  text-align: center;
  box-shadow: rgba(255, 255, 255, 0.4) 0rem 0.063rem 0rem 0rem inset;
  box-sizing: border-box;
  text-decoration-color: rgb(255, 255, 255);
  text-decoration-line: none;
  text-decoration-style: solid;
  border-top-left-radius: 0.188rem;
  border-top-right-radius: 0.188rem;
  &:hover {
    background-color: rgb(7, 112, 192);
  }
`;
const DeleteButton = styled.button`
  background-color: white;
  color: red;
  cursor: pointer;
  display: inline-block;
  font-weight: 25rem;
  line-height: 0.938rem;
  padding-bottom: 0.65rem;
  padding-left: 0.65rem;
  padding-right: 0.65rem;
  padding-top: 0.65rem;
  position: relative;
  text-align: center;
  box-sizing: border-box;
  text-decoration-color: rgb(255, 255, 255);
  text-decoration-line: none;
  text-decoration-style: solid;
  border-top-left-radius: 0.188rem;
  border-top-right-radius: 0.188rem;
  box-shadow: rgba(255, 255, 255, 0.4) 0rem 0.063rem 0rem 0rem inset;
  &:hover {
    background-color: rgb(219, 219, 219);
  }
  margin-left: 10px;
`;
function AskQuestions() {
  return (
    <>
      <Maincontainer>
        <TitleContainer>
          <TitlePadding>
            <Title>
              <TitleItems>
                <TitleItem>Title</TitleItem>
                <TitleExplain>
                  Be specific and imagine you’re asking a question to another
                  person.
                </TitleExplain>
                <InputConstainer>
                  <Input
                    placeholder="e.g. ls there an R function for finding the index of an
                    element in a vector?"
                  ></Input>
                </InputConstainer>
              </TitleItems>
            </Title>
          </TitlePadding>
        </TitleContainer>
        <TitleContainer>
          <TitlePadding>
            <Title>
              <TitleItems>
                <TitleItem>What are the details of your problem?</TitleItem>
                <TitleExplain>
                  Introduce the problem and expand on what you put in the title.
                  Minimum 20 characters.
                </TitleExplain>
                <InputConstainer>
                  <Input placeholder="여기에 Editer 창이 들어옴"></Input>
                </InputConstainer>
              </TitleItems>
            </Title>
          </TitlePadding>
        </TitleContainer>
        <ReviewButton>Review your question</ReviewButton>
        <DeleteButton>Discard draft</DeleteButton>
      </Maincontainer>
    </>
  );
}

export default AskQuestions;
