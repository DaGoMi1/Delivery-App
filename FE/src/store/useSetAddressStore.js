import {create} from 'zustand';

export const useSetAddressStore = create((set)=>(
  {
    isOpenAddressSlide : false,
    setIsOpenAddressSlide : (bool) => set(()=>({isOpenAddressSlide : bool}))
  }
))