import React from 'react'

const Videotitle = ({title,overview}) => {
    return (
      
        <div className='w-screen aspect-video  pt-28 px-12 absolute text-white bg-gradient-to-r from-black'>
            
            <h1 className='text-6xl font-bold '>{title}</h1>
            <p className='py-6 w-1/4 text-lg'>{overview}</p>
            <div>
                <button className='bg-gray-500 text-white p-4 px-16 text-xl rounded-lg m-2 bg-opacity-50 bg-white hover:opacity-80 '>▶️Play</button>
                <button className='bg-gray-500 text-white p-4 px-16 text-xl rounded-lg bg-opacity-50 hover:opacity-80'> ℹ️ More Info</button>
            </div>
    </div>
  )
}

export default Videotitle