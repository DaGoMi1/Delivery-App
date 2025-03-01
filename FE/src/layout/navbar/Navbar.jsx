import React from 'react'

const Navbar = () => {
  const submitSearchTerm = (e) => {
    e.preventDefault();
  }
  return (
    <div className='p-4'>
      <div className='flex justify-between'>
        <div className='flex gap-2'>
          <p>동구 범일로 41</p>
          <button><i class="fa-regular fa-square-caret-down"></i></button>
        </div>
        <div>
          <button><i class="fa-regular fa-bell"></i></button>
          <button><i class="fa-solid fa-cart-shopping"></i></button>
        </div>
      </div>

      <div>
        <form className='relative' onSubmit={submitSearchTerm}>
          <input className='w-full border-1 rounded-xl p-1 pl-4 caret-green-600 focus:outline-green-600' type="text" />
          <i class="fa-solid fa-magnifying-glass absolute right-2 top-2"></i>
        </form>
      </div>
    </div>
  )
}

export default Navbar