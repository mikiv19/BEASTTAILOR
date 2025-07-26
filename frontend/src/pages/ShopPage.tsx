// File: frontend/src/pages/ShopPage.tsx

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { 
    Grid, 
    Card, 
    CardMedia, 
    CardContent, 
    Typography, 
    CircularProgress, 
    Box, 
    Alert, 
    Container 
} from '@mui/material';

// Define a detailed interface for our ClothingItem data to get full TypeScript support.
// This should match the structure of the data coming from your Java backend.
interface ClothingItem {
    id: number;
    name: string;
    description: string;
    brand: string;
    basePrice: number;
    itemSlot: string;
    imageUrlThumbnail: string;
    imageUrlDetail: string;
    strBonus: number;
    dexBonus: number;
    conBonus: number;
    intBonus: number;
    wisBonus: number;
    chaBonus: number;
    acBonus: number;
    specialProperties: string | null;
}

const ShopPage: React.FC = () => {
    // State for storing the list of items from the API
    const [items, setItems] = useState<ClothingItem[]>([]);
    // State to handle the loading indicator
    const [loading, setLoading] = useState<boolean>(true);
    // State to handle any potential errors during fetching
    const [error, setError] = useState<string | null>(null);

    // useEffect is used here to perform a "side effect": fetching data from an API.
    // This effect runs only once when the component first mounts, thanks to the empty dependency array [].
    useEffect(() => {
        const fetchItems = async () => {
            try {
                // Set loading to true before we start the fetch
                setLoading(true);
                // Make the GET request to our backend's public item endpoint
                const response = await axios.get<ClothingItem[]>('http://localhost:8080/api/items');
                // On success, update the 'items' state with the data from the API
                setItems(response.data);
            } catch (err) {
                // If an error occurs, set a user-friendly error message
                setError('Failed to fetch items from the shop. The armory might be closed!');
                console.error("Error fetching items:", err);
            } finally {
                // No matter if it succeeded or failed, stop the loading indicator
                setLoading(false);
            }
        };

        fetchItems();
    }, []); // The empty dependency array [] is crucial. It tells React to run this effect only once.

    // --- Conditional Rendering ---
    // Show a loading spinner while the data is being fetched.
    if (loading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '80vh' }}>
                <CircularProgress />
            </Box>
        );
    }

    // Show an error message if the fetch failed.
    if (error) {
        return <Alert severity="error" sx={{ mt: 4 }}>{error}</Alert>;
    }

    // --- Main Content ---
    // If loading is false and there is no error, render the product catalog.
    return (
        <Container sx={{ mt: 4, mb: 4 }}>
            <Typography variant="h4" component="h1" gutterBottom align="center">
                The BeastTailor Catalog
            </Typography>
            <Grid container spacing={4}>
                {items.map((item) => (
                    <Grid item key={item.id} xs={12} sm={6} md={4}>
                        <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
                            <CardMedia
                                component="img"
                                height="250"
                                // NOTE: This will show a broken image icon until we add the actual image files.
                                image={item.imageUrlThumbnail}
                                alt={item.name}
                                sx={{ p: 1, objectFit: 'contain' }} // 'contain' ensures the whole image is visible
                            />
                            <CardContent sx={{ flexGrow: 1 }}>
                                <Typography gutterBottom variant="h5" component="div">
                                    {item.name}
                                </Typography>
                                <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                                    {item.brand}
                                </Typography>
                                <Typography variant="h6" component="p">
                                    {item.basePrice.toFixed(2)} GP
                                </Typography>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
};

export default ShopPage;