import React, { useContext, useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBars, faXmark } from '@fortawesome/free-solid-svg-icons'
import { Access } from './Access';
import { faFacebook, faInstagram, faLinkedinIn, faTwitter } from '@fortawesome/free-brands-svg-icons';
import { LoggedIn } from './LoggedIn';
import { UserContext } from '../../../UserContext';

export const Burguer = () => {

    const [open, setOpen] = useState(false);
    const { auth, setAuth } = useContext(UserContext);

    const menuRender = () => {
        if (auth) {
            return <LoggedIn setAuth={setAuth} />
        }

        if (!auth) {
            return <div>
                        <h2 className='header__menu'>MENÚ</h2>
                        <Access auth={auth} />
                   </div>
        }
    }

    return (
        <>
            <FontAwesomeIcon className='header__burguer' open={open} onClick={() => setOpen(!open)} icon={faBars} />

            <div className={open ? 'header__nav' : ''}>
                <div className='header__exit'>
                    <FontAwesomeIcon open={open} onClick={() => setOpen(!open)} icon={faXmark} />
                </div>

                {
                    menuRender()
                }

                <hr className='header__line' />

                <div className='header__option'>
                    <FontAwesomeIcon className='header__icon' icon={faFacebook} />
                    <FontAwesomeIcon className='header__icon' icon={faLinkedinIn} />
                    <FontAwesomeIcon className='header__icon' icon={faTwitter} />
                    <FontAwesomeIcon className='header__icon' icon={faInstagram} />
                </div>

            </div>
        </>
    )
}
