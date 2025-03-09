import {create} from 'zustand';

export const useUserStore = create((set)=>({
  user : { // 테스트를 위한 임시 유저정보. 나중에는 null로 바꾸기
    name : "정상인",
    phone : "01076377570",
    role : "customer",
    address : [
      {addr : "부산 동구 범일로 41", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)", isChecked: true},
      {addr : "부산 남구", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)", isChecked: false},
      {addr : "부산 서구", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)", isChecked: false},
      {addr : "부산 북구", detailAddr : "부산 동구 범일로 41 (오션브릿지 102동 1506호)", isChecked: false},
      
    ] 
  },
  setUser : (userData)=>set((state)=>{user: userData}), // getUser 쿼리에서 성공 시, 유저 정보 저장할 때 사용
  clearUser : () => set({user: null})
}))

