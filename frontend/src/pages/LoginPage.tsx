import axios from 'axios';
import React, { useState } from 'react';
import { Box, TextField, Button, Typography, Container, Alert } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const LoginPage: React.FC = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState<string | null>(null);
    
    const navigate = useNavigate();

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setError(null);

        if (!username || !password) {
            setError("Username and password are required.");
            return;
        }
        
        const loginData = new URLSearchParams();
        loginData.append('username', username);
        loginData.append('password', password);

        try {
            // Send the POST request to the /login endpoint
            const response = await axios.post('http://localhost:8080/login', loginData, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                withCredentials: true 
            });

            // On success, the backend will return our custom success message
            if (response.status === 200) {
                // For now, just log success and navigate home.
                // In the next step, we'll update our AuthContext here.
                console.log('Login Successful:', response.data);
                navigate('/'); // Navigate to the homepage on successful login
            }

        } catch (err: any) {
            // Handle login errors
            if (axios.isAxiosError(err) && err.response) {
                // The backend returned a 401 Unauthorized status
                setError(err.response.data.error || "Invalid username or password.");
            } else {
                setError("Network Error or server is not responding.");
            }
        }
    };

    return (
        <Container component="main" maxWidth="xs">
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                <Typography component="h1" variant="h5">
                    Sign In to BeastTailor
                </Typography>
                <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="username"
                        label="Username"
                        name="username"
                        autoComplete="username"
                        autoFocus
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Password"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />

                    {error && <Alert severity="error" sx={{ width: '100%', mt: 2 }}>{error}</Alert>}

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                    >
                        Sign In
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default LoginPage;