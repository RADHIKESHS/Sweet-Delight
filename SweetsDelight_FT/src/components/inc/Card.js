import React, { useCallback, useContext } from 'react'
import { IoMdCart, IoMdRemove } from 'react-icons/io'
import Store from '../store/storeContext'
import {KEYS} from '../store/reducer'
import { addProductToCart, removeProductFromCart } from '../request/fetch'
import { useNavigate } from 'react-router-dom'

const AddCartBtn = () => {
  return <>
            Add to Cart <IoMdCart style={{
                fontSize: '34px',
                background: 'var(--dark-blue)',
                color: 'white',
                padding: '5px',
                borderRadius: '50%',
                boxShadow: 'rgba(0, 0, 0, 0.24) 0px 3px 8px'
            }} />
        </>
}
const RemoveCartBtn = () => {
  return <>
            Remove from Cart <IoMdRemove style={{
                fontSize: '34px',
                background: 'var(--dark-blue)',
                color: 'white',
                padding: '5px',
                borderRadius: '50%',
                boxShadow: 'rgba(0, 0, 0, 0.24) 0px 3px 8px'
            }} />
        </>
}
export default function Card({product}) {
  const [state, dispatch] = useContext(Store)
  const navigate = useNavigate()
  
  const toggleCart = useCallback(() => {
    if(state[KEYS.USER] == null) return navigate("/signin")

    if(product.productid in state[KEYS.CART]){
      // remove cart
      removeProductFromCart(state[KEYS.USER].userId, product.productid)
      .then(e => {
        dispatch({type: KEYS.REMOVE_CART, payload: product.productid})
      })
      .catch(e => alert(e.message))
    }
    else{
      // add to cart
      addProductToCart(state[KEYS.USER].userId, product.productid)
      .then(e => {
        dispatch({type: KEYS.ADD_CART, payload: product})
      })
      .catch(alert)
    }
  }, [state[KEYS.CART]])

  return product && (
    <div className='card' style={{minWidth: 100, borderRadius: 'var(--radius)', overflow: 'hidden', border: '1px solid var(--xlight)'}}>
        <img style={{width: "100%", aspectRatio: '1/1', objectFit: 'cover'}} src={product.photopath} />
        <section style={{padding: 10}}>
            <h3 style={{margin: 0}}>{product.name}</h3>
            <span>Price: ₹ {product.price.toFixed(2)}</span>
              <button 
              onClick={toggleCart}
              style={{
                  height: '40px',
                  borderRadius: 'var(--radius)',
                  background: 'none',
                  color: 'var(--dark-blue)',
                  border: 'none',
                  outline: 'none',
                  marginTop: '10px',
                  fontSize: '16px',
                  fontWeight: 'bold',
                  width: '100%',
                  display: 'flex',
                  justifyContent: 'space-between',
                  alignItems: 'center',
                  cursor: 'pointer'
}}>
                  {
                    product.productid in state[KEYS.CART] ?
                    <RemoveCartBtn />:<AddCartBtn />
                  }
                  
            </button>
        </section>
    </div>
  )
}
