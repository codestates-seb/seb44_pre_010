import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';

import Providers from './components/layouts/Providers.jsx';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Providers />
  </React.StrictMode>,
);
