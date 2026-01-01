import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import api from '../../api/axios';
import { useNavigate } from 'react-router-dom';

const TransferForm = () => {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [accounts, setAccounts] = useState([]);
    const navigate = useNavigate();
    const [msg, setMsg] = useState(null);
    const [isError, setIsError] = useState(false);

    useEffect(() => {
        // Fetch client accounts to populate source dropdown
        api.get('/clients/current')
            .then(res => setAccounts(res.data.accounts || []))
            .catch(err => console.error(err));
    }, []);

    const onSubmit = async (data) => {
        setMsg(null);
        setIsError(false);
        try {
            await api.post('/transfers', data);
            setMsg('Virement effectué avec succès');
            setTimeout(() => navigate('/client/dashboard'), 2000);
        } catch (err) {
            setIsError(true);
            setMsg('Erreur lors du virement: ' + (err.response?.data?.error || err.message));
        }
    };

    return (
        <div className="form-page">
            <h2 className="page-title">Effectuer un Virement</h2>
            <form onSubmit={handleSubmit(onSubmit)} className="card form-card">
                {msg && <div className={isError ? "error-banner" : "success-banner"}>{msg}</div>}

                <div className="form-group">
                    <label>Compte Source</label>
                    <select
                        {...register('sourceAccountRib', { required: 'Compte source requis' })}
                        className="input-field"
                    >
                        <option value="">Sélectionner un compte</option>
                        {accounts.map(acc => (
                            <option key={acc.id} value={acc.rib}>
                                {acc.rib} (Solde: {acc.solde} MAD)
                            </option>
                        ))}
                    </select>
                    {errors.sourceAccountRib && <span className="error-text">{errors.sourceAccountRib.message}</span>}
                </div>

                <div className="form-group">
                    <label>RIB Destinataire</label>
                    <input
                        {...register('destinationAccountRib', {
                            required: 'RIB destinataire requis',
                            pattern: { value: /^[a-zA-Z0-9]{24}$/, message: 'Format RIB invalide (24 caractères)' }
                        })}
                        className="input-field"
                        placeholder="Entrez le RIB du destinataire"
                    />
                    {errors.destinationAccountRib && <span className="error-text">{errors.destinationAccountRib.message}</span>}
                </div>

                <div className="form-group">
                    <label>Montant</label>
                    <input
                        type="number" step="0.01"
                        {...register('amount', {
                            required: 'Montant requis',
                            min: { value: 1, message: 'Minimum 1.00 MAD' }
                        })}
                        className="input-field"
                    />
                    {errors.amount && <span className="error-text">{errors.amount.message}</span>}
                </div>

                <div className="form-group">
                    <label>Motif</label>
                    <textarea
                        {...register('description')}
                        className="input-field"
                    />
                </div>

                <button type="submit" className="btn-primary">Valider</button>
            </form>
        </div>
    );
};

export default TransferForm;
