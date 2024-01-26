import React from 'react'
import GptSearchBar from './GptSearchBar'
import GptMovieSuggestion from './GptMovieSuggestion'
import { Background } from './Utils/Constant'

const GptSearch = () => {
  return (
    <div className='text-red-500'>
          <div className='absolute -z-10'>
        <img src={ Background} alt='Bg' />
      </div>
      <GptSearchBar />
      <GptMovieSuggestion />
    </div>
  )
}

export default GptSearch