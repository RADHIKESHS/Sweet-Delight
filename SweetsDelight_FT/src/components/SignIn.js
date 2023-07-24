import React, { useContext, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { login } from './request/fetch'
import Store from './store/storeContext'
import {KEYS} from './store/reducer'
import Message from './inc/Message'

export default function SignIn() {
  const [error, setError] = useState(null)
  const [state, dispatch] = useContext(Store)
  const navigate = useNavigate()
  
  const loginHandler = e => {
    e.preventDefault()

    let username = e.target.username.value
    let password = e.target.password.value
    
    login(username, password)
    .then(res => {
      dispatch({type: KEYS.USER, payload: res})
      navigate('/')
    })
    .catch(e => setError(e.message || e))
  }
  return (
    <main className='singin screen'>
      <form onSubmit={loginHandler}>
        <h2>Sign In</h2>
          <label>
            <input type='text' placeholder='Enter your Username' name='username'/>
          </label>
          <label>
            <input type='password' placeholder='Enter your Password' name='password' />
          </label>
          <Message error={error} />
          <button>Sing Up</button>
          <br/>
          <div className='divider'>OR</div>
          <p>
            Don't have an account? <br/><Link to='/signup'>Create a new Account</Link>
          </p>
          <br/>
      </form>
    </main>
  )
}
