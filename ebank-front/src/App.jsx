import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import Navbar from './components/Navbar';
import ProtectedRoute from './components/ProtectedRoute';
import Login from './pages/Login';
import Dashboard from './pages/client/Dashboard';
import TransferForm from './pages/client/TransferForm';
import CreateClient from './pages/agent/CreateClient';
import CreateAccount from './pages/agent/CreateAccount';
import './App.css';

function App() {
  return (
    <AuthProvider>
      <Router>
        <Navbar />
        <div className="main-content">
          <Routes>
            <Route path="/login" element={<Login />} />

            {/* Redirect root to login for now */}
            <Route path="/" element={<Navigate to="/login" replace />} />
            <Route path="/unauthorized" element={<div>Accès non autorisé</div>} />

            {/* Client Routes */}
            <Route element={<ProtectedRoute allowedRoles={['CLIENT']} />}>
              <Route path="/client/dashboard" element={<Dashboard />} />
              <Route path="/client/transfer" element={<TransferForm />} />
            </Route>

            {/* Agent Routes */}
            <Route element={<ProtectedRoute allowedRoles={['AGENT_GUICHET']} />}>
              <Route path="/agent/create-client" element={<CreateClient />} />
              <Route path="/agent/create-account" element={<CreateAccount />} />
            </Route>
          </Routes>
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;
