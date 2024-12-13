import React, { useEffect, useState } from 'react'
import { eventoService } from '../services/evento';
import { useParams } from 'react-router-dom';
import Imagen from '../components/Imagen';

const VistaEvento = () => {
  const [evento, setEvento] = useState({});

  const { id } = useParams('id');

  useEffect(() => {
    eventoService.getEvento(id, setEvento);
  }, [id]);

  return (
    <div>
      <ul>
        <li>Nombre: {evento.nombre}</li>
        <li>Fecha: {evento.timestamp}</li>
        <li>Lugar: {evento.lugar}</li>
        <li>Organizador: {evento.organizador}</li>
        <li><Imagen imageUri={evento.imagen} /></li>
      </ul>
    </div>
  )
}

export default VistaEvento