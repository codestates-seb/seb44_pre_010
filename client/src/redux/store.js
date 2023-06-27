import { configureStore } from '@reduxjs/toolkit';
import modalReducer from './reducers/modalSlice';
import loginReducer from './reducers/loginSlice';

export default configureStore({
  reducer: {
    modal: modalReducer,
    login: loginReducer,
  },
});
