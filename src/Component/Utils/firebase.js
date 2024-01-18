// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getAuth } from "firebase/auth";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCEdCgELzrwaJcZzBzwDhevSt2JZ7-ggLw",
  authDomain: "netflixgptapp-7fc4f.firebaseapp.com",
  projectId: "netflixgptapp-7fc4f",
  storageBucket: "netflixgptapp-7fc4f.appspot.com",
  messagingSenderId: "488847730",
  appId: "1:488847730:web:66762b0557444105cbc5eb",
  measurementId: "G-Z0Q6Y67S7Y"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

export const auth = getAuth();