import React from 'react'
import GptSearchBar from './GptSearchBar'
import GptMovieSuggestion from './GptMovieSuggestion'
import { Background } from './Utils/Constant'

const GptSearch = () => {
  return (
    <>
          <div className='absolute -z-10 '>
        <img className='object-cover  md:w-fit' src={ Background} alt='Bg' />
        </div>
        <div className='pt-[30%] md:p-0'>
      <GptSearchBar />
      <GptMovieSuggestion />
      </div>
      </>
  )
}

export default GptSearch