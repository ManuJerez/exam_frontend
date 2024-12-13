import React, { useEffect, useState } from 'react';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import { useAuth } from '../context/AuthContext';
import { usuarioService } from '../services/usuario';
import Imagen from './Imagen';

const OpenStreetMap = () => {
  const position = [54.5260, 15.2551];
  const [lugares, setLugares] = useState([]);

  const {user} = useAuth();

  useEffect(() => {
    usuarioService.getLugaresByUsuario(user.email, setLugares);
  }, [user]);

  const customIcon = new L.Icon({
    iconUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png',
    iconRetinaUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon-2x.png',
    shadowUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-shadow.png',
    iconSize: [25, 41], // Tamaño del ícono
    iconAnchor: [12, 41], // Punto de anclaje del ícono
    popupAnchor: [1, -34], // Posición del popup
    shadowSize: [41, 41], // Tamaño de la sombra
  });

  return (
    <MapContainer center={position} zoom={5} style={{ height: '400px', width: '200%' }}>
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
      />

      {lugares.map(lugar => (
        <Marker position={[lugar.lat, lugar.lon]} icon={customIcon}>
          <Popup>
            {lugar.nombre}
            <Imagen imageUri={lugar.imagen} />
          </Popup>
        </Marker>
      ))}
    </MapContainer>
  );
};

export default OpenStreetMap;