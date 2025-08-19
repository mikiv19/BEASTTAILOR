import React from 'react';
import { Drawer, Box, Typography, List, ListItem, ListItemText, Divider, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { useFavorite } from '../context/FavoriteContext';

interface FavoritesDrawerProps {
    open: boolean;
    onClose: () => void;
}

const FavoritesDrawer: React.FC<FavoritesDrawerProps> = ({ open, onClose }) => {
    const { favoriteItems, removeFromFavorite } = useFavorite();

    return (
        <Drawer anchor="right" open={open} onClose={onClose}>
            <Box sx={{ width: 350, padding: 2 }} role="presentation">
                <Typography variant="h5" sx={{ mb: 2 }}>Your Favorites</Typography>
                <Divider />
                {favoriteItems.length === 0 ? (
                    <Typography sx={{ mt: 2 }}>You have no favorite items.</Typography>
                ) : (
                    <List>
                        {favoriteItems.map(item => (
                            <ListItem key={item.id} disableGutters secondaryAction={
                                <IconButton edge="end" onClick={() => removeFromFavorite(item.id)}>
                                    <DeleteIcon />
                                </IconButton>
                            }>
                                <ListItemText primary={item.clothingItem.name} />
                            </ListItem>
                        ))}
                    </List>
                )}
            </Box>
        </Drawer>
    );
};

export default FavoritesDrawer;