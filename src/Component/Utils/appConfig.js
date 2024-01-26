import { createSlice } from "@reduxjs/toolkit";

const appConfig = createSlice({
    name: "config",
    initialState: {
        lang:"en",
    },
    reducers: {
        changeLaungage: (state, action) => { 
            state.lang = action.payload;
        }

    }
})

export const { changeLaungage } = appConfig.actions;

export default appConfig.reducer;