import React from 'react'
import { Outlet } from 'react-router'
import Navbar from './navbar/Navbar'
import Footer from './footer/Footer'
const AppLayout = () => {
  
  return (
    <div>
      <Navbar/>
      <div className='min-h-[595px]'>
        <Outlet/>
      </div>
      <div className='fixed bottom-[0] w-[400px] bg-white py-2 z-[10]'>
        <Footer/>
      </div>
    </div>
  )
}

export default AppLayout