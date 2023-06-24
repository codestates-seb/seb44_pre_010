import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  isLoggedIn: false,
  accessToken: null,
  refreshToken: null,
};

const loginSlice = createSlice({
  name: 'login',
  initialState,
  reducers: {
    login: (state, action) => {
      state.isLoggedIn = true;
      state.accessToken = action.payload.accessToken;
      state.refreshToken = action.payload.refreshToken;
      state.userId = action.payload.userId;
    },
    logout: (state) => {
      state.isLoggedIn = false;
      state.accessToken = null;
      state.refreshToken = null;
      state.userId = null;
    },
  },
});

export const { login, logout } = loginSlice.actions;
export const loginState = (state) => state.login;

export default loginSlice.reducer;
