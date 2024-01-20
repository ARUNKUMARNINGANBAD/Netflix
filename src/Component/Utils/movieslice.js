import { createSlice } from "@reduxjs/toolkit";

const movieSlice = createSlice({
    name: "movies",
    initialState: {
        nowPlayingMovies: null,
        trailerMovie:null
    },
    reducers: {
        addNowPlayingmovies: (state, action) => { 
            state.nowPlayingMovies = action.payload;
        },

        addMovieTrailer: (state, action) => { 
            state.trailerMovie = action.payload;
        }
    }
}
   
)

export const { addNowPlayingmovies,addMovieTrailer } = movieSlice.actions;

export default movieSlice.reducer