import React, { useRef, useState } from 'react'
import Header from './Header'
import { checkValidatesignIn, checkValidatesignup } from './Utils/Validate';
import { createUserWithEmailAndPassword, updateProfile } from "firebase/auth";
import {  signInWithEmailAndPassword } from "firebase/auth";
import { auth } from './Utils/firebase';
import {  useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { addUser } from './Utils/userSlice';

const Login = () => {

  const [istoggle, settloggle] = useState(true);
  const [errormessage, seterrormessage] = useState(null);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  
  const email = useRef(null);
    
  const password = useRef(null);
  const confirmpassword = useRef(null);
  const name = useRef(null);

  const totoggleclick = () => { 
    settloggle(!istoggle)
    seterrormessage(null);
  }

  const handlevalidation = () => { 

   const message = istoggle ? checkValidatesignIn(email.current.value, password.current.value): checkValidatesignup(email.current.value, password.current.value,confirmpassword.current.value);
    //const message = checkValidate(email.current.value, password.current.value,confirmpassword.current.value);
    //console.log(message);
    seterrormessage(message);

    if (message) return;

    //sign In /SignUP
    if (!istoggle) {
     createUserWithEmailAndPassword(auth, email.current.value, password.current.value)
  .then((userCredential) => {
    // Signed up 
    const user = userCredential.user;

    updateProfile(user, {
  displayName: name.current.value, photoURL: "https://images.pexels.com/photos/236047/pexels-photo-236047.jpeg?cs=srgb&dl=clouds-cloudy-countryside-236047.jpg&fm=jpg"
}).then(() => {
  // Profile updated!
  const { uid, email, displayName, photoURL } = auth.currentUser;
      dispatch(addUser({ uid: uid, email: email, displayName: displayName, photoURL:photoURL }))
  navigate('/Browser')
  // ...
}).catch((error) => {
  // An error occurred
  seterrormessage(error.message);
});

    console.log(user);
    
    // ...
  })
  .catch((error) => {
    const errorCode = error.code;
    const errorMessage = error.message;
    seterrormessage(errorCode + "" + errorMessage);
    // ..
  });


    }
    else { 
      
     signInWithEmailAndPassword(auth, email.current.value, password.current.value)
  .then((userCredential) => {
    // Signed in 
    const user = userCredential.user;
    console.log(user);
    navigate('/Browser')
    // ...
  })
  .catch((error) => {
    const errorCode = error.code;
    const errorMessage = error.message;
     seterrormessage(errorCode + "" + errorMessage);
  });


    }
  }

  return (
    <div>
    <Header />
    <div className='absolute'>
    <img src='https://assets.nflxext.com/ffe/siteui/vlv3/df6621a3-890c-4ca0-b698-90bd5152f3d1/20a59be7-7062-4991-bca0-805e9a7f2716/IN-en-20240107-trifectadaily-perspective_alpha_website_small.jpg' alt='Bg' />
      </div>
     
      <form onSubmit={(e) => e.preventDefault()} className='w-3/12 p-12 absolute bg-black my-36 mx-auto right-0 left-0 text-white bg-opacity-80 rounded-lg'>
        <h1 className='font-bold '>{istoggle ? "Sign In" : "Sign Up"}</h1>
        {!istoggle && (<input
          ref={name}
          placeholder='Username' type='Name' className='p-4 my-4 w-full text-black bg-gray-700' />)}
        <input
          ref={email}
          placeholder='Email Address' type='text' className='p-4 my-4 w-full text-black bg-gray-700' />
        <input
          ref={password}
          placeholder='Password' type='password' className='p-4 my-4 w-full text-black  bg-gray-700' />
        {!istoggle && (<input ref={confirmpassword} placeholder='Confirm Password' type='password' className='p-4 my-4 w-full text-black bg-gray-700' />)}
        <div>
          <p className='text-red-700 font-bold'>{errormessage}</p>
          <button className='p-4 my-6  w-full font-bold bg-red-700 rounded-lg' onClick={handlevalidation} >{istoggle ? "Sign In" : "Sign Up"} </button>
        </div>
        
        <h4 className='cursor-pointer' onClick={totoggleclick} >{istoggle ? "New to Netflix? Sign Up Now":"Already User"}</h4>
      </form>
      </div>
  )
}

export default Login