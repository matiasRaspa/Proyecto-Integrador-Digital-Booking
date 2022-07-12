import React from 'react'
import { Routes, Route, BrowserRouter } from "react-router-dom";
import App from '../App';
import { LoginScreen } from '../components/auth/LoginScreen';
import { RegisterScreen } from '../components/auth/RegisterScreen';
import { Layout } from '../components/layout/Layout';
import { ProductIdRouter } from './ProductIdRouter';
import { Success } from '../components/modals/success/Success';
import { MyBookings } from '../components/my-bookings/MyBookings';
import { CreateProduct } from '../components/createProduct/CreateProduct';

export const AppRouter = () => {

    return (
        <BrowserRouter>
            <Layout>
                <Routes>
                    <Route path='/' element={<App />} />
                    <Route path="*" element={<App />} />
                    <Route path='/login' element={<LoginScreen />} />
                    <Route path='/register' element={<RegisterScreen />} />
                    <Route path='/product/*' element={<ProductIdRouter />} />
                    <Route path='/success' element={<Success />} />
                    <Route path='/:userId/bookings' element={<MyBookings />} />
                    <Route path='/administracion' element={<CreateProduct />} />
                </Routes>
            </Layout>
        </BrowserRouter>
    )
}
