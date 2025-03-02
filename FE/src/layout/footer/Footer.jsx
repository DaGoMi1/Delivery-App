import React from 'react'
import {Link, useLocation} from 'react-router';

const Footer = () => {
  const location = useLocation();
  const {pathname} = location;

  const footerMenus = [
    {img: <i class="fa-solid fa-house"></i>, name: "홈", pathname: '/'},
    {img: <i class="fa-solid fa-heart"></i>, name: "찜", pathname: '/interest'},
    {img: <i class="fa-solid fa-rectangle-list"></i>, name: "주문내역", pathname: '/orderList'},
    {img: <i class="fa-solid fa-face-smile"></i>, name: "마이페이지", pathname: '/myPage'},
  ]
  return (
    <div className='grid grid-cols-4 shadow-2xl'>
      {footerMenus.map((menu)=>(
        <div className='text-center'>
          <Link to={menu.pathname} className={menu.pathname === pathname ? 'text-[rgb(42,193,188)]' : 'text-gray-400'}>
            <div>{menu.img}</div>
            <div>{menu.name}</div>
          </Link>
        </div>
      ))}
    </div>
  )
}

export default Footer