import {useMutation} from '@tanstack/react-query';
import api from '../utils/api';
import { useNavigate } from 'react-router';

const registerUser = async({userForm}) => {
  return await api.post('/member/register', userForm);
}

export const useRegisterUserQuery = () => {
  const navigate = useNavigate();
  return useMutation({
    mutationFn : registerUser,
    onSuccess : () => navigate('/login'),
    onError : (error) => {
      console.log(error);
    }
  })
}