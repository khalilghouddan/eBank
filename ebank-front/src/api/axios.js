import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json',
    },
});

api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        console.log('Intercepting request to:', config.url); // DEBUG
        if (token) {
            console.log('Attaching Token:', token.substring(0, 10) + '...'); // DEBUG
            config.headers.Authorization = `Bearer ${token}`;
        } else {
            console.warn('No token found in localStorage'); // DEBUG
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response) {
            console.error('API Error Status:', error.response.status); // DEBUG
            console.error('API Error Data:', error.response.data); // DEBUG
        }
        if (error.response && error.response.status === 401) {
            console.error("Session invalide, veuillez s'authentifier");
            // Optional: Force logout if needed
            // localStorage.removeItem('token');
            // window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);

export default api;
