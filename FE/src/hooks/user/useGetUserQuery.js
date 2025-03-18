import {useQuery} from '@tanstack/react-query';
import api from '../../utils/api';
import { useUserStore } from '../../store/useUserStore';

const getUser = async() => {
  const accessToken = localStorage.getItem("accessToken");
  return await api.post('/member', {accessToken});
}

export const useGetUserQuery = () => {
  const setUser = useUserStore((state)=>state.setUser);
  return useQuery({
    queryKey : ["getUser"],
    queryFn : getUser,
    onSucees : (data) => {
      setUser(data);
    },
  })
}