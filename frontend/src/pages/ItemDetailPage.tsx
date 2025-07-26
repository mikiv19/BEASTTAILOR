import React, { useState, useEffect } from 'react';
import { useParams, Link as RouterLink } from 'react-router-dom';
import axios from 'axios';
import { Container, Typography, Box, CircularProgress, Alert, Grid, Paper,Button } from '@mui/material';
import type { ClothingItem } from '../types'
import { useCart } from '../context/CartContext'; 


const ItemDetailPage: React.FC = () => {
    // useParams hook gets the 'id' from the URL (e.g., /item/1)
    const { id } = useParams<{ id: string }>();
    const [item, setItem] = useState<ClothingItem | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);
    const { addToCart } = useCart();

    useEffect(() => {
        if (!id) return;

        const fetchItem = async () => {
            try {
                setLoading(true);
                const response = await axios.get<ClothingItem>(`http://localhost:8080/api/items/${id}`);
                setItem(response.data);
            } catch (err) {
                setError(`Failed to find item with ID ${id}. It may have been sold!`);
                console.error("Error fetching item:", err);
            } finally {
                setLoading(false);
            }
        };

        fetchItem();
    }, [id]);
    if (loading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '80vh' }}>
                <CircularProgress />
            </Box>
        );
    }

    if (error) {
        return <Alert severity="error" sx={{ mt: 4 }}>{error}</Alert>;
    }

    if (!item) {
        return <Alert severity="warning" sx={{ mt: 4 }}>Item not found.</Alert>;
    }

    return (
        <Container sx={{ mt: 4, mb: 4 }}>
            <Button component={RouterLink} to="/shop" sx={{ mb: 2 }}>
                ← Back to Shop
            </Button>
            <Paper elevation={3} sx={{ p: 4 }}>
                <Grid container spacing={4}>
                    <Grid xs={12} md={6}>
                        <Box
                            component="img"
                            src={item.imageUrlDetail} // Use the larger detail image
                            alt={item.name}
                            sx={{ width: '100%', height: 'auto', borderRadius: 2 }}
                        />
                    </Grid>
                    <Grid xs={12} md={6}>
                        <Typography variant="h3" component="h1" gutterBottom>
                            {item.name}
                        </Typography>
                        <Typography variant="h5" color="text.secondary" gutterBottom>
                            {item.brand} - ({item.itemSlot})
                        </Typography>
                        <Typography variant="body1" sx={{ my: 2 }}>
                            {item.description}
                        </Typography>
                        <Typography variant="h4" component="p" sx={{ my: 3 }}>
                            {item.basePrice.toFixed(2)} GP
                        </Typography>
                        <Box>
                            <Typography variant="h6" gutterBottom>Stats & Properties:</Typography>
                            {item.acBonus > 0 && <Typography>+ {item.acBonus} Armor Class</Typography>}
                            {item.strBonus > 0 && <Typography>+ {item.strBonus} Strength</Typography>}
                            {item.dexBonus > 0 && <Typography>+ {item.dexBonus} Dexterity</Typography>}
                            {item.conBonus > 0 && <Typography>+ {item.conBonus} Constitution</Typography>}
                            {item.intBonus > 0 && <Typography>+ {item.intBonus} Intelligence</Typography>}
                            {item.wisBonus > 0 && <Typography>+ {item.wisBonus} Wisdom</Typography>}
                            {item.chaBonus > 0 && <Typography>+ {item.chaBonus} Charisma</Typography>}
                            {item.specialProperties && <Typography><i>{item.specialProperties}</i></Typography>}
                        </Box>
                        <Button variant="contained" color="primary" size="large" sx={{ mt: 4 }} onClick={() => addToCart(item)}>
                            Add to Cart
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Container>
    );
};

export default ItemDetailPage;