import Cookies from "js-cookie"

const API_URL = 'http://localhost:8080'

export default function makeRequest(endpoint, method, body, responseType = 'json', config){
    let hasError = false
    let defaultConfig = {
        method,
        body: body && JSON.stringify(body),
        ...config,
        headers: {
            'Content-Type': 'application/json',
            ...config?.headers
        },
    }
    return {
        async execute(){
            if(hasError) throw hasError;
            return await fetch(API_URL + endpoint, defaultConfig).then(res => {
                hasError = res.status >= 400
                return res[responseType]()
            }).then(res => hasError ? Promise.reject(res):res)
        },
        auth(){
            let token = Cookies.get('token')
            if(token) {
                defaultConfig.headers.Authorization = `Bearer ${token}`
            }
            else{
                hasError = 'Token not found'
            }
            return this
        }
    }
    return 
}

// login 
export const login = async (username, password) =>{
    return await fetch(API_URL+'/sweetDelight/customerUser/logini', {
        headers: {'Authorization': `Basic ${btoa(username + ':' + password)}`}
    })
    .then(res => {
        if(res.status == 202){
            let token = res.headers.get('Authorization')
            Cookies.set('token', token, {expires: 30})
            return res.json()
        }
        return Promise.reject(res.json())
    })
}
export const auth = async () => {
    if(!Cookies.get('token')) throw "Token not found";
    return await fetch(API_URL+'/sweetDelight/customerUser/logini', {
        method: 'POST',
        body: Cookies.get("token")
    })
    .then(res => {
        if(res.status == 202) return res.json()
        return Promise.reject(res.json())
    })
}
// products
export const getProducts = async (category, query = '') => {
    if(category) category = '/'+category
    else category = ''

    const products = await makeRequest(`/product/product/getallavailableproduct${category}?${query}`).execute()
    return products
}

export const addProductToCart = async (customerId, productId) => {
    return await makeRequest(`/sweetDelight/customerUser/carts/${customerId}/add/${productId}`, 'PUT').auth().execute()
}
export const removeProductFromCart = async (customerId, productId) => {
    return await makeRequest(`/sweetDelight/customerUser/carts/${customerId}/remove/${productId}`, 'PUT', null, 'text').auth().execute()
}

//x
export const getProductsByName = async (search) => {
    return await makeRequest(`/sweetDelight/searchbyname?productName=${search}`).auth().execute()
}

// category
export const getCategories = async () => {
    return await makeRequest('/sweetDelight/categories').execute()
}

// cart

export const getCart = async (customerId) => {
    return await makeRequest(`/sweetDelight/customerUser/carts/${customerId}`).auth().execute()
}

// order

export const placeOrder = async (customerId) => {
    return await makeRequest(`/sweetorder/add/${customerId}`, "POST", null, 'text').auth().execute()
}

export const getOrders = async (customerId) => {
    return await makeRequest(`/sweetorder/getallorders/${customerId}`).auth().execute()
}
// customer
export const getCustomer = async (customerId) => {
    return await makeRequest(`/sweetDelight/admin/customers/${customerId}`).auth().execute()
}

export const updateCustomer = async (customerId, body) => {
    return await makeRequest(`/sweetDelight/customerUser/update/${customerId}`, 'PUT', body).auth().execute()
}
export const addCustomer = async (body) => {
    return await makeRequest(`/sweetDelight/customerUser/register`, 'POST', body).execute()
}

// user
export const deleteCustomer = async (customerId) => {
    return await makeRequest(`/sweetDelight/delete/${customerId}`, 'DELETE', 'text').auth().execute()
}

// admin
export const getAllCustomer = async () => {
    return await makeRequest('/sweetDelight/admin/customers').auth().execute()
}

export const getAllProducts = async () => {
    return await makeRequest('/sweetDelight/products').auth().execute()
}

export const deleteProduct = async (productId) => {
    return await makeRequest(`/sweetDelight/admin/products/${productId}`, 'DELETE',null, 'text').auth().execute()
}
export const addProduct = async (categoryId, body) => {
    return await makeRequest(`/sweetDelight/admin/products/${categoryId}`, 'POST', body).auth().execute()
}
export const assigCategoryToProduct = async (productId, categoryId) => {
    return await makeRequest(`/sweetDelight/admin/products/${productId}/categories/${categoryId}`, 'PUT').auth().execute()
}


export const getProductById = async (productId) => {
    return await makeRequest(`/sweetDelight/admin/products/${productId}`).auth().execute()
}

export const updateProduct = async (productId, body) => {
    return await makeRequest(`/sweetDelight/admin/product/update/${productId}`, 'PUT', body).auth().execute()
}

export const updateCustomerRole = async (customerId, role) => {
    return await makeRequest(`/sweetDelight/admin/${customerId}/role/${role}`, 'PUT').auth().execute()
}
export const updateOrderStatus = async (orderId, status) => {
    return await makeRequest(`/sweetDelight/admin/${orderId}/status/${status}`, 'PUT').auth().execute()
}