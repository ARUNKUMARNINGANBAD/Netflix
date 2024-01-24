import { createSlice } from "@reduxjs/toolkit";

const movieSlice = createSlice({
    name: "movies",
    initialState: {
        nowPlayingMovies: null,
        trailerMovie: null,
        trendingMovie:null,
    },
    reducers: {
        addNowPlayingmovies: (state, action) => { 
            state.nowPlayingMovies = action.payload;
        },

        addMovieTrailer: (state, action) => { 
            state.trailerMovie = action.payload;
        },

        addTerndingmovies: (state, action) => { 
            state.trendingMovie = action.payload;
        }
    }
}
   
)

export const { addNowPlayingmovies,addMovieTrailer,addTerndingmovies } = movieSlice.actions;

export default movieSlice.reducer