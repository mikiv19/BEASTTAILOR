import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Grid from '@mui/material/Grid'; 
import { Container, Typography, Box, CircularProgress, Alert, Card, CardMedia, CardActions, Button, Divider } from '@mui/material';
import { useAuth } from '../context/AuthContext';
import type { ClothingItem } from '../types';

interface WardrobeItem {
    id: number;
    clothingItem: ClothingItem;
    equipped: boolean; 
}

interface Wardrobe {
    id: number;
    items: WardrobeItem[];
}

const ProfilePage: React.FC = () => {
    const { user } = useAuth();
    
    const [wardrobeItems, setWardrobeItems] = useState<WardrobeItem[]>([]);
    const [equippedItems, setEquippedItems] = useState<WardrobeItem[]>([]);

    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchWardrobe = async () => {
            try {
                setError(null);
                setLoading(true);
                const response = await axios.get<Wardrobe>('http://localhost:8080/api/wardrobe', {
                    withCredentials: true
                });
                console.log("RAW DATA FROM BACKEND:", JSON.stringify(response.data, null, 2));
                const allItems = response.data.items || [];
                setWardrobeItems(allItems.filter(item => !item.equipped));
                setEquippedItems(allItems.filter(item => item.equipped));

            } catch (err) {
                setError('Failed to fetch your wardrobe. Please try again later.');
                console.error("Error fetching wardrobe:", err);
            } finally {
                setLoading(false);
            }
        };

        if (user) {
            fetchWardrobe();
        } else {
            setLoading(false);
            setWardrobeItems([]);
            setEquippedItems([]);
        }
    }, [user]);

    const handleEquip = async (itemToEquip: WardrobeItem) => {
        setError(null);
        try {
            await axios.post(`http://localhost:8080/api/wardrobe/items/${itemToEquip.id}/equip`, {}, {
                withCredentials: true
            });

            setWardrobeItems(prev => prev.filter(item => item.id !== itemToEquip.id));
            setEquippedItems(prev => [...prev, { ...itemToEquip, isEquipped: true }]);
        } catch (err) {
            console.error("Failed to equip item:", err);
            setError("Failed to equip item. Please try again.");
        }
    };

    const handleUnequip = async (itemToUnequip: WardrobeItem) => {
        setError(null);
        try {
            await axios.post(`http://localhost:8080/api/wardrobe/items/${itemToUnequip.id}/unequip`, {}, {
                withCredentials: true
            });

            setEquippedItems(prev => prev.filter(item => item.id !== itemToUnequip.id));
            setWardrobeItems(prev => [...prev, { ...itemToUnequip, isEquipped: false }]);
        } catch (err) {
            console.error("Failed to unequip item:", err);
            setError("Failed to unequip item. Please try again.");
        }
    };

    if (loading) {
        return <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}><CircularProgress /></Box>;
    }

    if (error) {
        return <Alert severity="error" sx={{ mt: 4 }}>{error}</Alert>;
    }

    if (!user) {
        return <Typography sx={{ mt: 4, textAlign: 'center' }}>Please log in to view your profile.</Typography>;
    }

    return (
        <Container sx={{ mt: 4, mb: 4 }}>
            <Typography variant="h4" component="h1" gutterBottom>
                Profile for {user.username}
            </Typography>
            
            <Box sx={{ mt: 4, mb: 4 }}>
                <Typography variant="h5" component="h2" gutterBottom>
                    Equipped Gear
                </Typography>
                {equippedItems.length > 0 ? (
                    <Grid container spacing={2}>
                        {equippedItems.map(item => (
                            <Grid size={{ xs: 6, sm: 4, md: 3 }} key={`equipped-${item.id}`}>
                                <Card>
                                    <CardMedia
                                        component="img"
                                        height="140"
                                        image={item.clothingItem.imageUrlThumbnail}
                                        alt={item.clothingItem.name}
                                        sx={{ p: 1, objectFit: 'contain' }}
                                    />
                                    <Box sx={{ p: 1, textAlign: 'center' }}>
                                        <Typography variant="caption">{item.clothingItem.name}</Typography>
                                    </Box>
                                    <CardActions sx={{ justifyContent: 'center' }}>
                                        <Button size="small" onClick={() => handleUnequip(item)}>Unequip</Button>
                                    </CardActions>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>
                ) : (
                    <Typography>No gear equipped. Select an item from your wardrobe to equip it.</Typography>
                )}
            </Box>

            <Divider sx={{ my: 4 }} />

            <Box sx={{ mt: 4 }}>
                <Typography variant="h5" component="h2" gutterBottom>
                    Your Wardrobe
                </Typography>
                {wardrobeItems.length > 0 ? (
                    <Grid container spacing={2}>
                        {wardrobeItems.map(item => (
                            <Grid size={{ xs: 6, sm: 4, md: 3 }} key={`wardrobe-${item.id}`}>
                                <Card>
                                    <CardMedia
                                        component="img"
                                        height="140"
                                        image={item.clothingItem.imageUrlThumbnail}
                                        alt={item.clothingItem.name}
                                        sx={{ p: 1, objectFit: 'contain' }}
                                    />
                                    <Box sx={{ p: 1, textAlign: 'center' }}>
                                        <Typography variant="caption">{item.clothingItem.name}</Typography>
                                    </Box>
                                    <CardActions sx={{ justifyContent: 'center' }}>
                                        <Button size="small" onClick={() => handleEquip(item)}>Equip</Button>
                                    </CardActions>
                                 </Card>
                            </Grid>
                        ))}
                    </Grid>
                ) : (
                    <Typography>Your wardrobe is empty. Go buy some gear!</Typography>
                )}
            </Box>
        </Container>
    );
};

export default ProfilePage;