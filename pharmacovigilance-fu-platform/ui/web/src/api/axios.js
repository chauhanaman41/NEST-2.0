import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api', // Points to API Gateway, not microservices directly
    headers: {
        'Content-Type': 'application/json',
    },
});

export default api;
