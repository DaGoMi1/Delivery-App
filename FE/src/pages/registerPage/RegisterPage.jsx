import React, { useState } from 'react'
import { useNavigate } from 'react-router'
import { useRegisterUserQuery } from '../../hooks/user/useRegisterUserQuery';
import ErrorBoundary from '../../common/errorBoundary/ErrorBoundary';

const RegisterPage = () => {
  const navigate = useNavigate();
  const {mutate:registerUser, isError, isLoading, error} = useRegisterUserQuery();
  const [userForm, setUserForm] = useState({
    userId : "",
    password : "",
    password2 : "",
    name : "",
    email : "",
    phone : "",
  })

  const submitUserForm = (e) => {
    e.preventDefault();
    registerUser({userForm});
  }

  return (
    <div>
      <div className='text-center p-4'>
        <h1 className='text-[36px] text-green-400'>배달의 부족</h1>
        <p className='text-[18px]'>회원가입</p>
      </div>

      <form className='p-4 flex flex-col gap-4'>
        <input value={userForm.userId} onChange={(e)=>setUserForm({...userForm, userId: e.target.value})} className='border-1 p-2 caret-green-600 focus:outline-green-600' type="text" placeholder='아이디' />
        <input value={userForm.password} onChange={(e)=>setUserForm({...userForm, password: e.target.value})} className='border-1 p-2 caret-green-600 focus:outline-green-600' type="password" placeholder='비밀번호'/>
        <input value={userForm.password2} onChange={(e)=>setUserForm({...userForm, password2: e.target.value})} className='border-1 p-2 caret-green-600 focus:outline-green-600' type="password" placeholder='비밀번호 확인'/>
        <input value={userForm.name} onChange={(e)=>setUserForm({...userForm, name: e.target.value})} className='border-1 p-2 caret-green-600 focus:outline-green-600' type="text" placeholder='이름'/>
        <input value={userForm.email} onChange={(e)=>setUserForm({...userForm, email: e.target.value})} className='border-1 p-2 caret-green-600 focus:outline-green-600' type="text" placeholder='이메일'/>
        <input value={userForm.phone} onChange={(e)=>setUserForm({...userForm, phone: e.target.value})} className='border-1 p-2 caret-green-600 focus:outline-green-600' type="text" placeholder="전화번호 ('-없이 숫자만 입력')"/>
        {isError && <ErrorBoundary error={error} />}
        <button onClick={submitUserForm} type='submit' className='bg-green-400 text-white border-1 p-2 cursor-pointer disabled:bg-gray-400' disabled={Object.values(userForm).some((value)=> value === "")}>완료</button>
        <button onClick={()=>navigate('/login')} type='button' className='bg-green-400 text-white border-1 p-2 cursor-pointer'>로그인으로 돌아가기</button>
      </form>
    </div>
  )
}

export default RegisterPage