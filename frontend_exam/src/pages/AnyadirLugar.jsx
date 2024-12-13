import React, { useState } from 'react' 
import { usuarioService } from '../services/usuario';
import { useAuth } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';

const AnyadirLugar = () => {
  const [direccion, setDireccion] = useState('');

  const navigate = useNavigate();
  const {user} = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    await usuarioService.addLugar(user.email, direccion);
    navigate("/");
  }

  return (
    <>
      <h1>Añadir lugar</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Direccion del lugar"
          name='value'
          value={direccion}
          onChange={e => setDireccion(e.target.value)}
        />
        <button type="submit">Añadir</button>
      </form>
    </>
  );
}

export default AnyadirLugar