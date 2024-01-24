//import React from 'react'

export const checkValidatesignIn = (email,password) => {
  
   const isemail= /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(email);
    const ispassword = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/.test(password);

    if (!isemail) return "Email is not valid";
    if (!ispassword) return "Password is not valid";

    

    return null;
}

export const checkValidatesignup = (email,password,confirmpassword) => {
  
   const isemail= /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(email);
    const ispassword = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/.test(password);

    if (!isemail) return "Email is not valid";
    if (!ispassword) return "Password is not valid it should contain atleast one special, one uppercase&Lowercase, one numeric of length 8";

    if(password!==confirmpassword) return "password and confirm password should be match same";

    return null;
}

//export default checkValidate