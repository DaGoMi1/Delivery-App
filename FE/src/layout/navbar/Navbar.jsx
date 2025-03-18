import React from 'react'
import {useNavigate, useLocation} from 'react-router';

const Navbar = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { pathname } = location;
  return (
    <div className='font-bold px-4 py-2 bg-[var(--color-mainColor)]'>
      <div className='flex justify-between'>
        <div className='flex gap-2'>
          <p>동구 범일로 41</p>
          <button
            onClick={()=>navigate('/setAddress', {state: {prevURL : pathname}})} 
            className='flex'><i class="fa-solid fa-sort-down"></i></button>
        </div>
        <div className='flex gap-2'>
          <button><i class="fa-regular fa-bell"></i></button>
          <button><i class="fa-solid fa-cart-shopping"></i></button>
        </div>
      </div>
    </div>
  )
}

export default Navbar