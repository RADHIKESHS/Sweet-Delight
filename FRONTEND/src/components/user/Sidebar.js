import React from 'react'
import { Link, Outlet, useLocation } from 'react-router-dom'

const userPaths = [
  {to: '', match: 'user', label: 'Profile'},
  {to: 'orders', match: 'orders', label: 'Orders'},
  {to: 'account', match: 'account', label: 'Account'},
  {to: 'logout', match: 'logout', label: 'Logout'}
]
export default function Sidebar() {
  const location = useLocation()
  const paths = location.pathname.split("/")

  return (
    <main className='user-sidebar screen'>
    <br/>
      <section className='container'>
            <ul className='user-nav'>
              {
                userPaths.map(route => (
                  <Link to={route.to} key={route.to}>
                    <li className={paths.at(-1) == route.match ? 'selected':''}>{route.label}</li>
                  </Link>
                ))
                
              }
            </ul>
            <section className='main-container'>
                <Outlet />
            </section>
      </section>
      <br/>
    </main>
  )
}
