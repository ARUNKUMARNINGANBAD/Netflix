import React from 'react'
import { useSelector } from 'react-redux'
import VedioBackground from './VedioBackground'
import Videotitle from './Videotitle'

const Maincontainer = () => {
    const movies = useSelector(store => store.movies?.nowPlayingMovies)
    
    if (!movies) return;

    const mainMovie = movies[0];
    
   // console.log(mainMovie);

    const {original_title, overview,id } = mainMovie;

  return (
    <div className='pt-[30%] bg-black md:pt-0'>
            <Videotitle title = {original_title} overview = {overview} />
            <VedioBackground movieID={id} />
        
    </div>
  )
}

export default Maincontainer