import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import api from '../api/axios';
import { useAuth } from '../context/AuthContext';
import { useState } from 'react';

const Login = () => {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const { login } = useAuth();
    const navigate = useNavigate();
    const [errorMsg, setErrorMsg] = useState('');

    const onSubmit = async (data) => {
        try {
            const response = await api.post('/auth/login', data);
            login(response.data.accessToken);
            // Redirect based on role? Or just home which will be protected
            // For now, redirect to a default page or let user choose
            // Ideally check role here or in AuthContext

            // Simple decode to check role for redirect
            const token = response.data.accessToken;
            const decoded = JSON.parse(atob(token.split('.')[1]));
            const roles = decoded.roles ? decoded.roles.split(',') : [];

            if (roles.includes('CLIENT')) {
                navigate('/client/dashboard');
            } else if (roles.includes('AGENT_GUICHET')) {
                navigate('/agent/create-client');
            } else {
                navigate('/');
            }

        } catch (err) {
            setErrorMsg('Login ou mot de passe incorrect');
            console.error(err);
        }
    };

    return (
        <div className="login-container">
            <form onSubmit={handleSubmit(onSubmit)} className="card login-form">
                <h2>Connexion</h2>
                {errorMsg && <div className="error-banner">{errorMsg}</div>}

                <div className="form-group">
                    <label>Login</label>
                    <input
                        {...register('login', { required: 'Login requis' })}
                        className="input-field"
                    />
                    {errors.login && <span className="error-text">{errors.login.message}</span>}
                </div>

                <div className="form-group">
                    <label>Mot de passe</label>
                    <input
                        type="password"
                        {...register('password', { required: 'Mot de passe requis' })}
                        className="input-field"
                    />
                    {errors.password && <span className="error-text">{errors.password.message}</span>}
                </div>

                <button type="submit" className="btn-primary">Se connecter</button>
            </form>
        </div>
    );
};

export default Login;
