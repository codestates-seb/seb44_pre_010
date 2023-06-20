import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

export default function Home() {
  const [questions, setQuestions] = useState([]);

  useEffect(() => {
    const getAllQuestions = async () => {
      const response = await fetch('/data/questions.json');
      const jsonData = await response.json();

      return jsonData;
    };

    getAllQuestions().then((data) => {
      setQuestions(data.data);
    });
  }, []);

  return (
    <>
      <h2>Top Questions</h2>
      <ul>
        {questions.map((question) => (
          <li key={question.questionId}>
            <Link to={`questions/${question.questionId}`}>
              {question.title}
            </Link>
          </li>
        ))}
      </ul>
    </>
  );
}
