import React, { useEffect } from 'react'
import { motion } from 'framer-motion'

const variants = {
  open: {opacity: 1, pointerEvents: 'all'},
  close: {opacity: 0, pointerEvents: 'none'}
}
export default function Overlay({children, value=1, hide, onClose}) {

  useEffect(() => {
    variants.open.opacity = value
  },[])

  const close = (e) => {
    if(e.target.classList.contains("overlay")){
      onClose(e)
    }
  } 
  return (
    <motion.div 
    className='overlay' 
    onClick={onClose && close}
    initial={variants.close}
    variants={variants}
    animate={hide ? "close":"open"}>
      {children}
    </motion.div>
  )
}
