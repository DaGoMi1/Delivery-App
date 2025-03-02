import React from 'react'
import HomeComponent from '../HomeComponent'
import Carousel from '../../../../common/carousel/Carousel'

// 나중에는 서버에서 받아서
const restaurants = [
  {name : "마산식당", review : 4.7, "logo" : "/public/images/마산식당.png"},
  {name : "마산식당", review : 4.7, "logo" : "/public/images/마산식당.png"},
  {name : "마산식당", review : 4.7, "logo" : "/public/images/마산식당.png"},
  {name : "마산식당", review : 4.7, "logo" : "/public/images/마산식당.png"},
  {name : "마산식당", review : 4.7, "logo" : "/public/images/마산식당.png"},
  {name : "마산식당", review : 4.7, "logo" : "/public/images/마산식당.png"}
]

const RecentOrder = () => {
  return (
    <HomeComponent>
      <HomeComponent.title>최근 주문한 음식점</HomeComponent.title>
      <HomeComponent.content>
        <Carousel>
          {restaurants?.map((restaurant)=>(
            <Carousel.item>
              <img src={restaurant.logo}/>
              <div className='flex gap-2 font-bold'>
                <p>{restaurant.name}</p>
                <p>⭐{restaurant.review}</p>
              </div>
            </Carousel.item>
          ))}
        </Carousel>
      </HomeComponent.content>
    </HomeComponent>
  )
}

export default RecentOrder