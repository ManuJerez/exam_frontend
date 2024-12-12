import { signOut } from 'firebase/auth'
import React from 'react'
import { auth } from '../firebase/firebase'
import { useAuth } from '../context/AuthContext'

const NavBar = () => {
  const { user } = useAuth();

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
          user && <button onClick={handleLogout}>Logout</button>
        }
      </nav>
    </>
  )
}

export default NavBar