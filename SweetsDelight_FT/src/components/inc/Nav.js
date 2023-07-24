import React, { useContext, useMemo } from 'react'
import {FiChevronDown, FiPhone, FiShield} from 'react-icons/fi'
import { Link } from 'react-router-dom';
import Store from '../store/storeContext';
import { KEYS } from '../store/reducer';

export default function Nav() {
  const [state] = useContext(Store)
  const categories = useMemo(() => Object.values(state[KEYS.CATEGORIES]), [state[KEYS.CATEGORIES]])
  return (
    <nav className='screen'>
        <ul>
            <li className='dropdown'>
                <Link className='text-hover'>
                    Category
                    <FiChevronDown />
                </Link>
                <section className='dropdown-menu'>
                    <ul>
                        {
                            categories.map(category => (
                                <li key={category.name + category.categoryid}>
                                    <Link className='text-hover' to={"/sweets/"+category.name}>
                                        {category.name}
                                    </Link>
                                </li>
                            ))
                        }
                    </ul>
                </section>
            </li>
            <li>
                <Link className='text-hover' to='/sweets'>
                    Sweets
                </Link>
            </li>
            <li>
                <Link className='text-hover' to='https://github.com/RADHIKESHS/frail-power-8560'>
                    About us
                </Link>
            </li>
            <li>
                <Link className='text-hover'>
                    Faq
                </Link>
            </li>
        </ul>
        <section className='nav-options'>
            <div className='nav-option'>
                <FiPhone />
                <section>
                    <span className='upper-text'>Contact our experts</span>
                    <span className='text-hover lower-text'>Any Questions?</span>
                </section>
            </div>
            <div className='nav-option'>
                <FiShield />
                <section>
                    <span className='upper-text'>Authenticity Guaranteed</span>
                    <span className='text-hover lower-text'>Shop with Confidence</span>
                </section>
            </div>
        </section>
    </nav>
  )
}
