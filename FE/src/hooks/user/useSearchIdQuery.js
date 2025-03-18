import { useMutation } from "@tanstack/react-query";
import api from "../../utils/api";

const searchId = async(phone) => {
  return await api.post('/member/find-id', {phone});
}

export const useSearchIdQuery = () => {
  return useMutation({
    mutationFn : searchId,
    onSuccess : (data) => console.log(data),
    onError : (error) => console.log(error),
    
  })
}