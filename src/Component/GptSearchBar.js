import React, { useRef } from 'react'
import laun from './Utils/laungConstant'
import { useDispatch, useSelector } from 'react-redux'
import openai from './Utils/openAI';
import { API_OPTIONS } from './Utils/Constant';
import { addGptMovies } from './Utils/gptSlice';

const GptSearchBar = () => {

    const laungkey = useSelector((store) => store.config.lang);

    const dispatch = useDispatch();

    const search = useRef(null);

    const searchMovieTmdb = async (movie) => { 
     const data = await  fetch("https://api.themoviedb.org/3/search/movie?query="+movie+"&include_adult=false&language=en-US&page=1", API_OPTIONS)
    
        const json = await data.json();

        return json.results;
    }

    const handleGptSerach = async () => { 
        console.log(search.current.value);

        const gptQuery = "Act as movie recommandion system give top 5 movies of only give the result of movies not needed any other sentenaces" + search.current.value + "give result as only  of movie names it should not contain any other text and movie name should be comma seperated  example:-  movie1 ,movie2,movie3,movie4,movie5";
        //Make a API call to openAI and get results
          const getResult = await openai.chat.completions.create({
    messages: [{ role: 'user', content: gptQuery }],
              model: 'gpt-3.5-turbo',
    
          });
        
       // if (!getResult.choices) return;
        
       // console.log(getResult.choices?.[0]?.message?.content);
      const gptmovie=   getResult.choices?.[0]?.message?.content;
        const gptMovies = getResult.choices?.[0]?.message?.content.split(",");
        //console.log(gptMovies);
        const promisearray = gptMovies.map(movie => searchMovieTmdb(movie));

        const tmdbresult = await Promise.all(promisearray);
        
        //console.log(tmdbresult);

        dispatch(addGptMovies({movienames:gptMovies, movieresults:tmdbresult}));
        
    }

  return (
      <div className='pt-[20%] flex justify-center'>
          <form className='w-full md:w-1/2 bg-black gird grid-cols-12' onSubmit={e=>e.preventDefault()}>
              <input
              ref={search}    className="p-4 m-4 w-8/12 col-span-9" type="text" placeholder={laun[laungkey].Placeholder} />
              <button
                  
                  className='col-span-3 m-4 py-2 px-4 w-2/12 bg-red-700 text-white rounded-lg'
              onClick={handleGptSerach}
              >{laun[laungkey].Search}</button>
                  
             
              
      </form>
      </div>
  )
}

export default GptSearchBar