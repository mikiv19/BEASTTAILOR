import { IconButton, Badge } from '@mui/material';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { useLocalCart } from '../context/LocalCartContext';

interface CartIconProps {
    onClick: () => void;
}

const CartIcon: React.FC<CartIconProps> = ({ onClick }) => {
    const { cartItemCount } = useLocalCart();

    return (
        <IconButton color="inherit" onClick={onClick}>
            <Badge badgeContent={cartItemCount} color="primary">
                <ShoppingCartIcon />
            </Badge>
        </IconButton>
    );
};

export default CartIcon;