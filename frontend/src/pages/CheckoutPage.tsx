import React, { useState } from 'react';
import { Container, Typography, Button, List, ListItem, ListItemText, Box, Paper, Divider, CircularProgress, Alert } from '@mui/material';
import { useLocalCart } from '../context/LocalCartContext';
import { useAuth } from '../context/AuthContext';
import apiClient from '../api/axiosConfig';
import { useNavigate } from 'react-router-dom';

interface HaggleResult {
    itemId: number;
    itemName: string;
    basePrice: number;
    diceRoll: number;
    statBonus: number;
    finalPrice: number;
    discountPercentage: number;
}

const CheckoutPage: React.FC = () => {
    const { cartItems, clearCart } = useLocalCart();
    const { isAuthenticated } = useAuth();
    const navigate = useNavigate();
    
    const [haggleResults, setHaggleResults] = useState<HaggleResult[]>([]);
    const [isHaggling, setIsHaggling] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const baseTotalPrice = cartItems.reduce((total, item) => total + item.basePrice * item.quantity, 0);

    const handleHaggle = async () => {
        setError(null);
        setIsHaggling(true);
        try {
            const cartForApi = cartItems.map(item => ({ 
                itemId: item.id, 
                quantity: item.quantity 
            }));
            
            const response = await apiClient.post<HaggleResult[]>('/api/checkout/haggle', cartForApi);
            setHaggleResults(response.data);
        } catch (err) {
            setError("The haggle failed. The shopkeeper is unimpressed.");
            console.error(err);
        } finally {
            setIsHaggling(false);
        }
    };

    const handleConfirmPurchase = async () => {
        if (!isAuthenticated) {
            alert("Please log in to purchase items.");
            navigate('/login');
            return;
        }
        
        try {
            const itemIds = cartItems.map(item => item.id);
            await apiClient.post('/api/wardrobe/purchase', itemIds);
            alert("Purchase successful! Items have been added to your wardrobe.");
            clearCart();
            navigate('/profile');
        } catch (err) {
            setError("There was an error with your purchase. Please try again.");
            console.error("Purchase error:", err);
        }
    };

    const finalTotalPrice = haggleResults.length > 0 
        ? haggleResults.reduce((sum, result) => sum + result.finalPrice, 0) 
        : baseTotalPrice;

    return (
        <Container maxWidth="md" sx={{ mt: 4, mb: 4 }}>
            <Paper elevation={3} sx={{ p: 4 }}>
                <Typography variant="h4" component="h1" gutterBottom>
                    Checkout
                </Typography>
                <Divider sx={{ mb: 2 }} />

                {cartItems.length === 0 ? (
                    <Typography>Your cart is empty.</Typography>
                ) : (
                    <Box>
                        <Typography variant="h6">Review Your Order:</Typography>
                        <List>
                            {cartItems.map(item => {
                                const result = haggleResults.find(r => r.itemId === item.id);
                                return (
                                    <ListItem key={item.id}>
                                        <ListItemText 
                                            primary={`${item.name} (x${item.quantity})`}
                                            secondary={
                                                result ? 
                                                `Rolled ${result.diceRoll} + ${result.statBonus} CHA = ${result.discountPercentage.toFixed(0)}% off!` 
                                                : 'Awaiting haggle...'
                                            } 
                                        />
                                        <Typography variant="body1">
                                            {result ? 
                                                <>
                                                    <Box component="span" sx={{ textDecoration: 'line-through', mr: 1 }}>
                                                        {item.basePrice.toFixed(2)} GP
                                                    </Box>
                                                    <strong>{result.finalPrice.toFixed(2)} GP</strong>
                                                </>
                                                : `${item.basePrice.toFixed(2)} GP`
                                            }
                                        </Typography>
                                    </ListItem>
                                );
                            })}
                        </List>
                        <Divider sx={{ my: 2 }} />
                        <Typography variant="h5" align="right" sx={{ mt: 2 }}>
                            Total: {finalTotalPrice.toFixed(2)} GP
                        </Typography>

                        {error && <Alert severity="error" sx={{ mt: 2 }}>{error}</Alert>}

                        <Box sx={{ mt: 3, display: 'flex', justifyContent: 'flex-end', gap: 2, alignItems: 'center' }}>
                            {!isAuthenticated && (
                                <Typography color="text.secondary">
                                    Log in to haggle for better prices!
                                </Typography>
                            )}
                            
                            {isAuthenticated && haggleResults.length === 0 && (
                                <Button variant="contained" color="primary" onClick={handleHaggle} disabled={isHaggling}>
                                    {isHaggling ? <CircularProgress size={24} /> : "Haggle for Prices"}
                                </Button>
                            )}

                            <Button 
                                variant="contained" 
                                color="success" 
                                onClick={handleConfirmPurchase} 
                                disabled={isHaggling || (isAuthenticated && haggleResults.length === 0)}
                            >
                                Confirm Purchase
                            </Button>
                        </Box>
                    </Box>
                )}
            </Paper>
        </Container>
    );
};

export default CheckoutPage;