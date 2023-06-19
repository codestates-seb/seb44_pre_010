import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './index.css';
import NotFound from './pages/NotFound.jsx';
import MyPage from './pages/MyPage.jsx';
import Home from './pages/Home.jsx';
import Questions from './pages/Questions.jsx';
import Tags from './pages/Tags.jsx';
import Login from './pages/Login.jsx';
import SignUp from './pages/SignUp.jsx';

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
    ],
  },
  { path: '/login', element: <Login /> },
  { path: '/signup', element: <SignUp /> },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
);
