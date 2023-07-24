import React from 'react'
import '../css/NotFound.css'

const NotFound = () => {
  return (
    <div className="not-found-container">
      <h1 className="not-found-title">404 - Route Not Found</h1>
      <p className="not-found-text">The page you are looking for does not exist.</p>
    </div>
  );
};

export default NotFound;
