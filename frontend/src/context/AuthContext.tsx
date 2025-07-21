import React, { createContext, useState, useContext,type ReactNode } from 'react';


interface User {
    username: string;
    // Add more user details here later, like roles or ID
}


interface AuthContextType {
    user: User | null;
    login: (userData: User) => void;
    logout: () => void;
    isAuthenticated: boolean;
}

const AuthContext = createContext<AuthContextType | null>(null);

export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<User | null>(null);

    // Update state on login
    const login = (userData: User) => {
        setUser(userData);
    };

    // Clear state on logout
    const logout = () => {
        setUser(null);
        // meaby call backend /logout endpoint here (need to implement)
    };

    //check if the user is logged in
    const isAuthenticated = !!user;

    const value = {
        user,
        login,
        logout,
        isAuthenticated
    };

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
};


export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};