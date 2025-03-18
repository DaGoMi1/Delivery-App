import { useMutation } from "@tanstack/react-query";
import api from "../../utils/api";
import { useNavigate } from "react-router";

const login = async({userId,password}) => {
  return await api.post('/auth/login',{userId,password})
}

export const useLoginQuery = () => {
  const navigate = useNavigate();
  return useMutation({
    mutationFn : login,
    onSuccess : (data) => {
      const accessToken = data.data.accessToken;
      const refreshToken = data.data.refreshToken;
      
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("refreshToken", refreshToken);
      navigate('/');
    },
    onError : (error) => console.log(error)
  })
}
