import RTKStore from '../../redux/store';
import NotFound from '../../pages/NotFound.jsx';
import MyPage from '../../pages/MyPage.jsx';
import Home from '../../pages/Home.jsx';
import Questions from '../../pages/Questions.jsx';
import Tags from '../../pages/Tags.jsx';
import Login from '../../pages/Login.jsx';
import SignUp from '../../pages/SignUp.jsx';
import QuestionDetail from '../../pages/QuestionDetail.jsx';
import App from '../../App';

import { Provider as RTKProvider } from 'react-redux';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import GlobalModal from '../modal/GlobalModal';

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
