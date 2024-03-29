import React from 'react'
import {  useSelector } from 'react-redux'
import useVideoTrailer from '../hooks/useVideoTrailer';

const VedioBackground = ({ movieID }) => {
 const trailerget =    useSelector(store =>  store.movies?.trailerMovie )
    useVideoTrailer(movieID);
  return (
      <div className='w-screen'>
          <iframe 
              className='w-screen aspect-video  '
      src={"https://www.youtube.com/embed/" + trailerget?.key + "?&autoplay=1&mute=1"}
      //src="https://www.youtube.com/embed/16zjxg1cw4U?&autoplay=1&mute=1"
     
              
          title="YouTube video player" 
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          >
          
          </iframe></div>
  )
}

export default VedioBackground