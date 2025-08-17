import React from 'react';
import { Container, Typography, Button, List, ListItem, ListItemText, Box } from '@mui/material';
import { useLocalCart } from '../context/LocalCartContext';
import { useAuth } from '../context/AuthContext';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const CheckoutPage: React.FC = () => {
    const { cartItems, clearCart } = useLocalCart();
    const { isAuthenticated } = useAuth();
    const navigate = useNavigate();

    const handlePurchase = async () => {
        if (!isAuthenticated) {
            alert("Please log in to purchase items.");
            navigate('/login');
            return;
        }
        
        const itemIds = cartItems.map(item => item.id);

        try {
            // Call our new "purchase" endpoint
            await axios.post('http://localhost:8080/api/wardrobe/purchase', itemIds);
            alert("Purchase successful! Items have been added to your wardrobe.");
            clearCart();
            navigate('/profile');
        } catch (error) {
            alert("There was an error with your purchase. Please try again.");
            console.error("Purchase error:", error);
        }
    };

    const totalPrice = cartItems.reduce((total, item) => total + item.basePrice * item.quantity, 0);

    return (
        <Container sx={{ mt: 4, mb: 4 }}>
            <Typography variant="h4" component="h1" gutterBottom>
                Checkout
            </Typography>
            <Box>
                <Typography variant="h6">Review Your Order:</Typography>
                <List>
                    {cartItems.map(item => (
                        <ListItem key={item.id}>
                            <ListItemText 
                                primary={`${item.name} (x${item.quantity})`}
                                secondary={`${item.basePrice.toFixed(2)} GP`}
                            />
                        </ListItem>
                    ))}
                </List>
                <Typography variant="h5" sx={{ mt: 2 }}>
                    Total: {totalPrice.toFixed(2)} GP
                </Typography>
                <Button
                    variant="contained"
                    color="primary"
                    sx={{ mt: 2 }}
                    onClick={handlePurchase}
                    disabled={cartItems.length === 0}
                >
                    Confirm Purchase
                </Button>
            </Box>
        </Container>
    );
};

export default CheckoutPage;