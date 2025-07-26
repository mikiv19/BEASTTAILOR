import React, { createContext, useContext, useState, useEffect, type ReactNode } from 'react';
import axios from 'axios';
import type { ClothingItem } from '../types';
import { useAuth } from './AuthContext';

export interface CartItem {
    id: number;
    quantity: number;
    clothingItem: ClothingItem;
}

interface CartContextType {
    cartItems: CartItem[];
    addToCart: (item: ClothingItem, quantity?: number) => Promise<void>;
    removeFromCart: (cartItemId: number) => Promise<void>;
    fetchCart: () => Promise<void>;
    cartItemCount: number;
    loading: boolean;
}

const CartContext = createContext<CartContextType | null>(null);

export const CartProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [cartItems, setCartItems] = useState<CartItem[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const { isAuthenticated } = useAuth();

    const fetchCart = async () => {
        if (!isAuthenticated) {
            setCartItems([]);
            setLoading(false);
            return;
        }
        try {
            setLoading(true);
            const response = await axios.get('http://localhost:8080/api/cart');
            setCartItems(response.data.items || []);
        } catch (error) {
            console.error("Failed to fetch cart:", error);
            setCartItems([]);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchCart();
    }, [isAuthenticated]);

    const addToCart = async (item: ClothingItem, quantity: number = 1) => {
        if (!isAuthenticated) {
            alert("Please log in to add items to your cart.");
            return;
        }
        try {
            const response = await axios.post(
                'http://localhost:8080/api/cart/items', 
                { itemId: item.id, quantity }
            );
            setCartItems(response.data.items || []);
        } catch (error) {
            console.error("Failed to add item to cart:", error);
            alert("There was an error adding the item to your cart.");
        }
    };

    const removeFromCart = async (cartItemId: number) => {
        if (!isAuthenticated) {
            alert("You must be logged in to modify the cart.");
            return;
        }
        try {
            const response = await axios.delete(
                `http://localhost:8080/api/cart/items/${cartItemId}`
            );
            setCartItems(response.data.items || []);
        } catch (error) {
            console.error("Failed to remove item from cart:", error);
            alert("There was an error removing the item from your cart.");
        }
    };

    const cartItemCount = cartItems.reduce((total, item) => total + item.quantity, 0);
    const value = { 
        cartItems, 
        addToCart, 
        removeFromCart,
        fetchCart, 
        cartItemCount, 
        loading 
    };

    return (
        <CartContext.Provider value={value}>
            {children}
        </CartContext.Provider>
    );
};

export const useCart = () => {
    const context = useContext(CartContext);
    if (!context) {
        throw new Error('useCart must be used within a CartProvider');
    }
    return context;
};