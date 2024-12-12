import React from 'react'
import OpenStreetMap from '../components/Map';
import { useAuth } from '../context/AuthContext';

const Home = () => {
  const { user } = useAuth();

  return (
    <>
      <h1>Bienvenido {user.displayName}</h1>
      <OpenStreetMap />
    </>
  )
}

export default Home