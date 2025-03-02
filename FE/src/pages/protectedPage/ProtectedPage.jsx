import React from 'react'
import { Navigate, Outlet } from 'react-router';

const ProtectedPage = () => {
  const token = localStorage.getItem("accessToken");
  return <Outlet/>
  // return token ? <Outlet/> : <Navigate to='/login' replace/>
}

export default ProtectedPage