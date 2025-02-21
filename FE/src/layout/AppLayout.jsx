import React from 'react'
import { Outlet } from 'react-router'

const AppLayout = () => {
  return (
    <div>
      <h3>test</h3>
      <Outlet/>
      <h3>test</h3>
    </div>
  )
}

export default AppLayout