import React, { useEffect } from 'react'
import { Outlet } from 'react-router'
import Navbar from './navbar/Navbar'
import Footer from './footer/Footer'
const AppLayout = () => {

  return (
    <div>
      <Navbar/>
      <Outlet/>
      <Footer/>
    </div>
  )
}

export default AppLayout