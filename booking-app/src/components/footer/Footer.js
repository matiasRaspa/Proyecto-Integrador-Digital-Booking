import React from 'react'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebook, faInstagram, faLinkedinIn,faTwitter } from '@fortawesome/free-brands-svg-icons';



export const Footer = () => {
  return (
    <footer>
      <h4>©2022 Digital Booking</h4>
      <div className='footer__icons'>
        <FontAwesomeIcon className='footer__icon' icon={faFacebook} />
        <FontAwesomeIcon className='footer__icon' icon={faLinkedinIn} />
        <FontAwesomeIcon className='footer__icon' icon={faTwitter} />
        <FontAwesomeIcon className='footer__icon' icon={faInstagram} />
      </div>
    </footer>
  )
}
