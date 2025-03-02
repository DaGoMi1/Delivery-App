import React from 'react'
import FoodCategory from './components/foodCategory/FoodCategory';
import LocalRestaurant from './components/localRestaurant/LocalRestaurant';
import RecentOrder from './components/recentOrder/RecentOrder';

const HomePage = () => {
  const submitSearchTerm = (e) => {
    e.preventDefault();
  }
  return (
    <div>
      <div className='sticky top-0 z-10 p-4 bg-[rgb(42,193,188)]'>
        <form className='relative' onSubmit={submitSearchTerm}>
          <input className='w-full  border-1 rounded-xl p-1 pl-4 caret-green-600 focus:outline-green-600 bg-white' type="text" placeholder='찾는 맛집 이름이 뭐예요?'/>
          <i class="fa-solid fa-magnifying-glass absolute right-2 top-2 text-[rgb(42,193,188)]"></i>
        </form>
      </div>
      
      <div className='p-4 pb-[80px]'>
        <FoodCategory/>
        <LocalRestaurant/>
        <RecentOrder/>
      </div>
    </div>
  )
}

export default HomePage