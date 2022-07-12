import React, { useContext, useEffect } from 'react'
import { useMediaQuery } from 'react-responsive'
import { UserContext } from '../../UserContext'
import { Access } from './head/Access'
import { Burguer } from './head/Burguer'
import { LoggedIn } from './head/LoggedIn'
import { Logo } from './head/Logo'

export const Header = () => {

    const { auth } = useContext(UserContext);
    

    useEffect(() => {
        
    }, [auth]);

    const isMobile = useMediaQuery({ maxWidth: 480 })
    const isTabletOrDesktop = useMediaQuery({ minWidth: 481 });

    const getInfoForRender = () => {

        if (isTabletOrDesktop && auth) {
            return <LoggedIn />
        }

        if (isTabletOrDesktop) {
            return <Access />
        }
        if (isMobile) {
            return <Burguer />
        }

    }

    return (
        <header>
            <div className='header__logo'>
                <Logo />
                {getInfoForRender()}
            </div>

        </header>
    )
}




