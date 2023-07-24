import React, { useContext, useLayoutEffect, useState } from 'react'
import {getOrders} from '../request/fetch'
import Store from '../store/storeContext'
import {KEYS} from '../store/reducer'

export default function Order() {
    const [state, dispatch] = useContext(Store)
    const [orders, setOrders] = useState([])

    useLayoutEffect(() => {
        getOrders(state[KEYS.USER].userId)
        .then(setOrders)
        .catch(e => console.error(e))
    }, [])
  return (
    <div className='orders-container'>
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
                                <td>{order.status}</td>
                            </tr>
                        )
                    })
                }
                
            </tbody>
        </table>
        <br/>
        <br/>
    </div>
  )
}
