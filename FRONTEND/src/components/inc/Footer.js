import React from 'react';
import '../../css/footer.css'

const Footer = () => {
  return (
    <>
    <hr className='line'/>
    <footer className='screen'>
      <div className="footer-content">
        <div className="footer-section">
          <h4>Company</h4>
          <ul>
            <li>Search</li>
            <li>About us</li>
            <li>Privacy policy</li>
            <li>Terms of Service</li>
            <li>Blog</li>
            <li>Support</li>
            <li>Contact us</li>
          </ul>
        </div>

        <div className="footer-section">
          <h4>Shipping</h4>
          <ul>
            <li>Shipping Policy</li>
            <li>Return & Refund Policy</li>
            <li>Frequently Asked Questions</li>
          </ul>
        </div>

        <div className="footer-section">
          <h4>Connect with us</h4>
          <ul>
            <li>Show us some love ❤️ on social media</li>
            <li>Email</li>
            <li>Facebook</li>
            <li>Instagram</li>
            <li>Pinterest</li>
            <li>Twitter</li>
            <li>YouTube</li>
          </ul>
        </div>
      </div>

      <div className="footer-bottom">
        <p>&copy; {new Date().getFullYear()} Sweet delights. All rights reserved.</p>
      </div>
    </footer>
    </>
  );
};

export default Footer;
