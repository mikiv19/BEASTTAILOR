import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { Box, Container } from '@mui/material'; 

import Navbar from './components/Navbar';
import HomePage from './pages/HomePage';
import RegisterPage from './pages/RegisterPage';
import LoginPage from './pages/LoginPage';
import ShopPage from './pages/ShopPage';
import ItemDetailPage from './pages/ItemDetailPage';
import ProfilePage from './pages/ProfilePage'; 
import CheckoutPage from './pages/CheckoutPage';

const App: React.FC = () => {
    return (
        <Box>
            <Navbar /> 
            <main>
                <Container sx={{ mt: 4, mb: 4 }}>
                    <Routes>
                        <Route path="/" element={<HomePage />} />
                        <Route path="/shop" element={<ShopPage />} />
                        <Route path="/item/:id" element={<ItemDetailPage />} />
                        <Route path="/register" element={<RegisterPage />} />
                        <Route path="/login" element={<LoginPage />} />
                        <Route path="/profile" element={<ProfilePage />} />
                        <Route path="/checkout" element={<CheckoutPage />} />
                    </Routes>
                </Container>
            </main>
        </Box>
    );
};

export default App;