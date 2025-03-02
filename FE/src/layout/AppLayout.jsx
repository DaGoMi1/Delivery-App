import React from 'react'
import { Outlet } from 'react-router'
import Navbar from './navbar/Navbar'
import Footer from './footer/Footer'
const AppLayout = () => {
  
  return (
    <div>
      <Navbar/>
      <Outlet/>
      <div className='fixed bottom-[0] w-full bg-white py-2'>
        <Footer/>
      </div>
    </div>
  )
}

export default AppLayout