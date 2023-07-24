import React, { useLayoutEffect, useState } from 'react'
import { IoMdSend } from 'react-icons/io'
import Card from './inc/Card'
import "../css/main.css";
import makeRequest, { getProducts } from './request/fetch';

export default function Home() {
  const [products, setProducts] = useState([])

  useLayoutEffect(() => {
    getProducts(null, 'pageNumber=0&pageSize=4')
    .then(res => setProducts(res.content))
    .catch(e => console.log)
  }, [])
  return (
    <main className='home screen'>
      <div className='banner'>
        <img src={process.env.PUBLIC_URL + "/assets/banner.png"} />
      </div>
      <section className='products'>
        <h1>HOMEMADE SWEETS</h1>
        <p>Indulge in the Exquisite World of Indian Sweets</p>
        <br/>
        <div className='card-list'>
          {
            products.map(product => <Card product={product} key={product.name} />)
          }
        </div>
        <br />
        <br />
        <center>
          <button className='more text-hover'>
            View All Product
          </button>
        </center>
        <br />
      </section>
      <section className='best-seller'>
          <h1>BEST SELLERS</h1>
          <br/>
          <section>
            <div className='item'>
                <img src={process.env.PUBLIC_URL + "/assets/sweet-1.png"} />
                <p>Satisfies your Cravings</p>
                <h1>
                    Enjoy Home Made sweets
                </h1>
                <button>
                  Show more
                </button>
            </div>
            <div className='item'>
                <img src={process.env.PUBLIC_URL + "/assets/sweet-2.png"} />
                <p>"Tradition on a Platter</p>
                <h1>
                  The Timeless Magic of Sweets
                </h1>
                <button>
                  Show more
                </button>
            </div>
            <div className='item'>
                <img src={process.env.PUBLIC_URL + "/assets/sweet-3.png"}/>
                <p>Deliciously Divine</p>
                <h1>
                  The Allure of Indian Sweets
                </h1>
                <button>
                  Show more
                </button>
            </div>
          </section>
      </section>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <section className='service-details'>
          <section>
            <div className='item'>
              <img src='https://aahari.com/cdn/shop/files/Free_shipping_icon_d37ee5d9-8a46-46fa-93f2-29fb09ea053f.png?crop=center&height=128&v=1669441900&width=128' />
              <h3>Free Shipping</h3>
              <p>We offer Free Shipping on orders all over India</p>
            </div>
            <div className='item'>
              <img src='https://aahari.com/cdn/shop/files/Support_icon_01d40fe0-5475-46f7-8d4f-86d0d0038123.png?crop=center&height=128&v=1669441930&width=128' />
              <h3>Top-notch support</h3>
              <p>Any question? Our team is just one click away!</p>
            </div>
            <div className='item'>
              <img src='https://aahari.com/cdn/shop/files/Secure_payments.png?crop=center&height=128&v=1669441950&width=128' />
              <h3>Secure payments</h3>
              <p>Your payment information is processed securely</p>
            </div>
            <div className='item'>
              <img src='https://aahari.com/cdn/shop/files/Made_with_love_icon_d6725057-9a41-48f6-a3c2-6c38ee87ceec.png?crop=center&height=128&v=1669441965&width=128' />
              <h3>Made with love</h3>
              <p>Product you shop was made with a large portion of love</p>
            </div>
          </section>
      </section>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <section className='bigger-images'>
          <div className='bigger-left' style={{background: `url(${process.env.PUBLIC_URL}/assets/bigger-left.png)`, backgroundSize: 'cover', backgroundRepeat: 'no-repeat'}}>
            <h1>Bringing Sweetness To Your Life.</h1>
            <p>Because taste matters</p>
            <button>GET IT</button>
          </div>
          <div className='bigger-right' style={{background: `url(${process.env.PUBLIC_URL}/assets/bigger-right.png)`, backgroundSize: 'cover', backgroundRepeat: 'no-repeat'}}>
            <h1>Bringing Sweetness To Your Life.</h1>
            <p>Because taste matters</p>
            <button>GET IT</button>
          </div>
      </section>
      <br/>
      <br/>
      <br/>
      <section className='subscribe-section'>
          <IoMdSend />
          <h1>Subscribe to get latest update</h1>
          <div>
            <input type='email' placeholder='Enter your email' />
            <button>Submit</button>
          </div>
      </section>
      <br/>
      <br/>
      <br/>
    </main>
  )
}
