import React, { useContext } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { useMediaQuery } from 'react-responsive';
import { UserContext } from '../../../UserContext';
import { useNavigate, Link } from 'react-router-dom';
import line from './../../../img/lin.svg'

import { getPayloadJwt } from '../../../helper/getPayloadJwt';


export const LoggedIn = () => {


    const navigate = useNavigate();
    const { setAuth } = useContext(UserContext);

    const isMobile = useMediaQuery({ maxWidth: 480 });

    const token = localStorage.getItem('token');

    const payload = getPayloadJwt(token)

    const nombreCompleto = payload.aud.split(' ')
    const nombre = nombreCompleto[0];
    const apellido = nombreCompleto[1];
    const initialName = nombre.charAt(0).toUpperCase();
    const initialLastName = apellido.charAt(0).toUpperCase();
    const fullName = initialName + nombre.slice(1) + ' ' + initialLastName + apellido.slice(1);

    const handleLogout = () => {
        setAuth(false);
        navigate('/');
        localStorage.removeItem("token");
    };

    console.log(new Date(payload.exp * 1000));

    const getPath = () => {

        const hasExpiredToken = payload === null ? false : new Date(payload.exp * 1000) >= new Date();

        if (!hasExpiredToken) {
            alert('Su sesión ha terminado')
            setAuth(false)
            return `/login`
        } else {
            return `/${payload.jti}/bookings`
        }
    }

    const getPathAdm = () => {

        const hasExpiredToken = payload === null ? false : new Date(payload.exp * 1000) >= new Date();

        if (!hasExpiredToken) {
            alert('Su sesión ha terminado')
            setAuth(false)
            return `/login`
        } else {
            return `/administracion`
        }
    }

    return (

        <div className='header__logged'>

            {
                payload.iss === "ROLE_ADMIN" ?
                    <div className='header__adm'>
                        <Link className='links' to={getPathAdm()}>
                            <p className='header__adm-title'>Administración</p>
                        </Link>
                        <img src={line} alt="line" />
                    </div> :
                    null
            }

            <div className='header__con-rese'>
                <h2 className='header__logged-initial' >{initialName + initialLastName}</h2>
            </div>
            <ul className='header__logged-greetings'>
                <li className='header__hello'>Hola, </li>
                <li className='header__name'>{fullName}
                    <ul>
                        <li>
                            <Link className='links' to={getPath()}>Mis Reservas</Link>
                        </li>
                    </ul>
                </li>
            </ul>

            {
                isMobile
                    ?
                    <p>¿Deseas <span className='header__logged-x' onClick={() => setAuth(false)}>cerrar sesión</span>?</p>
                    :
                    <FontAwesomeIcon onClick={handleLogout} icon={faXmark} className='header__faXmark' />
            }
        </div >
    )
}
