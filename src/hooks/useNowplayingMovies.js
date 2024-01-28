import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { addNowPlayingmovies } from '../Component/Utils/movieslice';
import { API_OPTIONS } from '../Component/Utils/Constant';

const useNowplayingMovies = () => {
  const dispatch = useDispatch();

  const nowplayingmovies = useSelector(store => store.movies.nowplayingmovies);

  const getNowplayingmovie = async () => { 
   const data = await fetch('https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1', API_OPTIONS)
 
    const json = await data.json();
    //console.log(json);
   // console.log(json.results);
    dispatch(addNowPlayingmovies(json.results))

  }

  useEffect(() => {
  !nowplayingmovies &&  getNowplayingmovie();
   },[])

}

export default useNowplayingMovies