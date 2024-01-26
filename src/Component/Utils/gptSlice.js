import { createSlice } from "@reduxjs/toolkit";

const gptSlice = createSlice({
    name: "gpt",
    initialState: {
        sliceToggle:false,
    },
    reducers: {
        toggleGptSearchView: (state) => { 
            
            state.sliceToggle = !state.sliceToggle;

        }
    },
})

export const { toggleGptSearchView} = gptSlice.actions;

export default gptSlice.reducer;