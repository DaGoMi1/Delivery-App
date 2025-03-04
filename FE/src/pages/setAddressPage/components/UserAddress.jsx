import React from 'react'
import { useUserStore } from '../../../store/useUserStore'

const UserAddress = () => {
  const addressList = useUserStore((state)=>state.user.address);
  const sortedAddressList = addressList.sort((a,b)=>b.isChecked-a.isChecked);
  
  // 나중에 클릭 시 isChecked를 변경하는 useMutation 만들어야함.
  return (
    <div className='p-4 bg-white flex flex-col gap-8'>
      {sortedAddressList.map((address)=>(
        <div className='flex items-center gap-4 cursor-pointer'>
          <div><i className={`fa-solid fa-location-dot ${address.isChecked ? "" : "text-gray-400"}`}></i></div>
          <div>
            <h1 className='text-[17px] flex items-center gap-2'>
              <p>{address.addr}</p> 
              {address.isChecked && 
                <p className='text-[13px] text-[var(--color-mainColor)]'>(현재 설정한 주소)</p>}
            </h1>
            <h2 className='text-[14px]'>{address.detailAddr}</h2>
            
            <div className=''>
              <button className='border-1 rounded-lg text-gray-600 mt-1 px-1 mr-2 cursor-pointer'>수정</button>
              <button className='border-1 rounded-lg text-gray-600 mt-1 px-1 mr-2 cursor-pointer'>삭제</button>
            </div>
          </div>
      </div>
      ))}
    </div>
  )
}

export default UserAddress