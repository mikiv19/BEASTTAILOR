import React, { useState, useEffect } from 'react';
import apiClient from '../api/axiosConfig'; 
import { Grid, Card, CardMedia, CardContent, Typography, CircularProgress, Box, Alert, Container } from '@mui/material';
import { Link, useSearchParams } from 'react-router-dom';
import type { ClothingItem } from '../types';

const ShopPage: React.FC = () => {
    const [items, setItems] = useState<ClothingItem[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);
    const [searchParams] = useSearchParams();

    useEffect(() => {
        const fetchItems = async () => {
            const brand = searchParams.get('brand');
            let apiUrl = '/api/items'; 
            if (brand) {
                apiUrl += `?brand=${encodeURIComponent(brand)}`;
            }

            try {
                setLoading(true);
                const response = await apiClient.get<ClothingItem[]>(apiUrl);
                setItems(response.data);
            } catch (err) {
                setError('Failed to fetch items from the shop. The armory might be closed!');
                console.error("Error fetching items:", err);
            } finally {
                setLoading(false);
            }
        };

        fetchItems();
    }, [searchParams]);

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

    return (
        <Container sx={{ mt: 4, mb: 4 }}>
            <Grid container spacing={4}>
                {items.map((item) => (
                    
                    <Grid key={item.id} size={{ xs: 12, sm: 6, md: 3 }}>
                        <Link to={`/item/${item.id}`} style={{ textDecoration: 'none' }}>
                            <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column', transition: 'transform 0.2s', '&:hover': { transform: 'scale(1.03)' } }}>
                                <CardMedia
                                    component="img"
                                    height="250"
                                    image={item.imageUrlThumbnail}
                                    alt={item.name}
                                    sx={{ p: 1, objectFit: 'contain' }}
                                />
                                <CardContent sx={{ flexGrow: 1 }}>
                                    <Typography gutterBottom variant="h5" component="div" color="text.primary">
                                        {item.name}
                                    </Typography>
                                    <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                                        {item.brand}
                                    </Typography>
                                    <Typography variant="h6" component="p" color="text.primary">
                                        {item.basePrice.toFixed(2)} GP
                                    </Typography>
                                </CardContent>
                            </Card>
                        </Link>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
};

export default ShopPage;