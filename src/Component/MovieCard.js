import React from 'react'
import { IMAGE_CDN_URL } from './Utils/Constant'

const MovieCard = ({moviescard }) => {
  return (
    <div className='w-48 m-3'>
      <img alt="posterImage" src={ IMAGE_CDN_URL + moviescard} />
    </div>

  )
}

export default MovieCard