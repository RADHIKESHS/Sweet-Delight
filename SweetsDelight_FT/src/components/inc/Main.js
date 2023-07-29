import React from 'react'
import Header from './Header'
import Footer from './Footer'
import { Outlet, useLocation } from 'react-router-dom'
import Breadcrumbs from './Breadcrumbs'

export default function Main() {
  const location = useLocation()
  return (
    <>
    <Header />
    {
    location.pathname != '/' &&
    <Breadcrumbs style={{padding: 'var(--screen-padding)', marginTop: 10, marginBottom: 10}}/>
    }
    <Outlet />
    <Footer />
    </>
  )
}
