import React from 'react'
import { useAuth } from '../context/AuthContext';
import OpenStreetMap from '../components/Map';
import { usuarioService } from '../services/usuario';
import { useNavigate } from 'react-router-dom';

const Home = () => {
  const { user } = useAuth();
  const navigate = useNavigate();

  const gotoAddLugar = async (user) => {
    try {
      await usuarioService.autenticarUsuario(user);
      navigate('/addLugar');
    } catch (error) {
      alert("Usuario no autenticado");
    }
  }

  return (
    <>
      <h1>Mapa de {user.email}</h1>
      <div className='container'>
        <div className='map'>
          <OpenStreetMap user={user} />
        </div>
      </div>
      <button onClick={() => gotoAddLugar(user)}>Añadir lugar</button>
      <button onClick={() => navigate('/explore')}>Explorar otros mapas</button>
    </>
  )
}

export default Home