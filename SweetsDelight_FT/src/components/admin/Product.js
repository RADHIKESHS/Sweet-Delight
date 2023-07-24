import React, { useCallback, useContext, useLayoutEffect, useMemo, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { IoIosAddCircle} from 'react-icons/io';
import Loader from './Loader';
import { addProduct, deleteProduct, getAllProducts, getProductById, updateProduct } from '../request/fetch';
import Store from '../store/storeContext';
import { KEYS } from '../store/reducer';

export default function Product() {
  const [products, setProducts] = useState(null);

  const navigate = useNavigate()

  useLayoutEffect(() => {
    getAllProducts()
    .then(setProducts)
    .catch(() => setProducts([]))
  }, [])

  const deleteProductHandler = pid => {
    deleteProduct(pid)
    .then(() => {
      setProducts(products => products?.filter(p => p.productid != pid))
    })
    .catch((e) => {
      console.log(e)
      alert("Failed to delete product, Try again!")
    })
  }

  if(products == null){
    return <Loader />
  }

  return products.length == 0 ? 
  (
    <>
    <h2 className='error'>No product Found</h2>
    <Link to={"add"}>want to add a product?</Link>
    </>
  ):
  (<>
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Available</th>
                    <th>Quantity</th>
                    <th>Category</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                {
                    products.map((product, i) => (
                        <tr key={product.productid}>
                            <td>{i+1}</td>
                            <td>{product.name}</td>
                            <td>{product.description}</td>
                            <td>
                              <img src={product.photopath} height={40} width={50} style={{objectFit:'cover', borderRadius: 'var(--radius)', marginTop: 5}}/>
                            </td>
                            <td style={{whiteSpace: 'nowrap'}}>₹ {product.price.toFixed(2)}</td>
                            <td>{product.available ? 'YES':'NO'}</td>
                            <td>{product.quantity}</td>
                            <td style={{whiteSpace: 'nowrap'}}>
                              {product.category.name}
                            </td>
                            <td>
                                <button onClick={() => deleteProductHandler(product.productid)}>Delete</button>
                                <button onClick={() => navigate(`/admin/sweets/update/${product.productid}`)}>Edit</button>
                            </td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <Link to="add" >
            <IoIosAddCircle className='add-more'/>
        </Link>
   </>
  )
}

export const AddProduct = () => {
  const [state] = useContext(Store)
  const categories = useMemo(() => Object.values(state[KEYS.CATEGORIES]), [state[KEYS.CATEGORIES]])

  const addCustomerHandler = (event) => {
      event.preventDefault();

      let categoryId = event.target.category.value
      let body = {
          name: event.target.name.value,
          description: event.target.description.value,
          price: event.target.price.value,
          photopath: event.target.photopath.value,
          available: event.target.available.value == 'true',
          quantity: parseInt(event.target.quantity.value),
      }

      addProduct(categoryId, body)
      .then((res) => {
          event.target.reset()
          event.target.status.type = "text"
          event.target.status.value = "Product added successfully"
          event.target.status.className = "success"
      })
      .catch((e) => {
          event.target.status.type = "text"
          event.target.status.value = e.message || "Failed to add product"
          event.target.status.className = "error"
      })
  }
  return (
      <form onSubmit={addCustomerHandler}>
          <h2 style={{color: 'white', marginBottom: 10}}>Add Product</h2>
          <input type='text' placeholder='Product Name' name='name'/>
          <input type='text' placeholder='Description' name='description'/>
          <input type='number' placeholder='Price' name='price'/>
          <input type='text' placeholder='Image Url' name='photopath'/>
          <select name='available'>
            <option value='true'>YES</option>
            <option value='false'>NO</option>
          </select>
          <input type='number' placeholder='Quantity' name='quantity'/>
          <select name='category'>
              {
                categories.map(category => (
                  <option key={category.name + category.categoryid} value={category.categoryid}>{category.name}</option>
                ))
              }
          </select>
          <input type='hidden' name='status' readOnly/>
          <input type='submit' value="Add" />
      </form>
  )
}

export const UpdateProduct = () => {
  const [state] = useContext(Store)
  const categories = useMemo(() => Object.values(state[KEYS.CATEGORIES]), [state[KEYS.CATEGORIES]])

  const {id} = useParams()

  const [isLoading, setLoading] = useState(true)
  const [product, setProduct] = useState(null)

  const navigate = useNavigate()

  useLayoutEffect(() => {
    getProductById(id)
    .then(setProduct)
    .catch(e => {
      alert('Product not found')
      navigate(-1)
    })
    .finally(() => setLoading(false))
  }, [])

  const updateCustomerHandler = useCallback((event) => {
    event.preventDefault();
    let categoryId = event.target.category.value

    let body = {
        ...product,
        name: event.target.name.value,
        description: event.target.description.value,
        price: event.target.price.value,
        photopath: event.target.photopath.value,
        available: event.target.available.value == 'true',
        quantity: parseInt(event.target.quantity.value),
        category: categories.find(category => category.categoryid == categoryId)
    }

    updateProduct(id, body)
    .then((res) => {
        event.target.status.type = "text"
        event.target.status.value = "Product updated successfully"
        event.target.status.className = "success"
    })
    .catch((e) => {
        event.target.reset()
        event.target.status.type = "text"
        event.target.status.value = e.message || "Failed to updated product"
        event.target.status.className = "error"
    })
    }, [product, categories])

  if(isLoading) return <Loader />

  return product == null ? 
  (
    <>
    <h2 className='error'>No product Found</h2>
    <Link to={"/admin/sweets/add"}>want to add a product?</Link>
    </>
  ):(
      <form onSubmit={updateCustomerHandler}>
          <h2 style={{color: 'white', marginBottom: 10}}>Update Product</h2>
          <input type='text' placeholder='Product Name' name='name' defaultValue={product.name}/>
          <input type='text' placeholder='Description' name='description' defaultValue={product.description}/>
          <input type='number' placeholder='Price' name='price' defaultValue={product.price}/>
          <input type='text' placeholder='Image Url' name='photopath' defaultValue={product.photopath}/>
          <select name='available' defaultValue={product.available}>
            <option value='true'>YES</option>
            <option value='false'>NO</option>
          </select>
          <input type='number' placeholder='Quantity' name='quantity' defaultValue={product.quantity}/>
          <select name='category' defaultValue={product.category.categoryid}>
              {
                categories.map(category => (
                  <option key={category.name + category.categoryid} value={category.categoryid}>{category.name}</option>
                ))
              }
          </select>
          <input type='hidden' name='status' readOnly/>
          <input type='submit' value="Update" />
      </form>
  )
}
