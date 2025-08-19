import React from 'react';
import { Box } from '@mui/material';
import { useTheme } from '@mui/material/styles';

export const LeftNavShape: React.FC = () => {
    const theme = useTheme();
    return (
        <Box sx={{ position: 'absolute', top: 0, left: 0, width: '100%', height: '100%', zIndex: -1 }}>
            <svg width="100%" height="100%" viewBox="0 0 250 56" preserveAspectRatio="none">
                <path 
                    d="M0,0 H220 C240,0 230,56 250,56 H0 Z"
                    fill={theme.palette.mode === 'dark' ? 'rgba(255, 255, 255, 0.08)' : 'rgba(255, 255, 255, 0.8)'}
                />
            </svg>
        </Box>
    );
};

export const RightNavShape: React.FC = () => {
    const theme = useTheme();
    return (
        <Box sx={{ position: 'absolute', top: 0, left: 0, width: '100%', height: '100%', zIndex: -1 }}>
            <svg width="100%" height="100%" viewBox="0 0 350 56" preserveAspectRatio="none">
                <path
                    d="M30,0 H350 V56 H0 C10,56 10,0 30,0 Z"
                    fill={theme.palette.mode === 'dark' ? 'rgba(255, 255, 255, 0.08)' : 'rgba(255, 255, 255, 0.08)'}
                />
            </svg>
        </Box>
    );
};