import React from 'react';
import { IconButton, Badge } from '@mui/material';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { useFavorite } from '../context/FavoriteContext';

interface FavoritesIconProps {
    onClick: () => void;
}

const FavoritesIcon: React.FC<FavoritesIconProps> = ({ onClick }) => {
    const { favoriteItemCount } = useFavorite();

    return (
        <IconButton color="inherit" onClick={onClick}>
            <Badge badgeContent={favoriteItemCount} color="error">
                <FavoriteIcon />
            </Badge>
        </IconButton>
    );
};

export default FavoritesIcon;