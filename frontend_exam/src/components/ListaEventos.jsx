import React, { useState } from 'react'
import { eventoService } from '../services/evento';
import Evento from './Evento';

const ListaEventos = () => {
  const [direccion, setDireccion] = useState('');
  const [eventos, setEventos] = useState([]);

  const mostrarEventos = async (direccion) => {
    await eventoService.getEventosProximos(direccion, setEventos);
  }

  return (
    <div className='lista-eventos'>
      <input
        type='text'
        placeholder='Indicar direccion...'
        value={direccion}
        onChange={(e) => setDireccion(e.target.value)}
      />
      <button onClick={() => mostrarEventos(direccion)}>
        Buscar eventos
      </button>
      <ul className='lista'>
        {eventos.length > 0 ? (
          eventos.map(evento =>
            <li key={evento.nombre}>
              <Evento evento={evento} />
            </li>
          )
        ) : (
          <p>No hay eventos proximos</p>
        )}
      </ul>
    </div>
  )
}

export default ListaEventos