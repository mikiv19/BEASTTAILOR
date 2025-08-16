import React from 'react';
import { IconButton, Badge } from '@mui/material';
import ShoppingFavoriteIcon from '@mui/icons-material/Favorite';
import { useFavorite } from '../context/FavoriteContext';

interface FavoriteIconProps {
    onClick: () => void;
}

const FavoriteIcon: React.FC<FavoriteIconProps> = ({ onClick }) => {
    const { favoriteItemCount } = useFavorite();

    return (
        <IconButton color="inherit" onClick={onClick}>
            <Badge badgeContent={favoriteItemCount} color="error">
                <ShoppingFavoriteIcon />
            </Badge>
        </IconButton>
    );
};

export default FavoriteIcon;