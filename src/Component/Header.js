import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { onAuthStateChanged, signOut } from "firebase/auth";
import { auth } from './Utils/firebase';
import { useDispatch, useSelector } from 'react-redux';
import { addUser, removeUser } from './Utils/userSlice';
import { LOGO } from './Utils/Constant';

const Header = () => {

  const navigate = useNavigate();

  const dispatch = useDispatch();

  const user = useSelector(store => store.user);
  const handleSignout = () => {

    signOut(auth).then(() => {
      // Sign-out successful.
      //navigate("/");
    }).catch((error) => {
      navigate("/error");
      // An error happened.
    });

  }

      useEffect(() => { 
        //console.log("am in");
      const unsubscribe =  onAuthStateChanged(auth, (user) => {
  if (user) {

      //console.log(user);
      const { uid, email, displayName, photoURL } = user;
      dispatch(addUser({ uid: uid, email: email, displayName: displayName, photoURL:photoURL }))
     navigate("/browser")
  } else {
    // User is signed out
    dispatch(removeUser());
    navigate("/")
      
  }
});

        return () => unsubscribe();
    }, []);

  return (
    <div className='absolute w-screen px-8 py-2 bg-gradient-to-b from-black z-10 flex justify-between' >
      <img className='w-44 h-15'
        src={ LOGO}
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