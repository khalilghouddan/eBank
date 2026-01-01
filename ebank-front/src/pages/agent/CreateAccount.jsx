import { useForm } from 'react-hook-form';
import api from '../../api/axios';
import { useState } from 'react';

const CreateAccount = () => {
    const { register, handleSubmit, reset, formState: { errors } } = useForm();
    const [msg, setMsg] = useState(null);
    const [isError, setIsError] = useState(false);

    const onSubmit = async (data) => {
        setMsg(null);
        setIsError(false);
        try {
            await api.post('/accounts', data);
            setMsg('Compte créé avec succès.');
            reset();
        } catch (err) {
            setIsError(true);
            setMsg('Erreur: ' + (err.response?.data?.error || err.message));
        }
    };

    return (
        <div className="form-page">
            <h2 className="page-title">Créer un Nouveau Compte</h2>
            <form onSubmit={handleSubmit(onSubmit)} className="card form-card">
                {msg && <div className={isError ? "error-banner" : "success-banner"}>{msg}</div>}

                <div className="form-group">
                    <label>ID Client</label>
                    <input
                        type="number"
                        {...register('clientId', { required: 'ID Client requis' })}
                        className="input-field"
                        placeholder="ID interne du client"
                    />
                    {errors.clientId && <span className="error-text">{errors.clientId.message}</span>}
                </div>

                <div className="form-group">
                    <label>Solde Initial</label>
                    <input
                        type="number"
                        step="0.01"
                        {...register('initialBalance')}
                        className="input-field"
                        defaultValue={0}
                    />
                </div>

                <button type="submit" className="btn-primary">Créer Compte</button>
            </form>
        </div>
    );
};

export default CreateAccount;
