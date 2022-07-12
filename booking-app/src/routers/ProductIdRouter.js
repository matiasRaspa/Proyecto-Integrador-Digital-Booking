import React from 'react'
import { Route, Routes } from 'react-router-dom'
import App from '../App'
import { Booking } from '../components/booking/Booking'
import { Product } from '../components/product/Product'

export const ProductIdRouter = () => {
    return (
        <>
            <Routes>
                <Route path="*" element={<App />} />
                <Route path='/:id/' element={<Product />} />
                <Route path='/:id/booking' element={<Booking />} />
            </Routes>

        </>
    )
}
