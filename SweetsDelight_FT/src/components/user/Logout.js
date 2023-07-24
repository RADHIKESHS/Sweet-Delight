import React, { useContext, useEffect } from 'react'
import { KEYS } from '../store/reducer'
import Store from '../store/storeContext'
import { Navigate } from 'react-router-dom'
import Cookies from 'js-cookie'

export default function Logout() {
  const [state, dispatch] = useContext(Store)

  dispatch({type: KEYS.RESET, payload: [KEYS.USER, KEYS.CART]})

  Cookies.remove('token')

  return <Navigate to='/' />
}
