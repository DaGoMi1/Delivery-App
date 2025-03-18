import React from 'react'

const HomeComponent = ({children}) => {
  return (
    <div>{children}</div>
  )
}

HomeComponent.title = ({children}) => {
  return <h3 className='font-bold my-4'>{children}</h3>
}

HomeComponent.content = ({children}) => {
  return <div>{children}</div>
}

export default HomeComponent