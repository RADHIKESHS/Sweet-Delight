import React from 'react'
import { Link, Outlet, useLocation } from 'react-router-dom'
import { IoMdCube, IoMdPerson, IoMdPricetag } from 'react-icons/io'
import "../../css/sidebar.css"

const routes = [
    {name: "Customer", path: "customers", icon: <IoMdPerson />},
    {name: "Sweets", path: "sweets", icon: <IoMdCube />},
    {name: "Order", path: "orders", icon: <IoMdPricetag/>}
]
export default function Sidebar() {
  const location = useLocation()
  const paths = location.pathname.split("/")
  paths[0] = "Home"

  return (
    <section className='admin-container'>
        <section className='sidebar'>
            <div className='head'></div>
            <ul>
                {
                    routes.map(route => (
                        <Link to={route.path} key={route.name}>
                            <li className={location.pathname.includes(route.path) ? "selected":""}>{route.icon}{route.name}</li>
                        </Link>
                    ))
                }
            </ul>
        </section>
        <main>
            <div className='head'>{paths.at(-1)}</div>
            <section>
                <div className='breadcrumbs'>
                    {paths.join(" / ")}
                </div>
                <Outlet />
            </section>
        </main>
    </section>
  )
}
