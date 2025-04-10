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
import SetAddressPage from './pages/setAddressPage/SetAddressPage';
function App() {
  
  return (
    <>
      <div className='lg:flex lg:justify-center'>
        <div className='lg:w-[400px] lg:box-content lg:border-1 z-[1] bg-white lg:min-h-[695px]'>
          <Routes>
            <Route path='/login' element={<LoginPage/>}/>
            <Route path='/register' element={<RegisterPage/>}/>
            <Route path='/searchId' element={<SearchIdPage/>}/>
            <Route path='/searchPassword' element={<SearchPasswordPage/>}/>
            <Route element={<ProtectedPage/>}>
              <Route path='/setAddress' element={<SetAddressPage/>}/>
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
        </div>
        <div className='bg-gray-400 w-full h-full fixed z-[0]'></div>
      </div>
    </>
  )
}

export default App
