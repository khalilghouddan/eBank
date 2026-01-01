import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const ProtectedRoute = ({ allowedRoles }) => {
    const { user } = useAuth();

    if (!user) {
        return <Navigate to="/login" replace />;
    }

    // Check if user has at least one of the allowed roles
    // user.roles is an array of strings like ['CLIENT'] or ['AGENT_GUICHET']
    const hasPermission = allowedRoles.some(role => user.roles.includes(role));

    if (!hasPermission) {
        return <Navigate to="/unauthorized" replace />;
    }

    return <Outlet />;
};

export default ProtectedRoute;
