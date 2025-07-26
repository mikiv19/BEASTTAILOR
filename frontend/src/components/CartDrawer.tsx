import React from 'react';
import { Drawer, Box, Typography, List, ListItem, ListItemText, Button, Divider, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { useCart } from '../context/CartContext';

interface CartDrawerProps {
    open: boolean;
    onClose: () => void;
}

const CartDrawer: React.FC<CartDrawerProps> = ({ open, onClose }) => {
    const { cartItems, removeFromCart } = useCart();
    const totalPrice = cartItems.reduce((total, cartItem) => 
        total + cartItem.clothingItem.basePrice * cartItem.quantity, 0);

    return (
        <Drawer anchor="right" open={open} onClose={onClose}>
            <Box sx={{ width: 350, padding: 2 }} role="presentation">
                <Typography variant="h5" sx={{ mb: 2 }}>Your Cart</Typography>
                <Divider />
                
                {cartItems.length === 0 ? (
                    <Typography sx={{ mt: 2 }}>Your cart is empty.</Typography>
                ) : (
                    <List>
                        {cartItems.map(cartItem => (
                            <ListItem 
                                key={cartItem.id} 
                                disableGutters
                                secondaryAction={
                                    <IconButton 
                                        edge="end" 
                                        aria-label="delete" 
                                        onClick={() => removeFromCart(cartItem.id)}
                                    >
                                        <DeleteIcon />
                                    </IconButton>
                                }
                            >
                                <ListItemText
                                    primary={`${cartItem.clothingItem.name} (x${cartItem.quantity})`}
                                    secondary={`${(cartItem.clothingItem.basePrice * cartItem.quantity).toFixed(2)} GP`}
                                />
                            </ListItem>
                        ))}
                        <Divider sx={{ my: 2 }}/>
                        <Typography variant="h6">
                            Total: {totalPrice.toFixed(2)} GP
                        </Typography>
                    </List>
                )}

                <Button 
                    variant="contained" 
                    fullWidth 
                    sx={{ mt: 2 }}
                    onClick={() => alert('Checkout is not implemented yet!')}
                    disabled={cartItems.length === 0}
                >
                    Proceed to Checkout
                </Button>
            </Box>
        </Drawer>
    );
};

export default CartDrawer;