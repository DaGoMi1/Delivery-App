import { useState } from 'react'
import './App.css'
import AppLayout from './layout/AppLayout'
import { Route,Routes } from 'react-router'
import LoginPage from './pages/loginPage/LoginPage';
import RegisterPage from './pages/registerPage/RegisterPage';
import StoreDetailPage from './pages/storeDetailPage/StoreDetailPage';
import CartPage from './pages/cartPage/CartPage';
import HomePage from './pages/homePage/HomePage';
import InterestPage from './pages/interestPage/InterestPage';
import OrderListPage from './pages/orderListPage/OrderListPage'
import MyPage from './pages/myPage/MyPage';
import StorePage from './pages/storePage/StorePage';
import ProtectedPage from './pages/protectedPage/ProtectedPage';
import SearchIdPage from './pages/searchIdPage/SearchIdPage';
import SearchPasswordPage from './pages/searchPasswordPage/SearchPasswordPage';
function App() {
  
  return (
    <>
      <Routes>
        <Route path='/login' element={<LoginPage/>}/>
        <Route path='/register' element={<RegisterPage/>}/>
        <Route path='/searchId' element={<SearchIdPage/>}/>
        <Route path='/searchPassword' element={<SearchPasswordPage/>}/>
        <Route element={<ProtectedPage/>}>
          <Route path='/storePage/:id' element={<StoreDetailPage/>}/>
          <Route path='/cartPage' element={<CartPage/>}/>
          <Route path='/' element={<AppLayout/>}>
            <Route index element={<HomePage/>}/>
            <Route path='/interest' element={<InterestPage/>}/>
            <Route path='/orderList' element={<OrderListPage/>}/>
            <Route path='/myPage' element={<MyPage/>}/>
            <Route path='/storePage' element={<StorePage/>}/>
          </Route>
        </Route>
      </Routes>
    </>
  )
}

export default App
