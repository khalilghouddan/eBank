import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Navbar = () => {
    const { user, logout } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    if (!user) return null;

    return (
        <nav className="navbar">
            <div className="navbar-container">
                <Link to="/" className="navbar-logo">eBank</Link>
                <ul className="nav-menu">
                    {user.roles.includes('CLIENT') && (
                        <>
                            <li className="nav-item">
                                <Link to="/client/dashboard" className="nav-links">Dashboard</Link>
                            </li>
                            <li className="nav-item">
                                <Link to="/client/transfer" className="nav-links">Virement</Link>
                            </li>
                        </>
                    )}

                    {user.roles.includes('AGENT_GUICHET') && (
                        <>
                            <li className="nav-item">
                                <Link to="/agent/create-client" className="nav-links">Nouveau Client</Link>
                            </li>
                            <li className="nav-item">
                                <Link to="/agent/create-account" className="nav-links">Nouveau Compte</Link>
                            </li>
                        </>
                    )}

                    <li className="nav-item">
                        <button onClick={handleLogout} className="btn-logout">Logout ({user.username})</button>
                    </li>
                </ul>
            </div>
        </nav>
    );
};

export default Navbar;
