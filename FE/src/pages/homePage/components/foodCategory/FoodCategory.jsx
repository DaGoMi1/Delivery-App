import React, { useEffect, useState } from 'react'
import HomeComponent from '../HomeComponent';

// 나중에는 서버에서 음식 카테고리와 사진들 받기
const foodCategories = [
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"},
  {"name": "치킨", img: "/public/images/chicken.png"}
]

const FoodCategory = () => {
  const [isOpenAllCategory, setIsOpenAllCategory] = useState(false);

  const handleMoreView = () => {
    setIsOpenAllCategory(true);  
  }

  const filteredCategory = isOpenAllCategory 
    ? foodCategories 
    : [...foodCategories.slice(0,7),
     {"name": "더보기", tag: <button onClick={handleMoreView}><i class="fa-solid fa-circle-plus"></i></button>}];
  

  useEffect(()=>{
    setIsOpenAllCategory(false);
  },[])

  return (
    <HomeComponent>
      <HomeComponent.title>음식배달</HomeComponent.title>
      <HomeComponent.content>
        <div className='grid grid-cols-4 gap-y-2'>
          {filteredCategory?.map((category)=>(
            <div className='cursor-pointer max-w-[80px] flex flex-col items-center justify-center'>
              {category.img ? <img src={category.img}/> : category.tag}
              <p>{category.name}</p>
            </div>
          ))}
        </div>
      </HomeComponent.content>
    </HomeComponent>
  )
}

export default FoodCategory