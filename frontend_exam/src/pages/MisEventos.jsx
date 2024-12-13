import React, { useEffect, useState } from 'react'
import { eventoService } from '../services/evento';
import { useAuth } from '../context/AuthContext';
import Evento from '../components/Evento';

const MisEventos = () => {
  const [eventos, setEventos] = useState([]);

  const { user } = useAuth()

  useEffect(() => {
    eventoService.getEventoByOrganizador(user.email, setEventos);
  }, [user.email]);

  return (
    <div>
      <h1>Mis Eventos</h1>
      <ul className='lista'>
        {eventos.length > 0 ? (
          eventos.map(evento =>
            <li key={evento.id}>
              <Evento evento={evento} />
            </li>
          )
        ) : (
          <p>No hay eventos propios</p>
        )}
      </ul>
      <button>Crear evento</button>
    </div>
  )
}

export default MisEventos