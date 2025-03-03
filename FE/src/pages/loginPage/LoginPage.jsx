import React, {useState} from 'react'
import {useNavigate, Link} from 'react-router';
import { useLoginQuery } from '../../hooks/user/useLoginQuery';
import ErrorBoundary from '../../common/errorBoundary/ErrorBoundary';
const LoginPage = () => {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const {mutate:login, isError, error} = useLoginQuery();

  const submitLogin = (e) => {
    e.preventDefault();
    login({userId,password});
  }

  return (
    <div>
      <div className='text-center p-4'>
        <h1 className='text-[36px] text-[var(--color-mainColor)]'>배달의 부족</h1>
        <p className='text-[18px]'>지금 맛있는 여정을 시작해보세요!</p>
      </div>

      <form className='p-4 flex flex-col gap-4'>
        <input value={userId} onChange={(e)=>setUserId(e.target.value)} className='border-1 p-2 caret-[var(--color-mainColor)] focus:outline-[var(--color-mainColor)]' type="text" placeholder='아이디' />
        <input value={password} onChange={(e)=>setPassword(e.target.value)} className='border-1 p-2 caret-[var(--color-mainColor)] focus:outline-[var(--color-mainColor)]' type="password" placeholder='비밀번호'/>
        {isError && <ErrorBoundary error={error}/>}
        <button onClick={submitLogin} type='submit' className='bg-[var(--color-mainColor)] text-white border-1 p-2 cursor-pointer disabled:bg-gray-400' disabled={!userId || !password}>로그인</button>
        <button onClick={()=>navigate('/register')} type='button' className='bg-[var(--color-mainColor)] text-white border-1 p-2 cursor-pointer'>회원가입</button>
      </form>



      <div className='flex flex-col items-center text-gray-400 gap-4 text-center'>
        <div>아이디 또는 비밀번호를 잊으셨나요?</div>
        <div className='flex gap-4'>
          <Link to="/searchId">아이디 찾기</Link>
          <Link to="/searchPassword">비밀번호 찾기</Link>
        </div>
      </div>
    </div>
  )
}

export default LoginPage