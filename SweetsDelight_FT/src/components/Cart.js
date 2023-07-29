import React, { useContext, useMemo, useState } from 'react'
import Card from './inc/Card'
import { IoMdArrowForward } from 'react-icons/io'
import Store from './store/storeContext'
import { KEYS } from './store/reducer'
import ReactModal from 'react-modal'
import { Link } from 'react-router-dom'
import { getCart, placeOrder } from './request/fetch'

const customStyles = {
  content: {
    top: '50%',
    left: '50%',
    right: 'auto',
    bottom: 'auto',
    marginRight: '-50%',
    transform: 'translate(-50%, -50%)',
  },
};

export default function Cart() {
  const [modalIsOpen, setModalIsOpen] = useState(false)

  const [state, dispatch] = useContext(Store)

  const carts = useMemo(() => Object.values(state[KEYS.CART]), [state[KEYS.CART]])
  
  const totalPrice = useMemo(() => {
    return carts.reduce((ac, cur) => ac + cur.price, 0)
  }, [carts])

  const placeOrderHandler = () => {
      placeOrder(state[KEYS.USER].userId)
      .then(res => {
        dispatch({type: KEYS.RESET, payload: [KEYS.CART]})
        setModalIsOpen(true)
      })
      .catch(e => alert("Failed to place order"))
  }

  return (
    <main className='cart screen'>
        <section className='container'>
            <section className='filters'></section>
            <section className='products'>
              {
                carts.length == 0 ? (
                <>
                <br/>
                <h1 style={{textAlign: 'center'}}>Cart is Empty</h1>
                <p style={{textAlign: 'center'}}>Your cart is empty, Try to add some products</p>
                <br/>
                </>
                ):<div className='card-list'>{carts.map(product => <Card product={product} key={product.name} />)}</div>
              }
            </section>
        </section>
        <br/>
        <br/>
        {
          carts.length > 0 ? (
            <div style={{display: 'flex', flexDirection:'column', alignItems: 'flex-end'}}>
              <div>
                <h3 style={{margin:0}}>Total Price</h3>
                <p style={{margin:0}}>₹ {totalPrice}</p>
              </div>
              <button onClick={placeOrderHandler}>
                Place Order<IoMdArrowForward />
              </button>
            </div>
          ):null
        }
        <br/>
        <br/>
        <ReactModal
        isOpen={modalIsOpen}
        onRequestClose={() => setModalIsOpen(false)}
        style={customStyles}
        contentLabel="success">
          <h2>Order Placed Successfully!</h2>
          <p>Thank you for your order <Link to='/user/orders'>Check Status</Link>.</p>
          <br />
          <center>
          <button onClick={() => setModalIsOpen(false)} style={{height: 40, width: 100, background: 'var(--dark-blue)', color: 'white', border: 'none', borderRadius: 'var(--radius)', cursor: 'pointer'}}>Close</button>
          </center>
        </ReactModal>
    </main>
  )
}
