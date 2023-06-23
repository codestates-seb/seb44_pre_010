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
    loginSuccess(state, action) {
      state.isLoggedIn = true;
      state.accessToken = action.payload.accessToken;
      state.refreshToken = action.payload.refreshToken;
    },
    logoutSuccess(state) {
      state.isLoggedIn = false;
      state.accessToken = null;
      state.refreshToken = null;
    },
  },
});

export const { loginSuccess, logoutSuccess } = loginSlice.actions;

export default loginSlice.reducer;
