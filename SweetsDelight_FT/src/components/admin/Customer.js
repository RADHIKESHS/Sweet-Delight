import React, { useContext, useEffect, useLayoutEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import { IoIosAddCircle, IoMdArrowBack, IoMdArrowForward } from 'react-icons/io';
import Loader from './Loader';
import { getAllCustomer, updateCustomerRole } from '../request/fetch';
import Store from '../store/storeContext';
import { KEYS } from '../store/reducer';

export default function Customer() {
  const [state, dispatch] = useContext(Store)

  const [customers, setCustomers] = useState(null);
  const [pageDetails, setPageDetails] = useState({
    currentPage: 0,
    totalPages: 0,
    size: 0,
    numberOfElements: 0
  });

  useLayoutEffect(() => {
    getAllCustomer()
    .then(setCustomers)
    .catch(() => setCustomers([]))
  }, [])

  const changeCustomerRoleHandler = (customerId, role) => {
    if(state[KEYS.USER].userId == customerId) return
    updateCustomerRole(customerId, role)
    .then(res => {
      setCustomers(customers => customers.map(c => c.userId == customerId ? res:c))
    })
    .catch(e => alert(e.message || e))
  }

  if(customers == null){
    return <Loader />
  }

  return customers.length == 0 ? 
  (
    <>
    <h2 className='error'>No customers Found</h2>
    </>
  ):
  (<>
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Customer Name</th>
                    <th>Username</th>
                    <th>Email ID</th>
                    <th>Address</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
                {
                    customers.map((customer, i) => (
                        <tr key={customer.userId}>
                            <td>{i+1 + (pageDetails.currentPage * pageDetails.size)}</td>
                            <td>{customer.customerName}</td>
                            <td>{customer.username}</td>
                            <td>{customer.customerEmail}</td>
                            <td>{customer.address}</td>
                            <td>
                              <select onChange={e => changeCustomerRoleHandler(customer.userId, e.target.value)} value={customer.role}>
                                <option value='ADMIN'>ADMIN</option>
                                <option value='USER'>USER</option>
                              </select>
                            </td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        {
            pageDetails.totalPages <= 1 ? null:
            <ReactPaginate
                initialPage={pageDetails.currentPage}
                breakLabel="..."
                nextLabel={<IoMdArrowForward />}
                onPageChange={null}
                pageRangeDisplayed={5}
                pageCount={pageDetails.totalPages}
                previousLabel={<IoMdArrowBack />}
                renderOnZeroPageCount={null}
            />
        }
   </>
  )
}
