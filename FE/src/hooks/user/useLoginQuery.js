import { useMutation } from "@tanstack/react-query";
import api from "../../utils/api";
import { useNavigate } from "react-router";

const login = async({email,password}) => {
  return await api.post('/auth/login',{email,password})
}

export const useLoginQuery = () => {
  const navigate = useNavigate();
  return useMutation({
    mutationFn : login,
    onSuccess : () => navigate('/home'),
    onError : (error) => console.log(error)
  })
}
