import React, { useEffect } from 'react'
import Header from './Header'
import useNowplayingMovies from '../hooks/useNowplayingMovies'
import Maincontainer from './Maincontainer';
import Secondarycontainer from './Secondarycontainer';
import useTrendingmovies from '../hooks/useTrendingmovies';
import GptSearch from './GptSearch';
import { useSelector } from 'react-redux'

const Browser = () => {

  const showGptSearch = useSelector((store) => store.gptsearch?.sliceToggle);
  console.log(showGptSearch)

  useNowplayingMovies();
  useTrendingmovies();

  return (
    <div>
      <Header />
      
      { 
        showGptSearch ? (<GptSearch />) :     
        (<>
          <Maincontainer />
      <Secondarycontainer />
      </>)
      }
    { /*
    -moviecontainer
      - videobackground
      - video title
    - secondary container
      - movielist *n
      - cards * n
     */}
   

      

      </div>
  )
}

export default Browser