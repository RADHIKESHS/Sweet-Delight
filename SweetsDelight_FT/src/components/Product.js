import React, { useCallback, useLayoutEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import Card from './inc/Card'
import ReactPaginate from 'react-paginate';
import { IoMdArrowBack, IoMdArrowForward } from 'react-icons/io';
import { getProducts } from './request/fetch';

const query = new URLSearchParams("pageSize=12");

export default function Product() {
  const {category} = useParams();
  const [products, setProducts] = useState([])
  const [pageDetails, setPageDetails] = useState({
    currentPage: 0,
    totalPages: 0,
    size: 0,
    numberOfElements: 0
  });

  const getProductDetails = useCallback(() => {
    getProducts(category, query.toString()).then(res => {
      setProducts(res.content)
      setPageDetails({
        currentPage: res.number,
        totalPages: res.totalPages,
        size: res.size,
        numberOfElements: res.numberOfElements
    })
    })
    .catch(e => setProducts([]))
  }, [category])
  
  useLayoutEffect(getProductDetails, [category])

  const changePageHandler = ({selected}) => {
    query.set("pageNumber", selected)
    getProductDetails()
  }

  return (
    <main className='product screen'>
        <section className='container'>
          {
            products.length == 0 ?<center>
            <h1>No product found</h1>
            <p>Please try different category</p>
            </center>:
            <>
              <section className='filters'></section>
              <section className='products'>
                  <div className='card-list'>
                    {
                      products.map(product => <Card product={product} key={product.name}/>)
                    }
                  </div>
                  <br/>
                  <br/>
                  <center>
                  {
                      pageDetails.totalPages <= 1 ? null:
                      <ReactPaginate
                          className='pagination'
                          initialPage={pageDetails.currentPage}
                          breakLabel="..."
                          nextLabel={<IoMdArrowForward />}
                          onPageChange={changePageHandler}
                          pageRangeDisplayed={5}
                          pageCount={pageDetails.totalPages}
                          previousLabel={<IoMdArrowBack />}
                          renderOnZeroPageCount={null}
                      />
                  }
                  </center>
                  <br/>
                  <br/>
                  <br/>
              </section>
            </>
          }
        </section>
    </main>
  )
}
