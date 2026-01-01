import { createContext, useContext, useState, useEffect } from 'react';
import { jwtDecode } from "jwt-decode";
// Note: User prompt implies manual token handling or simple decode. 
// Installing jwt-decode is best practice for reading claims from JWT.
// If not available, we will try to just store the token and roles manually if passed, but usually login response has token only.
// Backend response: { accessToken: "..." }
// JWT contains roles.

// I will first try to do without jwt-decode if I didn't install it, but I should probably install it.
// Or I can just parse the base64 myself.

function parseJwt(token) {
    try {
        return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
        return null;
    }
}

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            const decoded = parseJwt(token);
            if (decoded && decoded.exp * 1000 > Date.now()) {
                const roles = decoded.roles ? decoded.roles.split(',') : [];
                setUser({ username: decoded.sub, roles, token });
            } else {
                localStorage.removeItem('token');
            }
        }
        setLoading(false);
    }, []);

    const login = (token) => {
        localStorage.setItem('token', token);
        const decoded = parseJwt(token);
        const roles = decoded.roles ? decoded.roles.split(',') : [];
        setUser({ username: decoded.sub, roles, token });
    };

    const logout = () => {
        localStorage.removeItem('token');
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, login, logout, loading }}>
            {!loading && children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);
