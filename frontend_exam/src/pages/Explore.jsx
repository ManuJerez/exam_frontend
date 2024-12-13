import React, { useEffect, useState } from 'react'
import { useAuth } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';
import OpenStreetMap from '../components/Map';
import { usuarioService } from '../services/usuario';

const Explore = () => {
  const { user } = useAuth();
  const [userVisitado, setUserVisitado] = useState(user);
  const [email, setEmail] = useState(user.email);

  const navigate = useNavigate();

  const getUsuarioVisitado = async () => {
    navigate('/explore');
  }
  
  return (
    <>
      <form>
        <input type='text' placeholder='Introduzca email' value={email} onChange={(e) => setEmail(e.target.value)} />
        <button onClick={() => getUsuarioVisitado()}>Explorar</button>
      </form>
      <h1>Mapa de {userVisitado.email}</h1>
      <div className='container'>
        <div className='map'>
          {
            userVisitado ?
            <OpenStreetMap user={userVisitado} />
            :
            <p>No hay usuario con ese email</p>
          }
        </div>
      </div>
    </>
  )
}

export default Explore