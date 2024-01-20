import React, { useEffect } from 'react'
import { useDispatch } from 'react-redux';
import { addMovieTrailer } from '../Component/Utils/movieslice';
import { API_OPTIONS } from '../Component/Utils/Constant';

const useVideoTrailer = (movieID) => {
    const dispatch = useDispatch();
    

    //const [trailerkey,settrailerkey] = useState(null);
    const getMovieVideos = async () => {
        //const trailerUrl = "https://api.themoviedb.org/3/movie/" + movieID
        const data = await fetch("https://api.themoviedb.org/3/movie/"+ movieID+"/videos?language=en-US", API_OPTIONS)
        
        const json = await data.json();
       // console.log(json)

        const filtertrailer = json.results.filter((video) => video.type === "Trailer")

        const trailer = filtertrailer===0 ? filtertrailer[0]:json.results[0];
        //console.log(trailer);
        dispatch(addMovieTrailer(trailer));
        //settrailerkey(trailer.key);
    }

    useEffect(() => { getMovieVideos()},[])
}

export default useVideoTrailer