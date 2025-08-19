import React, { useState, useEffect } from 'react';
import { useParams, Link as RouterLink, useNavigate } from 'react-router-dom';
import apiClient from '../api/axiosConfig'; 
import { Container, Typography, Box, CircularProgress, Alert, Grid, Paper,Button,IconButton,Stack,Divider} from '@mui/material';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import { useAuth } from '../context/AuthContext';
import { useLocalCart } from '../context/LocalCartContext';
import { useFavorite } from '../context/FavoriteContext';
import type { ClothingItem } from '../types';

const ItemDetailPage: React.FC = () => {
    const navigate = useNavigate();
    const { id } = useParams<{ id: string }>();
    
    const { isAuthenticated } = useAuth();
    const { addToCart } = useLocalCart();
    const { addToFavorite } = useFavorite();
    
    const [item, setItem] = useState<ClothingItem | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (!id) return;
        const fetchItem = async () => {
            try {
                setLoading(true);
                const response = await apiClient.get<ClothingItem>(`/api/items/${id}`);
                setItem(response.data);
            } catch (err) {
                setError(`Failed to find item with ID ${id}. It may have been sold!`);
                setTimeout(() => {
                        navigate('/');
                    }, 3000);
            } finally {
                setLoading(false);
            }
        };
        fetchItem();
    }, [id]);

    if (loading) {
        return <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}><CircularProgress /></Box>;
    }
    if (error) {
        return <Alert severity="error" sx={{ mt: 4 }}>{error}</Alert>;
    }
    if (!item) {
        return <Alert severity="warning" sx={{ mt: 4 }}>Item not found.</Alert>;
    }

    const handleAddToCart = () => {
        addToCart(item);
        alert(`${item.name} added to cart!`);
    };

    const handleAddToFavorites = () => {
        addToFavorite(item);
        alert(`${item.name} added to favorites!`);
    };

    return (
        <Container sx={{ mt: 4, mb: 4 }}>
            <Button component={RouterLink} to="/shop" sx={{ mb: 2 }}>
                &larr; Back to Shop
            </Button>
            <Paper elevation={3} sx={{ p: 4 }}>
                <Grid container spacing={4}>
                    <Grid size={{ xs: 12, md: 6 }}>
                        <Box
                            component="img"
                            src={item.imageUrlDetail}
                            alt={item.name}
                            sx={{ width: '100%', height: 'auto', borderRadius: 2 }}
                        />
                    </Grid>
                    <Grid size={{ xs: 12, md: 6 }}>
                        <Typography variant="h3" component="h1" gutterBottom>{item.name}</Typography>
                        <Typography variant="h5" color="text.secondary" gutterBottom>{item.brand} - ({item.itemSlot})</Typography>
                        <Typography variant="body1" sx={{ my: 2 }}>{item.description}</Typography>
                        <Typography variant="h4" component="p" sx={{ my: 3 }}>{item.basePrice.toFixed(2)} GP</Typography>
                        
                        <Divider sx={{ my: 2 }} />

                        <Box>
                            <Typography variant="h6" gutterBottom>Stats & Properties:</Typography>
                            {item.acBonus > 0 && <Typography>+ {item.acBonus} Armor Class</Typography>}
                            {item.strBonus > 0 && <Typography>+ {item.strBonus} Strength</Typography>}
                            {item.dexBonus > 0 && <Typography>+ {item.dexBonus} Dexterity</Typography>}
                            {item.conBonus > 0 && <Typography>+ {item.conBonus} Constitution</Typography>}
                            {item.intBonus > 0 && <Typography>+ {item.intBonus} Intelligence</Typography>}
                            {item.wisBonus > 0 && <Typography>+ {item.wisBonus} Wisdom</Typography>}
                            {item.chaBonus > 0 && <Typography>+ {item.chaBonus} Charisma</Typography>}
                            {item.specialProperties && <Typography sx={{ mt: 1, fontStyle: 'italic' }}>"{item.specialProperties}"</Typography>}
                        </Box>

                        <Stack direction="row" spacing={2} sx={{ mt: 4 }}>
                            <Button variant="contained" color="primary" size="large" onClick={handleAddToCart}>
                                Add to Cart
                            </Button>
                            {isAuthenticated && (
                                <IconButton color="primary" size="large" onClick={handleAddToFavorites} aria-label="add to favorites">
                                    <FavoriteBorderIcon />
                                </IconButton>
                            )}
                        </Stack>
                    </Grid>
                </Grid>
            </Paper>
        </Container>
    );
};

export default ItemDetailPage;