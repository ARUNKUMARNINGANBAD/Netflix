import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { onAuthStateChanged, signOut } from "firebase/auth";
import { auth } from './Utils/firebase';
import { useDispatch, useSelector } from 'react-redux';
import { addUser, removeUser } from './Utils/userSlice';
import { LOGO, SUPPORTED_LANUGUAGE } from './Utils/Constant';
import { toggleGptSearchView} from './Utils/gptSlice'
import { changeLaungage } from './Utils/appConfig';

const Header = () => {

  const navigate = useNavigate();

  const dispatch = useDispatch();

  const user = useSelector(store => store.user);

  const laungselect =useSelector((store) => store.gptsearch?.sliceToggle);

  const handleSignout = () => {

    signOut(auth).then(() => {
      // Sign-out successful.
      //navigate("/");
    }).catch((error) => {
      navigate("/error");
      // An error happened.
    });

  }

  const hanbleGptsearch = () => { 
    //console.log("clicked seradch")
    dispatch(toggleGptSearchView());
    
  }

  const handlechangelaung = (e) => { 
    dispatch(changeLaungage(e.target.value))
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
    <div className='absolute w-screen px-8 py-2 bg-gradient-to-b from-black z-10 flex justify-between bg-black sm:bg-blue-700 md:bg-green-700 flex-col md:flex-row ' >
      <img className='w-44  mx-auto md:mx-0'
        src={ LOGO}
        alt='logo' />
      {user &&
        (<div className='flex p-4 '>
        { laungselect && (
          <select className='px-4 py-2 mx-2 my-4' onChange={handlechangelaung}>

            {SUPPORTED_LANUGUAGE.map(laung => (<option key={laung.identifier} value={laung.identifier}>{laung.name}</option>))}
          </select>)}
        <button className='text-white bg-purple-500 px-4 py-2 mx-2 my-4 rounded-lg '
          onClick={hanbleGptsearch}>
          {laungselect ? "HomePage" : "GPTSearch"}</button>
          <img className='hidden md:block w-12 h-12 m-2' src={user?.photoURL} alt="photos" />
     
          <button className='font-bold text-white' onClick={handleSignout}>Sign Out</button>
        </div>)
      }
      </div>
  )
}

export default Header