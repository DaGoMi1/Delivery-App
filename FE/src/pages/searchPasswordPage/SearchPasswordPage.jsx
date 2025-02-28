import React, { useState } from 'react'
import { Link } from 'react-router';
import LoadingSpinner from '../../common/loadingSpinner/LoadingSpinner';
import ErrorBoundary from '../../common/errorBoundary/ErrorBoundary';
import {useSearchPasswordQuery} from '../../hooks/user/useSearchPasswordQuery';
const SearchPasswordPage = () => {
  const [userId, setUserId] = useState("");
  const {mutate:serachPassword, data, isLoading, isError, error} = useSearchPasswordQuery();

  const submitSearchPassword = (e) => {
    e.preventDefault();
    serachPassword({userId});  
  }

  if(isLoading) {
    return <LoadingSpinner/>
  }


  return (
    <div>
       <div className='text-center p-4'>
        <h1 className='text-[36px] text-green-400'>배달의 부족</h1>
        <p className='text-[18px]'>비밀번호 찾기</p>
      </div>

      <form className='p-4 flex flex-col gap-4'>
        <input value={userId} onChange={(e)=>setUserId(e.target.value)} className='border-1 p-2 caret-green-600 focus:outline-green-600' placeholder='아이디' type="text" />
        <button onClick={submitSearchPassword} type='submit' className='bg-green-400 text-white border-1 p-2 cursor-pointer disabled:bg-gray-400' disabled={!userId} >완료</button>
      </form>

      {isError && <ErrorBoundary error={error}/>}
      {data && <div className='bg-green-400 p-2'><p>회원님의 비밀번호는 {data?.password} 입니다.</p></div>}

      <div className='flex flex-col items-center text-gray-400 gap-4 text-center'>
        <div>아이디를 잊으셨나요?</div>
        <div className='flex gap-4'>
          <Link to="/searchId">아이디 찾기</Link>
          <Link to="/login">로그인으로 돌아가기</Link>
        </div>
      </div>
    </div>
  )
}

export default SearchPasswordPage