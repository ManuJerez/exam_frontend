import React, { useState } from 'react'
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
          <OpenStreetMap />
        </div>
      </div>
      <button onClick={() => gotoAddLugar(user)}>Ver mis eventos</button>
    </>
  )
}

export default Home