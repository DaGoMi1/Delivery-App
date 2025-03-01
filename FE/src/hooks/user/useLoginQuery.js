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
    onSuccess : () => navigate('/home'),
    onError : (error) => console.log(error)
  })
}
