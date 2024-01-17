import React, { useState } from 'react'
import Header from './Header'

const Login = () => {

  const [istoggle,settloggle] = useState(true);

  const totoggleclick = () => { 
      settloggle(!istoggle)
  }

  return (
    <div>
    <Header />
    <div className='absolute'>
    <img src='https://assets.nflxext.com/ffe/siteui/vlv3/df6621a3-890c-4ca0-b698-90bd5152f3d1/20a59be7-7062-4991-bca0-805e9a7f2716/IN-en-20240107-trifectadaily-perspective_alpha_website_small.jpg' alt='Bg' />
      </div>
     
      <form className='w-3/12 p-12 absolute bg-black my-36 mx-auto right-0 left-0 text-white bg-opacity-80 rounded-lg'>
        <h1 className='font-bold '>{istoggle ? "Sign In" : "Sign Up"}</h1>
        {!istoggle && (<input placeholder='Username' type='Name' className='p-4 my-4 w-full text-black bg-gray-700' />)}
        <input placeholder='Email Address' type='text' className='p-4 my-4 w-full text-black bg-gray-700' />
        <input placeholder='Password' type='password' className='p-4 my-4 w-full text-black  bg-gray-700' />
        {!istoggle && (<input placeholder='Confirm Password' type='password' className='p-4 my-4 w-full text-black bg-gray-700' />)}
        <div>
          <button className='p-4 my-6  w-full font-bold bg-red-700 rounded-lg' >{istoggle ? "Sign In":"Sign Up"}</button>
        </div>
        
        <h4 className='cursor-pointer' onClick={totoggleclick}>{istoggle ? "New to Netflix? Sign Up Now":"Already User"}</h4>
      </form>
      </div>
  )
}

export default Login