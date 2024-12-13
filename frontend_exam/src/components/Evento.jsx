import React from 'react'
import { useNavigate } from 'react-router-dom'

const Evento = ({ evento }) => {
  const navigate = useNavigate();
  
  return (
    <>
      <div className="card my-3">
        <div className="card-body">
          <h5 className="card-title">Tema: {evento.nombre}</h5>
          <p className="card-text">Autor: {evento.organizador}</p>
        </div>
        <button onClick={() => navigate(`/evento/${evento.id}`)}>Detalles del evento</button>
      </div>
    </>
  )
}

export default Evento