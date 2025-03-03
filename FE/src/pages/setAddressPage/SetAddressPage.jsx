import React from 'react'
import { useLocation, useNavigate } from 'react-router'

// 나중에는 서버에서 받아오기
const addressList = [
  {addr : "부산 동구 범일로 41", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)"},
  {addr : "부산 동구 범일로 41", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)"},
  {addr : "부산 동구 범일로 41", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)"},
  {addr : "부산 동구 범일로 41", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)"},
  {addr : "부산 동구 범일로 41", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)"},
  {addr : "부산 동구 범일로 41", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)"},
  {addr : "부산 동구 범일로 41", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)"},
  {addr : "부산 동구 범일로 41", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)"}
]
 
const SetAddressPage = () => {  
  const location = useLocation();
  const navigate = useNavigate();
  return (
    <div className='font-bold bg-gray-300'>
      <div className='p-4 flex flex-col gap-4 bg-white mb-4'>
        <div className='flex justify-between'>
          <button onClick={()=> navigate(location.state.prevURL)}><i class="fa-solid fa-arrow-left"></i></button>
          <h1>주소 설정</h1>
          <button>편집</button>
        </div>

        <form className='relative'>
          <input className='w-full rounded-xl p-1 pl-8 caret-[var(--color-mainColor)] focus:outline-[var(--color-mainColor)] bg-gray-300 focus:bg-white' type="text" placeholder='지번, 도로명, 건물명으로 검색'/>
          <i class="fa-solid fa-magnifying-glass absolute left-2 top-2 text-[rgb(42,193,188)]"></i>
        </form>
        
        <div>
          <button className='w-full border-1 border-gray-400 rounded-sm p-1'><i class="fa-solid fa-location-crosshairs"></i>  현재 위치로 찾기</button>
        </div>

        <div>
          <button><i class="fa-solid fa-house"></i> 우리집 추가</button>
        </div>
      </div>

      <div>
        <div className='p-4 bg-white flex flex-col gap-8'>
          {addressList?.map((address)=>(
            <div className='flex items-center gap-4'>
              <div><i class="fa-solid fa-location-dot"></i></div>
              <div>
                <h1 className='text-[17px]'>{address.addr}</h1>
                <h2 className='text-[14px]'>{address.detailAddr}</h2>
              </div>
            </div>
          ))}
        </div>
      </div>

    </div>
  )
}

export default SetAddressPage