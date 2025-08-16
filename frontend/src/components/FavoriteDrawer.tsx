import React from 'react';
import { Drawer, Box, Typography, List, ListItem, ListItemText, Button, Divider, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { useFavorite } from '../context/FavoriteContext';

interface FavoriteDrawerProps {
    open: boolean;
    onClose: () => void;
}

const FavoriteDrawer: React.FC<FavoriteDrawerProps> = ({ open, onClose }) => {
    const { favoriteItems, removeFromFavorite } = useFavorite();
    const totalPrice = favoriteItems.reduce((total, favoriteItem) => 
        total + favoriteItem.clothingItem.basePrice * favoriteItem.quantity, 0);

    return (
        <Drawer anchor="right" open={open} onClose={onClose}>
            <Box sx={{ width: 350, padding: 2 }} role="presentation">
                <Typography variant="h5" sx={{ mb: 2 }}>Your Favorite</Typography>
                <Divider />
                
                {favoriteItems.length === 0 ? (
                    <Typography sx={{ mt: 2 }}>Your favorite is empty.</Typography>
                ) : (
                    <List>
                        {favoriteItems.map(favoriteItem => (
                            <ListItem 
                                key={favoriteItem.id} 
                                disableGutters
                                secondaryAction={
                                    <IconButton 
                                        edge="end" 
                                        aria-label="delete" 
                                        onClick={() => removeFromFavorite(favoriteItem.id)}
                                    >
                                        <DeleteIcon />
                                    </IconButton>
                                }
                            >
                                <ListItemText
                                    primary={`${favoriteItem.clothingItem.name} (x${favoriteItem.quantity})`}
                                    secondary={`${(favoriteItem.clothingItem.basePrice * favoriteItem.quantity).toFixed(2)} GP`}
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
                    disabled={favoriteItems.length === 0}
                >
                    Proceed to Checkout
                </Button>
            </Box>
        </Drawer>
    );
};

export default FavoriteDrawer;