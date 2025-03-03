import { useMutation } from "@tanstack/react-query";
import api from "../../utils/api";

const searchPassword = async ({phone,userId}) => {
  return await api.post('/member/find-password', {phone,userId})
}

export const useSearchPasswordQuery = () => {
  return useMutation({
    mutationFn : searchPassword,
    onSuccess : (data) => console.log(data),
    onError : (error) => console.log(error)
  })
}