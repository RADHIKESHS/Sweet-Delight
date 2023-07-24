export const KEYS = {
    ADD_CATEGORIES: 'addCategories',
    REMOVE_CATEGORIES: 'removeCategories',
    CATEGORIES: 'categories',
    ADD_CART: 'addCart',
    REMOVE_CART: 'removeCart',
    CART: 'cart',
    USER: 'user',
    IS_ADMIN: 'isAdmin',
    RESET: 'reset'
}
const initialState = {
    [KEYS.CATEGORIES]: {},
    [KEYS.USER]: null,
    [KEYS.IS_ADMIN]: false,
    [KEYS.CART]: {}
};
  
const handleAddSetPayload = (state, type, payload, keyGen) => {
    var updated;
    if (Array.isArray(payload)) {
        updated = {...state[type]}

        for(let d of payload){
            updated[keyGen(d)] = d
        }
        
    } else {
        updated = {...state[type], [keyGen(payload)]: payload}
    }
    return { ...state, [type]: updated };
}
const handleRemoveSetPayload = (state, type, payload) => {
    var updated;
    if (Array.isArray(payload)) {
        updated = {...state[type]}
        for(let id of payload){
            if(id in updated) delete updated[id]
        }
    } else {
        updated = {...state[type]}
        if(payload in updated) delete updated[payload]
    }
    return { ...state, [type]: updated };
}
const reset = (state, payload) => {
    let update = {...state}

    if(Array.isArray(payload)){
        for(let key of payload) {
            if(key in update) update[key] = initialState[key]
        }
    }
    else{
        if(payload in update) update[payload] = initialState[payload]
    }

    return update
}

const reducer = (state, action) => {
    switch (action.type) {
        case KEYS.ADD_CATEGORIES:
        return handleAddSetPayload(state, KEYS.CATEGORIES, action.payload, c => c.categoryid);
        case KEYS.REMOVE_CATEGORIES:
        return handleRemoveSetPayload(state, KEYS.CATEGORIES, action.payload)
        case KEYS.ADD_CART:
        return handleAddSetPayload(state, KEYS.CART, action.payload, c => c.productid);
        case KEYS.CART:
        return { ...state, [KEYS.CART]: action.payload};
        case KEYS.CATEGORIES:
        return { ...state, [KEYS.CATEGORIES]: action.payload};
        case KEYS.REMOVE_CART:
        return handleRemoveSetPayload(state, KEYS.CART, action.payload);
        case KEYS.USER:
        return { ...state, [KEYS.USER]: action.payload};
        case KEYS.IS_ADMIN:
        return { ...state, [KEYS.IS_ADMIN]: action.payload};
        case KEYS.RESET:
        return reset(state, action.payload);
        default:
        return state;
    }
};

export { initialState, reducer };
  