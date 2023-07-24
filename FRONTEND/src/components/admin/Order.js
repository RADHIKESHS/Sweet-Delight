import React, { useLayoutEffect, useState, useContext } from 'react'
import Loader from './Loader';
import { getOrders, updateOrderStatus } from '../request/fetch';
import Store from '../store/storeContext'
import {KEYS} from '../store/reducer'

export default function Order() {
  const [state, dispatch] = useContext(Store)
  const [orders, setOrders] = useState(null);

  useLayoutEffect(() => {
    getOrders(state[KEYS.USER].userId)
    .then(setOrders)
    .catch(e => setOrders([]))
  }, [])

  const changeOrderStatusHandler = (orderId, status) => {
    updateOrderStatus(orderId, status)
    .then(res => {
      setOrders(orders => orders.map(c => c.sweetOrderId == orderId ? res:c))
    })
    .catch(e => alert(e.message || e))
  }

  if(orders == null){
    return <Loader />
  }

  return orders.length == 0 ? 
  <h2 className='error'>No orders Found</h2>:
  <table>
      <thead>
          <tr>
                <th>#</th>
                <th>OrderId</th>
                <th>Amount</th>
                <th>OrderDate</th>
                <th>Status</th>
          </tr>
      </thead>
      <tbody>
      {
        orders.map((order, i) => {
            return (
                <tr key={'order-id'+order.sweetOrderId}>
                    <td>{i+1}</td>
                    <td>{order.sweetOrderId}</td>
                    <td>₹ {order.orderBill.totalCost.toFixed(2)}</td>
                    <td>{new Date(order.date).toLocaleDateString()}</td>
                    <td>
                      <select onChange={e => changeOrderStatusHandler(order.sweetOrderId, e.target.value)} value={order.status}>
                        <option value='PENDING'>PENDING</option>
                        <option value='PROCESSING'>PROCESSING</option>
                        <option value='SHIPPED'>SHIPPED</option>
                        <option value='DELIVERED'>DELIVERED</option>
                        <option value='ON_HOLD'>ON_HOLD</option>
                        <option value='BACKORDERED'>BACKORDERED</option>
                        <option value='CANCELLED'>CANCELLED</option>
                        <option value='RETURNED'>RETURNED</option>
                        <option value='REFUNDED'>REFUNDED</option>
                        <option value='COMPLETED'>COMPLETED</option>
                      </select>
                    </td>
                </tr>
            )
        })
    }
      </tbody>
  </table>
}
