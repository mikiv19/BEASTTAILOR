import React, { useState } from 'react';
import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import HomePage from './pages/HomePage';
import RegisterPage from './pages/RegisterPage';
import LoginPage from './pages/LoginPage';
import ShopPage from './pages/ShopPage';
import ItemDetailPage from './pages/ItemDetailPage';
import { Container, Button, Box, Typography } from '@mui/material';
import { useAuth } from './context/AuthContext';
import CartIcon from './components/CartIcon';
import CartDrawer from './components/CartDrawer';

const App: React.FC = () => {
    const { isAuthenticated, user, logout } = useAuth();
    const navigate = useNavigate();
    const [isCartOpen, setIsCartOpen] = useState(false);

    const handleLogout = () => {
        logout();
        navigate('/');
    };

    return (
        <Container>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', my: 2 }}>
                <Typography variant="h6" component={Link} to="/" sx={{ textDecoration: 'none', color: 'inherit' }}>
                    BeastTailor
                </Typography>
                <nav>
                    <Button component={Link} to="/shop">Shop</Button>
                    {isAuthenticated ? (
                        <>
                            <Typography component="span" sx={{ mx: 2 }}>
                                Welcome, {user?.username}!
                            </Typography>
                            <Button variant="outlined" onClick={handleLogout} sx={{ mr: 1 }}>
                                Logout
                            </Button>
                            <CartIcon onClick={() => setIsCartOpen(true)} />
                        </>
                    ) : (
                        <>
                            <Button component={Link} to="/login" sx={{ ml: 1 }}>
                                Login
                            </Button>
                            <Button component={Link} to="/register" variant="contained">
                                Register
                            </Button>
                        </>
                    )}
                </nav>
            </Box>
            <hr />
            <CartDrawer open={isCartOpen} onClose={() => setIsCartOpen(false)} />

            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/shop" element={<ShopPage />} />
                <Route path="/item/:id" element={<ItemDetailPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/login" element={<LoginPage />} />
            </Routes>
        </Container>
    );
};

export default App;