import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.tsx';
import { BrowserRouter } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext.tsx';
import { FavoriteProvider } from './context/FavoriteContext.tsx';
import { LocalCartProvider } from './context/LocalCartContext.tsx';
import axios from 'axios'; 
import { ThemeProvider, CssBaseline } from '@mui/material';
import theme from './theme'; 
import './index.css';

axios.defaults.withCredentials = true;

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <AuthProvider>
        <FavoriteProvider>
          <LocalCartProvider> 
            <BrowserRouter>
              <App />
            </BrowserRouter>
          </LocalCartProvider>
        </FavoriteProvider>
      </AuthProvider>
    </ThemeProvider>
  </React.StrictMode>
);