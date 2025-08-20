import React, { useState, useEffect, useMemo } from 'react';
import apiClient from '../api/axiosConfig';
import { Container, Typography, Box, CircularProgress, Alert, Card, CardMedia, CardActions, Button, Divider, Paper, Grid, CardContent } from '@mui/material';
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

    const gearSlots = [
        { slotType: 'CAPE', label: 'Cape', gridArea: 'cape' },
        { slotType: 'HEAD', label: 'Head', gridArea: 'head' },
        { slotType: 'SHOULDERS', label: 'Shoulders', gridArea: 'shoulders' },
        { slotType: 'CHEST', label: 'Chest', gridArea: 'chest' },
        { slotType: 'HANDS', label: 'Back', gridArea: 'back' },
        { slotType: 'FEET', label: 'Legs', gridArea: 'legs' },
        { slotType: 'FEET', label: 'Feet', gridArea: 'feet' },
        { slotType: 'NECK', label: 'Hands', gridArea: 'hands' },
        { slotType: 'ACCESSORY', label: 'Accessory', gridArea: 'accessory' },
    ];

    const fetchWardrobe = async () => {
        try {
            setError(null);
            setLoading(true);
            const response = await apiClient.get<Wardrobe>('/api/wardrobe');
            const allItems = response.data.items || [];
            setWardrobeItems(allItems.filter(item => !item.equipped));
            setEquippedItems(allItems.filter(item => item.equipped));
        } catch (err) {
            setError('Failed to fetch your wardrobe. Please try again later.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        if (user) {
            fetchWardrobe();
        } else {
            setLoading(false);
            setWardrobeItems([]);
            setEquippedItems([]);
        }
    }, [user]);

    const totalStats = useMemo(() => {
        const stats = { acBonus: 0, strBonus: 0, dexBonus: 0, conBonus: 0, intBonus: 0, wisBonus: 0, chaBonus: 0 };
        equippedItems.forEach(item => {
            const ci = item.clothingItem;
            stats.acBonus += ci.acBonus || 0;
            stats.strBonus += ci.strBonus || 0;
            stats.dexBonus += ci.dexBonus || 0;
            stats.conBonus += ci.conBonus || 0;
            stats.intBonus += ci.intBonus || 0;
            stats.wisBonus += ci.wisBonus || 0;
            stats.chaBonus += ci.chaBonus || 0;
        });
        return stats;
    }, [equippedItems]);
    
    const handleUnequip = async (itemToUnequip: WardrobeItem) => {
        setError(null);
        try {
            await apiClient.post(`/api/wardrobe/items/${itemToUnequip.id}/unequip`);
            setEquippedItems(prev => prev.filter(item => item.id !== itemToUnequip.id));
            setWardrobeItems(prev => [...prev, { ...itemToUnequip, equipped: false }]);
        } catch (err) {
            setError("Failed to unequip item. Please try again.");
        }
    };
    
    const handleEquip = async (itemToEquip: WardrobeItem) => {
        setError(null);
        const itemInSlot = equippedItems.find(
            item => item.clothingItem.itemSlot === itemToEquip.clothingItem.itemSlot
        );
        if (itemInSlot) {
            await handleUnequip(itemInSlot);
        }
        try {
            await apiClient.post(`/api/wardrobe/items/${itemToEquip.id}/equip`);
            setWardrobeItems(prev => prev.filter(item => item.id !== itemToEquip.id));
            setEquippedItems(prev => [...prev, { ...itemToEquip, equipped: true }]);
        } catch (err) {
            setError("Failed to equip item. Please try again.");
            await fetchWardrobe();
        }
    };
    
    const findEquippedItem = (slotType: string) => {
        return equippedItems.find(item => item.clothingItem.itemSlot === slotType);
    };

    if (loading) return <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}><CircularProgress /></Box>;
    if (error) return <Alert severity="error" sx={{ mt: 4 }}>{error}</Alert>;
    if (!user) return <Typography sx={{ mt: 4, textAlign: 'center' }}>Please log in to view your profile.</Typography>;

    return (
        <Container sx={{ mt: 4, mb: 4 }} maxWidth="lg">
            <Grid container spacing={4} sx={{ mt: 2 }}>
                <Grid size={{ xs: 12, md: 5 }}>
                    <Paper elevation={3} sx={{ p: 2, position: 'sticky', top: '80px' }}>
                        <Typography variant="h5" component="h2" gutterBottom align="center">
                            Equipped Gear
                        </Typography>
                        <Box sx={{ 
                            p: 2,
                            display: 'grid',
                            gap: 2,
                            gridTemplateColumns: 'repeat(3, 1fr)',
                            gridTemplateAreas: `
                                'cape head .'
                                'shoulders chest back'
                                '. legs .'
                                'hands feet accessory'
                            `,
                        }}>
                            {gearSlots.map(slot => {
    const equippedItem = findEquippedItem(slot.slotType);
    return (
        <Box key={slot.slotType} sx={{ gridArea: slot.gridArea, aspectRatio: '1 / 1' }}>
            {equippedItem ? (
                <Card sx={{ height: '100%', position: 'relative', '&:hover .unequip-button': { opacity: 1 } }}>
                    <CardMedia
                        component="img"
                        image={equippedItem.clothingItem.imageUrlThumbnail}
                        alt={equippedItem.clothingItem.name}
                        sx={{ width: '100%', height: '100%', objectFit: 'contain', p: 1 }}
                    />
                    <Button
                        className="unequip-button"
                        size="small"
                        variant="contained"
                        onClick={() => handleUnequip(equippedItem)}
                        sx={{
                            position: 'absolute',
                            bottom: 8,
                            left: '50%',
                            transform: 'translateX(-50%)',
                            opacity: 0.2,
                            transition: 'opacity 0.2s ease-in-out',
                        }}
                    >
                        Unequip
                    </Button>
                </Card>
            ) : (
                <Paper variant="outlined" sx={{ 
                    height: '100%', 
                    display: 'flex', 
                    alignItems: 'center', 
                    justifyContent: 'center', 
                    backgroundColor: 'rgba(0,0,0,0.2)' 
                }}>
                    <Typography variant="caption" color="text.secondary">{slot.label}</Typography>
                </Paper>
            )}
        </Box>
    );
})}
                        </Box>
                            <Divider sx={{ my: 2 }} />
                        <Box sx={{ px: 2, pb: 2 }}>
                            <Typography variant="h5" component="h2" gutterBottom align="center">
                                Character Stats
                            </Typography>
                            {Object.values(totalStats).every(v => v === 0) ? (
                                <Typography color="text.secondary" align="center">No stat bonuses from equipped gear.</Typography>
                            ) : (
                                Object.entries(totalStats).map(([stat, value]) => {
                                    if (value > 0) {
                                        const statName = stat.replace('Bonus', '').toUpperCase();
                                        return <Typography key={stat}>+ {value} {statName}</Typography>;
                                    }
                                    return null;
                                })
                            )}
                        </Box>
                    </Paper>
                </Grid>

                <Grid size={{ xs: 12, md: 7 }}>
                    <Box>
                        {wardrobeItems.length > 0 ? (
                            <Grid container spacing={2}>
                                {wardrobeItems.map((item) => (
                                    <Grid key={item.id} size={{ xs: 12, sm: 6, md: 4 }}>
                                        <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
                                            <CardMedia component="img" height="180" image={item.clothingItem.imageUrlThumbnail} alt={item.clothingItem.name} sx={{ p: 1, objectFit: 'contain' }} />
                                            <CardContent sx={{ display: 'flex', flexDirection: 'column', flexGrow: 1 }}>
                                                <Box sx={{ flexGrow: 1 }}>
                                                    <Typography gutterBottom variant="body1" component="div">{item.clothingItem.name}</Typography>
                                                </Box>
                                                <Typography variant="body2" color="text.secondary">{item.clothingItem.brand}</Typography>
                                            </CardContent>
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
                </Grid>
            </Grid>
        </Container>
    );
};

export default ProfilePage;