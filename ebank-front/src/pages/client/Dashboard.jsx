import { useEffect, useState } from 'react';
import api from '../../api/axios';

const Dashboard = () => {
    const [client, setClient] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchClientData = async () => {
            try {
                const response = await api.get('/clients/current');
                setClient(response.data);
            } catch (error) {
                console.error("Error fetching client data", error);
            } finally {
                setLoading(false);
            }
        };

        fetchClientData();
    }, []);

    if (loading) return <div>Chargement...</div>;
    if (!client) return <div>Impossible de charger les données.</div>;

    return (
        <div className="dashboard-container">
            <h1 className="page-title">Bienvenue, {client.prenom} {client.nom}</h1>

            <div className="accounts-grid">
                {client.accounts && client.accounts.length > 0 ? (
                    client.accounts.map(account => (
                        <div key={account.id} className="card account-card">
                            <h3>Compte</h3>
                            <p className="rib">RIB: {account.rib}</p>
                            <p className={`status status-${account.status.toLowerCase()}`}>{account.status}</p>
                            <div className="balance">
                                {account.solde.toLocaleString('fr-FR', { style: 'currency', currency: 'MAD' })}
                            </div>
                        </div>
                    ))
                ) : (
                    <p>Aucun compte associé.</p>
                )}
            </div>
        </div>
    );
};

export default Dashboard;
