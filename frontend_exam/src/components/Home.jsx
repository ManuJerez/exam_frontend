import React from 'react'
import OpenStreetMap from './Map';
import { useAuth } from './AuthContext';

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