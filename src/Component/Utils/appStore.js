import { configureStore } from "@reduxjs/toolkit";
import userReducer from './userSlice'
import movieReducer from './movieslice'

const appStore = configureStore({
    reducer: {
        user: userReducer,
        movies : movieReducer,

    },
})

export default appStore