import React from 'react'
import laun from './Utils/laungConstant'
import { useSelector } from 'react-redux'

const GptSearchBar = () => {

    const laungkey = useSelector((store) => store.config.lang);

  return (
      <div className='pt-[10%] flex justify-center'>
          <form className='w-1/2 bg-black gird grid-cols-12'>
              <input className="p-4 m-4 w-8/12 col-span-9" type="text" placeholder={laun[laungkey].Placeholder } />
              <button className='col-span-3 m-4 py-2 px-4 w-2/12 bg-red-700 text-white rounded-lg'>{ laun[laungkey].Search}</button>
                  
             
              
      </form>
      </div>
  )
}

export default GptSearchBar