import React from 'react'
import { useSelector } from 'react-redux'
import MovieList from './MovieList';

const GptMovieSuggestion = () => {

  const { movienames, movieresults } = useSelector((store) => store.gptsearch)
  console.log(movienames);
  if (!movienames) return null;
  return (
    <div className='p-4 m-4 bg-black text-white bg-opacity-80'>
    <div>
      { 
          movienames.map((moviename, index) => (

            <MovieList key={moviename} title={moviename} movies={movieresults[index]} />

          ))
      }
    </div>
    </div>
  )
}

export default GptMovieSuggestion