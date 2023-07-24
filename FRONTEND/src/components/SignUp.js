import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { addCustomer } from './request/fetch';
import Message from './inc/Message'

export default function SignUp() {
  const [msg, setMsg] = useState({error: null, success: null})

  const signupHandler = (e) => {
    e.preventDefault();

    let body = {
      username: e.target.username.value,
      address: e.target.address.value,
      customerName: e.target.name.value,
      customerEmail: e.target.email.value,
      password: e.target.password.value,
      role: "USER",
    }

    addCustomer(body)
    .then(res => {
      e.target.reset()
      setMsg({success: "Account created successfully", error: null})
    })
    .catch(e => {
      setMsg({error: "Failed to create account, try again", success: null})
    })
  }
  return (
    <main className='singin screen'>
      <form onSubmit={signupHandler}>
        <h2>Sign Up</h2>
          <label>
            <input type='text' placeholder='Full Name' name='name'/>
          </label>
          <label>
            <input type='text' placeholder='Address' name='address'/>
          </label>
          <label>
            <input type='text' placeholder='Username' name='username'/>
          </label>
          <label>
            <input type='email' placeholder='Email' name='email'/>
          </label>
          <label>
            <input type='password' placeholder='Password' name='password'/>
          </label>
          <Message {...msg}/>
          <button>Sing Up</button>
          <br/>
          <div className='divider'>OR</div>
          <p>
            Already have an account? <br/><Link to='/signin'>Sign in</Link>
          </p>
          <br/>
      </form>
    </main>
  )
}
