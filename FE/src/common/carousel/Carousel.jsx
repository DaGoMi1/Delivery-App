import React from 'react'

const Carousel = ({children}) => {
  return (
    <div className='carousel rounded-box flex gap-4'>{children}</div>
  )
}

Carousel.item = ({children}) => {
  return <div className="carousel-item flex flex-col">{children}</div>
}




export default Carousel