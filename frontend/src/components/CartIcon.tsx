import React from 'react';
import { IconButton, Badge } from '@mui/material';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { useCart } from '../context/CartContext';

interface CartIconProps {
    onClick: () => void;
}

const CartIcon: React.FC<CartIconProps> = ({ onClick }) => {
    const { cartItemCount } = useCart();

    return (
        <IconButton color="inherit" onClick={onClick}>
            <Badge badgeContent={cartItemCount} color="error">
                <ShoppingCartIcon />
            </Badge>
        </IconButton>
    );
};

export default CartIcon;