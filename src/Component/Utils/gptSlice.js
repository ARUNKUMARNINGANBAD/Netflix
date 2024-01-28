import { createSlice } from "@reduxjs/toolkit";

const gptSlice = createSlice({
    name: "gpt",
    initialState: {
        sliceToggle: false,
        gptMovies: null,
        movienames: null,
        movieresults: null,
    },
    reducers: {
        toggleGptSearchView: (state) => { 
            
            state.sliceToggle = !state.sliceToggle;

        },
        addGptMovies: (state, action) => { 
            const {movienames,movieresults } = action.payload;
            state.movienames = movienames;
            state.movieresults = movieresults;


        }
    },
})

export const { toggleGptSearchView , addGptMovies} = gptSlice.actions;

export default gptSlice.reducer;