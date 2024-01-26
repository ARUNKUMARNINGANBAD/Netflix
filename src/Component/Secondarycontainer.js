import React from 'react'

import MovieList from './MovieList'
import { useSelector } from 'react-redux'

const Secondarycontainer = () => {

  const movies = useSelector(store => store?.movies);
  //console.log(movies.nowPlayingMovies);

  return (


    <div className='bg-black'>
      <div className='-mt-52 relative z-20'>
      <MovieList title={"Now Playing"} movies={movies?.nowPlayingMovies} />
      <MovieList title={"Treanding"} movies={movies?.nowPlayingMovies} />
      <MovieList title={"Popular"} movies={movies?.trendingMovie} />
      <MovieList title={"Upcoming Movies"} movies={movies?.nowPlayingMovies} />
      <MovieList title={"Horror"} movies={movies?.nowPlayingMovies} />
    </div>
      {

      /*
      movie - popular
      movie - * n
      movie - now playing
      movie - Treading
      movire -Horror
      
       */
      }
      
    </div>
  )
}

export default Secondarycontainer