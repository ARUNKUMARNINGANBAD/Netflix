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
        <div>
            <Videotitle title = {original_title} overview = {overview} />
            <VedioBackground movieID={id} />
        
    </div>
  )
}

export default Maincontainer