import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  modalType: '',
  isOpen: false,
};

export const modalSlice = createSlice({
  name: 'modal',
  initialState,
  reducers: {
    open: (state, actions) => {
      const { modalType } = actions.payload;
      state.modalType = modalType;
      state.isOpen = true;
    },
    close: (state) => {
      state.isOpen = false;
    },
  },
});

export const { open, close } = modalSlice.actions;
export const selectModal = (state) => state.modal;
export default modalSlice.reducer;
