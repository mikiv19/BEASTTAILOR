import React, { useState } from 'react';
import { Box, TextField, Button, Typography, Container, Alert } from '@mui/material';
import apiClient from '../api/axiosConfig'; 
import { isAxiosError } from 'axios'; 
import { useNavigate } from 'react-router-dom';

interface RegisterResponse {
    message: string;
}

interface ErrorResponse {
    message: string;
}

const RegisterPage: React.FC = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const [error, setError] = useState<string | null>(null);
    const [success, setSuccess] = useState<string | null>(null);

    const navigate = useNavigate();

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault(); 

        setError(null);
        setSuccess(null);

        if (!username || !password) {
            setError("Username and password are required.");
            return;
        }

        try {
            const response = await apiClient.post<RegisterResponse>('/api/auth/register', {
                username: username,
                password: password,
            });

            setSuccess(response.data.message || 'User registered successfully!');
            
            setUsername('');
            setPassword('');

            setTimeout(() => {
                navigate('/login'); 
            }, 1000);

        } catch (err) {
            let errorMessage = "An unexpected error occurred.";
            if (isAxiosError<ErrorResponse>(err)) {
                if (err.response) {
                    errorMessage = err.response.data.message || "An unknown error occurred during registration.";
                } else if (err.request) {
                    errorMessage = "Network Error: The server is not responding.";
                }
            }
            setError(errorMessage);
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
                    Register for BeastTailor
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
                        autoComplete="new-password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />

                    {error && <Alert severity="error" sx={{ width: '100%', mt: 2 }}>{error}</Alert>}
                    {success && <Alert severity="success" sx={{ width: '100%', mt: 2 }}>{success}</Alert>}

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                    >
                        Register
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default RegisterPage;