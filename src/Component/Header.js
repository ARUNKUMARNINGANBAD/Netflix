import React from 'react'
import { useNavigate } from 'react-router-dom'
import { signOut } from "firebase/auth";
import { auth } from './Utils/firebase';
import { useSelector } from 'react-redux';

const Header = () => {

  const navigate = useNavigate();

  const user = useSelector(store => store.user);
  const handleSignout = () => {

    signOut(auth).then(() => {
      // Sign-out successful.
      navigate("/");
    }).catch((error) => {
      navigate("/error");
      // An error happened.
    });

  }

  return (
    <div className='absolute w-screen px-8 py-2 bg-gradient-to-b from-black z-10 flex justify-between' >
      <img className='w-44 h-15'
        src='https://cdn.cookielaw.org/logos/dd6b162f-1a32-456a-9cfe-897231c7763c/4345ea78-053c-46d2-b11e-09adaef973dc/Netflix_Logo_PMS.png'
        alt='logo' />
      {user &&
        (<div className='flex p-4 '>
          <img className='w-12 h-12 m-2' src={user?.photoURL} alt="photos" />
     
          <button className='font-bold text-white' onClick={handleSignout}>Sign Out</button>
        </div>)
      }
      </div>
  )
}

export default Header