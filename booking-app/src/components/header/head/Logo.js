import React from 'react'
import { useMediaQuery } from 'react-responsive'
import { Link } from 'react-router-dom'
import logo from '../../../img/logo-db.png'
import lemaLogo from '../../../img/logo-lema.png'

export const Logo = () => {
    const isTabletOrMobile = useMediaQuery({ minWidth: 938 })

    return (
        <div>
            <Link to={'/'}>
                {
                    isTabletOrMobile ? < img src={lemaLogo} alt="Logo" /> : < img src={logo} alt="Logo" />
                }
            </Link>
        </div>

    )
}
