import React from 'react'
import { IMAGE_CDN_URL } from './Utils/Constant'

const MovieCard = ({moviescard }) => {

  if (!moviescard) return null;
  return (
    <div className='w-36 md:w-48  pr-4'>
      <img alt="posterImage" src={ IMAGE_CDN_URL + moviescard} />
    </div>

  )
}

export default MovieCard