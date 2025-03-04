import React, {useState} from 'react'
import { useLocation, useNavigate } from 'react-router'
import UserAddress from './components/UserAddress';
 
const SetAddressPage = () => {
  const [searchAddress, setSearchAddress] = useState("");
  const location = useLocation();
  const navigate = useNavigate();

  const handleCurrentLocation = () => {
    navigator.geolocation.getCurrentPosition((position)=>{
      const {latitude, longitude} = position.coords;
      console.log(latitude,longitude);
      
    })
  }

  return (
    <div className='font-bold bg-gray-300'>
      <div className='p-4 flex flex-col gap-4 bg-white mb-4'>
        <div className='flex justify-center relative'>
          <button className='absolute left-0 top-0' onClick={()=> navigate(location.state.prevURL)}><i class="fa-solid fa-arrow-left"></i></button>
          <h1>주소 설정</h1>
        </div>

        <form className='relative'>
          <input value={searchAddress} onChange={(e)=>setSearchAddress(e.target.value)} className='w-full rounded-xl p-1 pl-8 caret-[var(--color-mainColor)] focus:outline-[var(--color-mainColor)] bg-gray-300 focus:bg-white' type="text" placeholder='지번, 도로명, 건물명으로 검색'/>
          <i class="fa-solid fa-magnifying-glass absolute left-2 top-2 text-[var(--color-mainColor)]"></i>
        </form>
        
        <div>
          <button onClick={handleCurrentLocation} className='w-full border-1 border-gray-400 rounded-sm p-1'><i class="fa-solid fa-location-crosshairs"></i>  현재 위치로 찾기</button>
        </div>
      </div>

      <div>
        <UserAddress/>
      </div>

    </div>
  )
}

export default SetAddressPage