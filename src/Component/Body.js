import React, { useEffect } from 'react'
import Login from './Login'
import Browser from './Browser'
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import {  onAuthStateChanged } from "firebase/auth";
import { auth } from './Utils/firebase';

import { addUser, removeUser } from './Utils/userSlice';
import { useDispatch } from 'react-redux';


const Body = () => {
    const dispatch = useDispatch();
    
    const appRouter = createBrowserRouter([
        {
            path: "/",
            element:<Login />
        },
        {
            path: "/Browser",
            element:<Browser />
        }
    ])

    useEffect(() => { 
        //console.log("am in");
        onAuthStateChanged(auth, (user) => {
  if (user) {
    // User is signed in, see docs for a list of available properties
      // https://firebase.google.com/docs/reference/js/auth.user
      console.log(user);
      const { uid, email, displayName, photoURL } = user;
      dispatch(addUser({ uid: uid, email: email, displayName: displayName, photoURL:photoURL }))
    
      
    // ...
  } else {
    // User is signed out
      dispatch(removeUser());
      
  }
});

    }, []);

    return (
        
      <div>
            <RouterProvider router={ appRouter} />
            </div>
           
           
  )
}

export default Body