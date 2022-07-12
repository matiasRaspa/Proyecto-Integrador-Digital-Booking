import React, {  useState } from 'react'
import { AppRouter } from './routers/AppRouter'
import { UserContext } from './UserContext'

export const BookingApp = () => {


    const [auth, setAuth] = useState(
        localStorage.getItem('token') ? true : false
    );



    const [favourites, setFavourites] = useState([]);

    return (

        <UserContext.Provider value={{
            auth,
            setAuth,
            favourites,
            setFavourites
        }}>
            <AppRouter />

        </UserContext.Provider>

    )
}
