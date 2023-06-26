import RTKStore from '../../redux/store';
import NotFound from '../../pages/NotFound.jsx';
import MyPage from '../../pages/MyPage.jsx';
import Home from '../../pages/Home.jsx';
import Questions from '../../pages/Questions.jsx';
import Tags from '../../pages/Tags.jsx';
import Login from '../../pages/Login.jsx';
import SignUp from '../../pages/SignUp.jsx';
import Search from '../../pages/Search.jsx';
import QuestionDetail from '../../pages/QuestionDetail.jsx';
import App from '../../App';

import { Provider as RTKProvider } from 'react-redux';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import GlobalModal from '../modal/GlobalModal';
import QuestionEditPage from '../../pages/QuestionEditPage';

import DeleteProfile from '../../pages/DeleteProfile';
import EditProfile from '../../pages/EditProfile';

import AskQuestions from '../../pages/AskQuestions';

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    errorElement: <NotFound />,
    children: [
      { index: true, element: <Home /> },
      { path: 'questions', element: <Questions /> },
      { path: 'tags', element: <Tags /> },
      { path: 'mypage', element: <MyPage /> },
      { path: 'questions/:id', element: <QuestionDetail /> },
      { path: 'questions/:id/edit', element: <QuestionEditPage /> },
      { path: 'search', element: <Search /> },
      { path: 'mypage/edit', element: <EditProfile /> },
      { path: 'mypage/delete', element: <DeleteProfile /> },
      { path: 'askquestions', element: <AskQuestions /> },
    ],
  },
  { path: 'login', element: <Login /> },
  { path: 'signup', element: <SignUp /> },
]);

export default function Providers({ children }) {
  return (
    <RTKProvider store={RTKStore}>
      <RouterProvider router={router}>
        {children}
        <GlobalModal />
      </RouterProvider>
    </RTKProvider>
  );
}
