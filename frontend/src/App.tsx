import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { Box, Container } from '@mui/material'; // Import Box as well

// Import all your components
import Navbar from './components/navbar';
import HomePage from './pages/HomePage';
import RegisterPage from './pages/RegisterPage';
import LoginPage from './pages/LoginPage';
import ShopPage from './pages/ShopPage';
import ItemDetailPage from './pages/ItemDetailPage';
import ProfilePage from './pages/ProfilePage'; 
import CheckoutPage from './pages/CheckoutPage';

const App: React.FC = () => {
    return (
        // We use a Box as the main app wrapper instead of the theme, 
        // as you already have the background set in your theme's CssBaseline.
        <Box>
            {/* The Navbar is now a direct child of the root Box, with NO container. */}
            {/* It will be full-width by default. */}
            <Navbar /> 

            <main>
                {/* The Container ONLY wraps the page content (Routes). */}
                {/* This adds the padding and max-width to your pages but not the navbar. */}
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