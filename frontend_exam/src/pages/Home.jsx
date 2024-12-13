import React, { useState } from 'react'
import { useAuth } from '../context/AuthContext';
import OpenStreetMap from '../components/Map';
import UploadImage from '../components/UploadImage';
import { usuarioService } from '../services/usuario';
import { useNavigate } from 'react-router-dom';
import ListaEventos from '../components/ListaEventos';
import Imagen from '../components/Imagen';

const Home = () => {
  const { user } = useAuth();
  const navigate = useNavigate();

  const verMisEventos = async (user) => {
    try {
      await usuarioService.autenticarUsuario(user);
      navigate('/misEventos');
    } catch (error) {
      alert("Usuario no autenticado");
    }
  }

  return (
    <>
      <h1>Eventual</h1>
      <div className='container'>
        <ListaEventos />
        <div className='map'>
          <OpenStreetMap />
        </div>
        <div className='right-container'>
          
        </div>
        <div className='upload'>
          <UploadImage />
        </div>
        <div>
          <button onClick={() => verMisEventos(user)}>Mis Eventos</button>
        </div>
        <Imagen imageUri={'o8nk4pnuexpkhlrjuht7'}/>
      </div>
    </>
  )
}

export default Home