import React, { useEffect } from 'react'
import { useDispatch } from 'react-redux';
import { addTerndingmovies } from '../Component/Utils/movieslice';
import { API_OPTIONS } from '../Component/Utils/Constant';

const useTrendingmovies = () => {
    const dispatch = useDispatch();

    const getPopularmovies = async () => { 
      
   const data = await fetch('https://api.themoviedb.org/3/movie/popular?language=en-US&page=1', API_OPTIONS)
 
    const json = await data.json();
    //console.log(json);
   // console.log(json.results);
    dispatch(addTerndingmovies(json.results))

  }

  useEffect(() => {
    getPopularmovies();
   },[])

}

export default useTrendingmovies