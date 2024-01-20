import React, { useEffect } from 'react'
import Header from './Header'
import useNowplayingMovies from '../hooks/useNowplayingMovies'
import Maincontainer from './Maincontainer';
import Secondarycontainer from './Secondarycontainer';

const Browser = () => {

   useNowplayingMovies();

  return (
    <div>
    <Header />
    { /*
    -moviecontainer
      - videobackground
      - video title
    - secondary container
      - movielist *n
      - cards * n
     */}
      <Maincontainer />
      <Secondarycontainer />
      

      </div>
  )
}

export default Browser