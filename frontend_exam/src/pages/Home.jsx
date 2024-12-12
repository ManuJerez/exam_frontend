import React from 'react'
import { useAuth } from '../context/AuthContext';
import OpenStreetMap from '../components/Map';
import Image from '../components/Image';
import UploadImage from '../components/UploadImage';

const Home = () => {
  const { user } = useAuth();

  return (
    <>
      <h1>Bienvenido {user.displayName}</h1>
      <div className='container'>
        <div className='map'>
          <OpenStreetMap />
        </div>
        <div className='right-container'>
          <Image imageName='cld-sample-5'/>
        </div>
        <div className='upload'>
          <UploadImage />
        </div>
      </div>
    </>
  )
}

export default Home