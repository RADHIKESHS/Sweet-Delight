import React from 'react'
import { useLocation } from 'react-router-dom'

export default function Breadcrumbs({style}) {  
    const location = useLocation()
    const paths = location.pathname.split("/")
    paths[0] = "Home"
  return (
    <div 
    className='breadcrumbs screen'
    style={{
        padding: "2px 5px",
        color: "#808080",
        borderRadius: 3,
        fontSize: 14,
        marginBottom: 5,
        alignSelf: "flex-start",
        ...style}}>
            {decodeURIComponent(paths.join(" / "))}
        </div>
  )
}
