import { configureStore } from "@reduxjs/toolkit";
import userReducer from './userSlice'
import movieReducer from './movieslice'
import gptReducer from './gptSlice'
import configReducer from './appConfig'

const appStore = configureStore({
    reducer: {
        user: userReducer,
        movies: movieReducer,
        gptsearch: gptReducer,
        config:configReducer,
        

    },
})

export default appStore