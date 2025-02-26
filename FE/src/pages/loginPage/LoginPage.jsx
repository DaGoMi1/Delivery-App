import React, {useState} from 'react'
import {useNavigate} from 'react-router';
import { useLoginQuery } from '../../hooks/useLoginQuery';
import ErrorBoundary from '../../common/errorBoundary/ErrorBoundary';
const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const {mutate:login, isError, error} = useLoginQuery();

  const submitLogin = (e) => {
    e.preventDefault();
    login({email,password});
  }

  return (
    <div>
      <div className='text-center p-4'>
        <h1 className='text-[36px] text-green-400'>배달의 부족</h1>
        <p className='text-[18px]'>지금 맛있는 여정을 시작해보세요!</p>
      </div>

      <form className='p-4 flex flex-col gap-4'>
        <input value={email} onChange={(e)=>setEmail(e.target.value)} className='border-1 p-2 caret-green-600 focus:outline-green-600' type="text" placeholder='이메일 아이디' />
        <input value={password} onChange={(e)=>setPassword(e.target.value)} className='border-1 p-2 caret-green-600 focus:outline-green-600' type="password" placeholder='비밀번호'/>
        {isError && <ErrorBoundary error={error}/>}
        <button onClick={submitLogin} type='submit' className='bg-green-400 text-white border-1 p-2 cursor-pointer disabled:bg-gray-400' disabled={!email || !password}>로그인</button>
        <button onClick={()=>navigate('/register')} type='button' className='bg-green-400 text-white border-1 p-2 cursor-pointer'>회원가입</button>
      </form>



      <div className='flex flex-col items-center text-gray-400 gap-4 text-center'>
        <div>아이디 또는 비밀번호를 잊으셨나요?</div>
        <div className='flex gap-4'>
          <a href="">아이디 찾기</a>
          <a href="">비밀번호 찾기</a>
        </div>
      </div>
    </div>
  )
}

export default LoginPage