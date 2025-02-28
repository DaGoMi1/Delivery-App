import React from 'react'
import { Navigate, Outlet } from 'react-router';

const ProtectedPage = () => {
  const token = localStorage.getItem("token");
  return token ? <Outlet/> : <Navigate to='/login' replace/>
  // return <Outlet/>
}

export default ProtectedPage