import React, { useContext } from 'react'
import {KEYS} from '../store/reducer'
import Store from '../store/storeContext'
import { deleteCustomer } from '../request/fetch'
import {useNavigate} from 'react-router-dom'

export default function Account() {
  const [state, dispatch] = useContext(Store)
  const navigate = useNavigate()

  const deleteAccountHandler = () => {
    deleteCustomer(state[KEYS.USER].userId)
    .then(() => {
      dispatch({type: KEYS.RESET, payload: [KEYS.USER, KEYS.IS_ADMIN, KEYS.CART]})
      navigate('/')
    })
    .catch(e => alert("Failed to delete account, Try again!"))
  }
  return (
    <div className='account-container'>
      <br/>
      <br/>
      <br/>
      <p>
        Are you absolutely sure that you want to delete your account? <b>please note that there is no option to restore the account or its data nor reuse the username once it's deleted.</b> If you click the button we will send you an email with further instructions on deleting your account.
      </p>
      <button onClick={deleteAccountHandler}>Delete Account</button>
      <br/>
      <br/>
      <br/>
    </div>
  )
}
