import React from 'react'
import { useLocation, useNavigate } from 'react-router-dom';


export const Access = () => {
    const navigate = useNavigate();
    const location = useLocation();

    const handleRegister = () => {
        return navigate('/register')
    }

    const handleLogin = () => {
        return navigate('/login')
    }

    const viewButtoms = {
        'default': <>
            <button className='header__button' onClick={handleRegister}> Crear cuenta</button>
            <hr className='header__line' />
            <button className='header__button' onClick={handleLogin}>Iniciar sesión</button>
        </>,
        '/login': <button className='header__button' onClick={handleRegister}> Crear cuenta</button>,
        '/register': <button className='header__button' onClick={handleLogin}>Iniciar sesión</button>
    }

    return (
        <div>
            {viewButtoms[location.pathname] || viewButtoms['default']}
        </div>
    )
}

