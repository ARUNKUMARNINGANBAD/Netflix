export const LOGO = "https://cdn.cookielaw.org/logos/dd6b162f-1a32-456a-9cfe-897231c7763c/4345ea78-053c-46d2-b11e-09adaef973dc/Netflix_Logo_PMS.png";

export const Background = 'https://assets.nflxext.com/ffe/siteui/vlv3/df6621a3-890c-4ca0-b698-90bd5152f3d1/20a59be7-7062-4991-bca0-805e9a7f2716/IN-en-20240107-trifectadaily-perspective_alpha_website_small.jpg';

export const profile = "https://images.pexels.com/photos/236047/pexels-photo-236047.jpeg?cs=srgb&dl=clouds-cloudy-countryside-236047.jpg&fm=jpg";

export const API_OPTIONS = {
  method: 'GET',
  headers: {
    accept: 'application/json',
    Authorization: "Bearer " +  process.env.REACT_APP_API_OPTIONS_KEY,
  }
};

export const SUPPORTED_LANUGUAGE = [{ identifier: "en", name: "English" },
{ identifier: "kannada", name: "ಕನ್ನಡ" },
{ identifier: "hindi", name: "हिंदी" }];

export const IMAGE_CDN_URL = "https://image.tmdb.org/t/p/w500";

export const OPEN_API_KEY = "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"
