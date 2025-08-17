import React from 'react';
import { Drawer, Box, Typography, List, ListItem, ListItemText, Button, Divider, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { useLocalCart } from '../context/LocalCartContext';
import { Link as RouterLink } from 'react-router-dom';

interface CartDrawerProps {
    open: boolean;
    onClose: () => void;
}

const CartDrawer: React.FC<CartDrawerProps> = ({ open, onClose }) => {
    const { cartItems, removeFromCart, cartItemCount } = useLocalCart();
    const totalPrice = cartItems.reduce((total, item) => total + item.basePrice * item.quantity, 0);

    return (
        <Drawer anchor="right" open={open} onClose={onClose}>
            <Box sx={{ width: 350, padding: 2 }} role="presentation">
                <Typography variant="h5" sx={{ mb: 2 }}>Shopping Cart</Typography>
                <Divider />
                {cartItems.length === 0 ? (
                    <Typography sx={{ mt: 2 }}>Your cart is empty.</Typography>
                ) : (
                    <List>
                        {cartItems.map(item => (
                            <ListItem key={item.id} disableGutters secondaryAction={
                                <IconButton edge="end" onClick={() => removeFromCart(item.id)}>
                                    <DeleteIcon />
                                </IconButton>
                            }>
                                <ListItemText
                                    primary={`${item.name} (x${item.quantity})`}
                                    secondary={`${(item.basePrice * item.quantity).toFixed(2)} GP`}
                                />
                            </ListItem>
                        ))}
                        <Divider sx={{ my: 2 }}/>
                        <Typography variant="h6">Total: {totalPrice.toFixed(2)} GP</Typography>
                    </List>
                )}
                <Button 
                    variant="contained" 
                    fullWidth 
                    sx={{ mt: 2 }} 
                    disabled={cartItemCount === 0}
                    component={RouterLink}
                    to="/checkout"
                    onClick={onClose}
                >
                    Proceed to Checkout
                </Button>
            </Box>
        </Drawer>
    );
};

export default CartDrawer;