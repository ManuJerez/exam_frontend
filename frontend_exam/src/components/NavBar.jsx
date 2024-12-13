import { signOut } from 'firebase/auth'
import React from 'react'
import { auth } from '../firebase/firebase'
import { useAuth } from '../context/AuthContext'
import { useNavigate } from 'react-router-dom'

const NavBar = () => {
  const { user } = useAuth();
  const navigate = useNavigate();

  const handleLogout = async () => {
    await signOut(auth);
  }

  return (
    <>
      <nav>
        <div>
          <a href="/">Home</a>
        </div>
        {
          user ? <button onClick={handleLogout}>Logout</button>
          : <button onClick={() => navigate('/login')}>Login</button>
        }
      </nav>
    </>
  )
}

export default NavBar