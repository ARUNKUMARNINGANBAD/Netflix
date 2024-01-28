import React, { useRef, useState } from 'react'
import Header from './Header'
import { checkValidatesignIn, checkValidatesignup } from './Utils/Validate';
import { createUserWithEmailAndPassword, updateProfile } from "firebase/auth";
import {  signInWithEmailAndPassword } from "firebase/auth";
import { auth } from './Utils/firebase';
import {  useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { addUser } from './Utils/userSlice';
import { Background, profile } from './Utils/Constant';

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
  displayName: name.current.value, photoURL: profile
}).then(() => {
  // Profile updated!
  const { uid, email, displayName, photoURL } = auth.currentUser;
      dispatch(addUser({ uid: uid, email: email, displayName: displayName, photoURL:photoURL }))
  //navigate('/Browser')
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
    //navigate('/Browser')
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
        <img className="" src={ Background} alt='Bg' />
      </div>
     
      <form onSubmit={(e) => e.preventDefault()} className='w-full md:w-3/12 p-12 absolute bg-black my-36 mx-auto right-0 left-0 text-white bg-opacity-80 rounded-lg'>
        <h1 className='font-bold '>{istoggle ? "Sign In" : "Sign Up"}</h1>
        {!istoggle && (<input
          ref={name}
          placeholder='Username' type='Name' className='p-4 my-4 w-full bg-gray-700' />)}
        <input
          ref={email}
          placeholder='Email Address' type='text' className='p-4 my-4 w-full bg-gray-700' />
        <input
          ref={password}
          placeholder='Password' type='password' className='p-4 my-4 w-full bg-gray-700' />
        {!istoggle && (<input ref={confirmpassword} placeholder='Confirm Password' type='password' className='p-4 my-4 w-full text-black bg-gray-700' />)}
        <div>
          <p className='text-red-700 font-bold'>{errormessage}</p>
          <button className='p-4 my-6  w-full font-bold bg-red-700 rounded-lg' onClick={handlevalidation} >{istoggle ? "Sign In" : "Sign Up"} </button>
        </div>
        
        <h4 className='cursor-pointer font-extrabold' onClick={totoggleclick} >{istoggle ? "New to Netflix? Sign Up Now":"Already User"}</h4>
      </form>
      </div>
  )
}

export default Login