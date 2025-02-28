import { useMutation } from "@tanstack/react-query";
import api from "../../utils/api";

const searchId = async({name, phone}) => {
  return await api.post('/', {name,phone});
}

export const useSearchIdQuery = () => {
  return useMutation({
    mutationFn : searchId,
    onSuccess : (data) => console.log(data),
    onError : (error) => console.log(error),
    
  })
}