import React, { useState, useEffect } from 'react';
import { Typography, Button, Box, IconButton, Badge } from '@mui/material';
import { useTheme } from '@mui/material/styles';
import { NavLink, useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { useLocalCart } from '../context/LocalCartContext';
import PersonOutlineIcon from '@mui/icons-material/PersonOutline';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import axios from 'axios';

import CartDrawer from './CartDrawer';
import FavoritesDrawer from './FavoriteDrawer';
import { LeftNavShape, RightNavShape } from './NavShapes';

const Navbar: React.FC = () => {
    const { isAuthenticated, logout } = useAuth();
    const { cartItems } = useLocalCart();
    const navigate = useNavigate();
    const location = useLocation();
    const theme = useTheme();

    const [brands, setBrands] = useState<string[]>([]);
    const [isCartOpen, setIsCartOpen] = useState(false);
    const [isFavoritesOpen, setIsFavoritesOpen] = useState(false);

    useEffect(() => {
        const fetchBrands = async () => {
            try {
                const response = await axios.get<string[]>('http://localhost:8080/api/items/brands');
                setBrands(response.data);
            } catch (error) {
                console.error("Failed to fetch brands:", error);
            }
        };
        fetchBrands();
    }, []);

    const handleLogout = () => { logout(); navigate('/'); };

    const isBrandActive = (brand: string) => {
        return location.pathname === '/shop' && new URLSearchParams(location.search).get('brand') === brand;
    };

    const brandLinkStyle = (isActive: boolean) => ({
        color: isActive ? '#000' : 'text.secondary',
        backgroundColor: isActive ? '#fff' : 'rgba(255, 255, 255, 0.1)',
        textDecoration: 'none',
        padding: '6px 16px',
        margin: '0 4px',
        fontWeight: 'bold',
        borderRadius: '20px',
        transition: 'background-color 0.2s, color 0.2s',
        '&:hover': {
            backgroundColor: isActive ? '#e0e0e0' : 'rgba(255, 255, 255, 0.2)',
        }
    });

    return (
        <>
            <Box 
                component="header"
                sx={{
                    display: 'flex',
                    alignItems: 'center',
                    height: '60px',
                    position: 'sticky',
                    top: 0,
                    zIndex: theme.zIndex.appBar,
                    paddingX: { xs: 2, md: 0 },
                    backgroundColor: 'rgba(0, 0, 0, 0.5)',
                }}
            >
                <Box sx={{ 
                    position: 'relative', 
                    height: '100%', 
                    display: 'flex', 
                    alignItems: 'center', 
                    justifyContent: 'flex-start',
                    paddingLeft: '24px', 
                    paddingRight: '48px',
                    backdropFilter: 'blur(10px)',
                    WebkitBackdropFilter: 'blur(10px)',
                    flex: '0 0 20%',
                }}>
                    <LeftNavShape />
                    <Typography variant="h6" component={NavLink} to="/" sx={{ color: 'text.primary', textDecoration: 'none', fontWeight: 'bold', whiteSpace: 'nowrap' }}>
                        BeastTailor
                    </Typography>
                </Box>

                <Box sx={{ 
                    flex: '0 0 60%',
                    display: 'flex', 
                    justifyContent: 'center', 
                    height: '100%', 
                    alignItems: 'center',
                    gap: '5vw',

                }}>
                    {brands.map(brand => (
                        <Button key={brand} component={NavLink} to={`/shop?brand=${encodeURIComponent(brand)}`} sx={brandLinkStyle(isBrandActive(brand))}>
                            {brand}
                        </Button>
                    ))}
                </Box>

                <Box sx={{ 
                    position: 'relative', 
                    height: '100%', 
                    display: 'flex', 
                    alignItems: 'center', 
                    justifyContent: 'flex-end',
                    gap: 1, 
                    paddingLeft: '48px', 
                    paddingRight: '24px',
                    backdropFilter: 'blur(10px)',
                    WebkitBackdropFilter: 'blur(10px)',
                    flex: '0 0 20%',
                }}>
                    <RightNavShape />
                    {isAuthenticated ? (
                        <>
                            <IconButton title="Favorites" color="inherit" onClick={() => setIsFavoritesOpen(true)}><FavoriteBorderIcon /></IconButton>
                            <IconButton title="Cart" color="inherit" onClick={() => setIsCartOpen(true)}>
                                <Badge badgeContent={cartItems.length} color="primary"><ShoppingCartIcon /></Badge>
                            </IconButton>
                            <IconButton title="Profile" color="inherit" onClick={() => navigate('/profile')}><PersonOutlineIcon /></IconButton>
                            <Button variant="outlined" color="inherit" onClick={handleLogout}>Logout</Button>
                        </>
                    ) : (
                        <>
                           <IconButton title="Cart" color="inherit" onClick={() => setIsCartOpen(true)}>
                                <Badge badgeContent={cartItems.length} color="primary"><ShoppingCartIcon /></Badge>
                            </IconButton>
                            <Button color="inherit" component={NavLink} to="/login">Login</Button>
                            <Button variant="contained" component={NavLink} to="/register">Register</Button>
                        </>
                    )}
                </Box>
            </Box>
            
            <CartDrawer open={isCartOpen} onClose={() => setIsCartOpen(false)} />
            {isAuthenticated && <FavoritesDrawer open={isFavoritesOpen} onClose={() => setIsFavoritesOpen(false)} />}
        </>
    );
};

export default Navbar;