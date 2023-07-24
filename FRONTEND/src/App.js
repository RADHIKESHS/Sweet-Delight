import {useEffect, useLayoutEffect, useReducer, useState} from 'react'
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import './App.css';
import Main from './components/inc/Main';
import Product from './components/Product';
import Home from './components/Home';
import Cart from './components/Cart';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';
import UserSidebar from './components/user/Sidebar';
import UserProfile from './components/user/Profile';
import UserOrder from './components/user/Order';
import UserAccount from './components/user/Account';
import AdminSidebar from './components/admin/Sidebar';
import AdminCustomer from './components/admin/Customer';
import AdminProduct, {AddProduct, UpdateProduct} from './components/admin/Product';
import AdminOrder from './components/admin/Order';
import { auth, getCart, getCategories, getCustomer } from './components/request/fetch';

import Store from './components/store/storeContext';
import {initialState, reducer, KEYS} from './components/store/reducer';
import NotFound from './components/NotFound';
import Cookies from 'js-cookie';
import Logout from './components/user/Logout';

function App() {
  const [state, dispatch] = useReducer(reducer, initialState);

  useEffect(() => {
    if(state[KEYS.USER]){
      getCart(1)
      .then(cart => {
        dispatch({type: KEYS.ADD_CART, payload: cart})
      })
      .catch(e=> {})
    }
  }, [state[KEYS.USER]])
  
  useLayoutEffect(() => {
    auth().then(user => {
      dispatch({type: KEYS.USER, payload: user})
    }).catch(() => {
      Cookies.remove('token')
    })
    

    getCategories().then(categories => {
      dispatch({type: KEYS.ADD_CATEGORIES, payload: categories})
    }).catch(e => {})
  }, [])
  return (
    <div className="App">
      <Store.Provider value={[state, dispatch]}>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<Main/>} >
                <Route index element={<Home />} />
                <Route path='sweets' element={<Product />} />
                <Route path='sweets/:category' element={<Product />} />
                <Route path='cart' element={<Cart />} />
                <Route path='signin' element={state[KEYS.USER] ? <Navigate to='/'/>:<SignIn />} />
                <Route path='signup' element={state[KEYS.USER] ? <Navigate to='/'/>:<SignUp />} />
                {
                  state[KEYS.USER] && <Route path='user' element={<UserSidebar />}>
                    <Route index element={<Navigate to='profile' replace/>} />
                    <Route path='profile' element={<UserProfile />} />
                    <Route path='orders' element={<UserOrder />} />
                    <Route path='account' element={<UserAccount />} />
                    <Route path='logout' element={<Logout />} />
                  </Route>
                }
                
            </Route>
            {
                  state[KEYS.USER] && state[KEYS.USER].role == 'ADMIN' && <Route path='/admin' element={<AdminSidebar />}>
                <Route index element={<Navigate to='customers' replace />} />
                <Route path='customers' element={<AdminCustomer />} />
                <Route path='sweets' element={<AdminProduct />} />
                <Route path='sweets/add' element={<AddProduct />} />
                <Route path='sweets/update/:id' element={<UpdateProduct />} />
                <Route path='orders' element={<AdminOrder />} />
            </Route>
          }
          <Route path='*' element={<NotFound />} />
          </Routes>
        </BrowserRouter>
      </Store.Provider>
    </div>
  );
}

export default App;
