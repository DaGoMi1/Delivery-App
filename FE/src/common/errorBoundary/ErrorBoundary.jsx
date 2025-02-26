import React from 'react'

const ErrorBoundary = ({error}) => {
  return (
    <div>
      <div className='text-red-600 font-bold text-center'>
        {error.message}
      </div>
    </div>
  )
}

export default ErrorBoundary