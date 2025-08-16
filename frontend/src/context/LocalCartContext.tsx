import React, { createContext, useContext, useState, useEffect, type ReactNode } from 'react';
import type { ClothingItem } from '../types';

export interface LocalCartItem extends ClothingItem {
    quantity: number;
}

interface LocalCartContextType {
    cartItems: LocalCartItem[];
    addToCart: (item: ClothingItem, quantity?: number) => void;
    removeFromCart: (itemId: number) => void;
    clearCart: () => void;
    cartItemCount: number;
}

const LocalCartContext = createContext<LocalCartContextType | null>(null);

const getCartFromStorage = (): LocalCartItem[] => {
    try {
        const items = localStorage.getItem('beastTailorCart');
        return items ? JSON.parse(items) : [];
    } catch {
        return [];
    }
};

export const LocalCartProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [cartItems, setCartItems] = useState<LocalCartItem[]>(getCartFromStorage());

    useEffect(() => {
        try {
            localStorage.setItem('beastTailorCart', JSON.stringify(cartItems));
        } catch {}
    }, [cartItems]);

    const addToCart = (item: ClothingItem, quantity: number = 1) => {
        setCartItems(prev =>
            prev.some(i => i.id === item.id)
                ? prev.map(i =>
                    i.id === item.id ? { ...i, quantity: i.quantity + quantity } : i
                  )
                : [...prev, { ...item, quantity }]
        );
    };

    const removeFromCart = (itemId: number) => {
        setCartItems(prev => prev.filter(i => i.id !== itemId));
    };

    const clearCart = () => setCartItems([]);

    const cartItemCount = cartItems.reduce((sum, i) => sum + i.quantity, 0);

    return (
        <LocalCartContext.Provider value={{ cartItems, addToCart, removeFromCart, clearCart, cartItemCount }}>
            {children}
        </LocalCartContext.Provider>
    );
};

export const useLocalCart = () => {
    const context = useContext(LocalCartContext);
    if (!context) throw new Error('useLocalCart must be used within a LocalCartProvider');
    return context;
};