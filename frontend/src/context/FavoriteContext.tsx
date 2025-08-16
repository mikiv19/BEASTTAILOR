import React, { createContext, useContext, useState, useEffect, type ReactNode } from 'react';
import axios from 'axios';
import type { ClothingItem } from '../types';
import { useAuth } from './AuthContext';

export interface FavoriteItem {
    id: number;
    quantity: number;
    clothingItem: ClothingItem;
}

interface FavoriteContextType {
    favoriteItems: FavoriteItem[];
    addToFavorite: (item: ClothingItem, quantity?: number) => Promise<void>;
    removeFromFavorite: (favoriteItemId: number) => Promise<void>;
    fetchFavorite: () => Promise<void>;
    favoriteItemCount: number;
    loading: boolean;
}

interface FavoritesListResponse {
    id: number;
    items: FavoriteItem[];
}
const FavoriteContext = createContext<FavoriteContextType | null>(null);

export const FavoriteProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [favoriteItems, setFavoriteItems] = useState<FavoriteItem[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const { isAuthenticated } = useAuth();

    const fetchFavorite = async () => {
        if (!isAuthenticated) {
            setFavoriteItems([]);
            setLoading(false);
            return;
        }
        try {
            setLoading(true);
            const response = await axios.get<FavoritesListResponse>('http://localhost:8080/api/favorites');
            setFavoriteItems(response.data.items || []);
        } catch (error) {
            console.error("Failed to fetch favorite:", error);
            setFavoriteItems([]);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchFavorite();
    }, [isAuthenticated]);

    const addToFavorite = async (item: ClothingItem, quantity: number = 1) => {
        if (!isAuthenticated) {
            alert("Please log in to add items to your favorite.");
            return;
        }
        try {
            const response = await axios.post<FavoritesListResponse>(
                'http://localhost:8080/api/favorite/items', 
                { itemId: item.id, quantity }
            );
            setFavoriteItems(response.data.items || []);
        } catch (error) {
            console.error("Failed to add item to favorite:", error);
            alert("There was an error adding the item to your favorite.");
        }
    };

    const removeFromFavorite = async (favoriteItemId: number) => {
        if (!isAuthenticated) {
            alert("You must be logged in to modify the favorite.");
            return;
        }
        try {
            const response = await axios.delete<FavoritesListResponse>(
                `http://localhost:8080/api/favorite/items/${favoriteItemId}`
            );
            setFavoriteItems(response.data.items || []);
        } catch (error) {
            console.error("Failed to remove item from favorite:", error);
            alert("There was an error removing the item from your favorite.");
        }
    };

    const favoriteItemCount = favoriteItems.reduce((total, item) => total + item.quantity, 0);
    const value = { 
        favoriteItems, 
        addToFavorite, 
        removeFromFavorite,
        fetchFavorite, 
        favoriteItemCount, 
        loading 
    };

    return (
        <FavoriteContext.Provider value={value}>
            {children}
        </FavoriteContext.Provider>
    );
};

export const useFavorite = () => {
    const context = useContext(FavoriteContext);
    if (!context) {
        throw new Error('useFavorite must be used within a FavoriteProvider');
    }
    return context;
};