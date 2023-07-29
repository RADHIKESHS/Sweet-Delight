import React, { useContext, useState } from 'react'
import Store from '../store/storeContext'
import {KEYS} from '../store/reducer'
import { updateCustomer } from '../request/fetch'
import Message from '../inc/Message'

export default function Profile() {
  const [msg, setMsg] = useState({error:null, success: null})

  const [state, dispatch] = useContext(Store)

  const submitFormHandler = (e) => {
    e.preventDefault()
    let formData = new FormData(e.target)
    updateCustomer(state[KEYS.USER].userId, {
      ...state[KEYS.USER],
      password: '',
      customerName: formData.get('name')
    })
    .then(res => {
      dispatch({type: KEYS.USER, payload: res})
      setMsg(({success: 'Profile update successfully', error: null}))
    })
    .catch(e => {
      setMsg(({error: 'Something went wrong', success: null}))
    })
  }
  return (
    <div className='profile-container'>
    <br/>
       <form onSubmit={submitFormHandler}>
          <h1>Profile Details</h1>
          <label>
            <div style={{fontSize: 12, color: 'gray', marginBottom: 10}}>Name</div>
            <input type='text' placeholder='Name' name='name' defaultValue={state[KEYS.USER].customerName}/>
          </label>
          <label>
          <div style={{fontSize: 12, color: 'gray', marginBottom: 10}}>Address</div>
            <input type='text' placeholder='Address' name='address' defaultValue={state[KEYS.USER].address} readOnly disabled/>
          </label>
          <label>
          <div style={{fontSize: 12, color: 'gray', marginBottom: 10}}>Username</div>
            <input type='text' placeholder='Username' name='username' defaultValue={state[KEYS.USER].username} readOnly disabled/>
          </label>
          <label>
          <div style={{fontSize: 12, color: 'gray', marginBottom: 10}}>Email</div>
            <input type='email' placeholder='Email' name='email' defaultValue={state[KEYS.USER].customerEmail} readOnly disabled/>
          </label>
          <Message {...msg}/>
          <button>Update</button>
       </form>
       <br/>
       <br/>
       <br/>
    </div>
  )
}
