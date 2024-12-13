import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import Home from './pages/Home';
import ProtectedRoute from './context/ProtectedRoute';
import { AuthProvider } from './context/AuthContext';
import NavBar from './components/NavBar';
import AnyadirLugar from './pages/AnyadirLugar';
import Explore from './pages/Explore';

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route path='/' element={<ProtectedRoute><Home /></ProtectedRoute>} />
          <Route path='/login' element={<Login />} />
          <Route path='/addLugar' element={<AnyadirLugar />} />
          <Route path='/explore' element={<Explore />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
