import React, {useState} from 'react'
import { Link } from 'react-router';
import { useSearchIdQuery } from '../../hooks/user/useSearchIdQuery';
import LoadingSpinner from '../../common/loadingSpinner/LoadingSpinner';
import ErrorBoundary from '../../common/errorBoundary/ErrorBoundary';

const SearchIdPage = () => {
  const [phone, setPhone] = useState("");

  const {mutate: searchId, data, isLoading, isError, error} = useSearchIdQuery();

  const submitSearchId = (e) => {
    e.preventDefault();
    searchId(phone);
  }

  if(isLoading) {
    return <LoadingSpinner/>
  }

  return (
    <div>
      <div className='text-center p-4'>
        <h1 className='text-[36px] text-[var(--color-mainColor)]'>배달의 부족</h1>
        <p className='text-[18px]'>아이디 찾기</p>
      </div>
      <form className='p-4 flex flex-col gap-4'>
        <input value={phone} onChange={(e)=>setPhone(e.target.value)} className='border-1 p-2 caret-[var(--color-mainColor)] focus:outline-[var(--color-mainColor)]' placeholder='전화번호' type="text" />
        <button onClick={submitSearchId} type='submit' className='bg-[var(--color-mainColor)] text-white border-1 p-2 cursor-pointer disabled:bg-gray-400' disabled={!name || !phone}>완료</button>
      </form>

      {isError && <ErrorBoundary error={error}/>}
      {data && <div className='bg-green-400 p-2'><p>회원님의 아이디는 {data?.userId} 입니다.</p></div>}
      <div className='flex flex-col items-center text-gray-400 gap-4 text-center'>
        <div>비밀번호를 잊으셨나요?</div>
        <div className='flex gap-4'>
          <Link to="/searchPassword">비밀번호 찾기</Link>
          <Link to="/login">로그인으로 돌아가기</Link>
        </div>
      </div>
    </div>
  )
}

export default SearchIdPage