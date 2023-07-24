import "../../css/header.css";
import React, { useContext, useEffect, useMemo, useRef, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import Overlay from './Overlay';
import {IoMdMail, IoLogoFacebook, IoMdPerson, IoLogoInstagram, IoLogoTwitter, IoMdSearch, IoMdCart, IoMdArrowDown} from 'react-icons/io'
import Nav from './Nav';
import Store from "../store/storeContext";
import { KEYS } from "../store/reducer";
import { getProductsByName } from "../request/fetch";


export default function Header() {
    const [isBlur, setBlur] = useState(true)
    const [searchValue, setSearchValue] = useState('')
    const navigate = useNavigate()
    const [state] = useContext(Store)

    const cartCount = useMemo(() => Object.keys(state[KEYS.CART]).length, [state[KEYS.CART]])
    
    const [searchResult, setSearchResult] = useState(null)

    const timeout = useRef()

    const onInputHandler = (e) => {
        setSearchValue(e.target.value)
        clearTimeout(timeout.current)

        if(e.target.value == ''){
            setSearchResult(null)
            return
        }
        timeout.current = setTimeout(() => {
            getProductsByName(e.target.value)
            .then(setSearchResult)
            .catch(() => setSearchResult(null))
        }, 500)
    }
    return (
        <>
        <Overlay hide={isBlur} value={0.3} onClose={() => setBlur(true)}/>
        
        <header>
            <div className='offers'>
                <div className='social-media-icons'>
                    <Link>
                        <IoMdMail />
                    </Link>
                    <Link>
                        <IoLogoFacebook />
                    </Link>
                    <Link>
                        <IoLogoInstagram />
                    </Link>
                    <Link>
                        <IoLogoTwitter />
                    </Link>
                </div>
                <div className='text'>
                    Now Free Shipping on orders all over India
                </div>
                <div></div>
            </div>
            <section className='screen'>
                <img onClick={() => navigate("/")} src={process.env.PUBLIC_URL + "/assets/logo.png"} className='logo' style={{cursor: 'pointer'}}/>
                <div className='search' focus={isBlur ? "false":"true"}>
                    <IoMdSearch />
                    <input 
                    type='text' 
                    placeholder='Search'  
                    value={searchValue} 
                    onInput={onInputHandler}
                    onFocus={() => setBlur(false)}/>
                    {
                        searchResult && <section className='search-result'>{
                            searchResult.length ?
                            <ul>
                                {
                                    searchResult.map(product => (
                                        <Link key={product.name}>
                                            <li>
                                                <div>{product.name}</div>
                                                <p>{product.description}</p>
                                            </li>
                                        </Link>
                                    ))
                                }
                            </ul>:null
                        }</section>
                    }
                    
                </div>
                <button onClick={() => navigate(state[KEYS.USER] ? '/user':'/signin')}>
                    <IoMdPerson />
                    Account
                </button>
                <button onClick={() => navigate(state[KEYS.USER] ? '/cart':'/signin')}>
                    <IoMdCart />
                    Cart ({cartCount})
                </button>
            </section>
            <hr className="line"/>
            <Nav />
            <hr className="line"/>
        </header>
        </>
    )
}
