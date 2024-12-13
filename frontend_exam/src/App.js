import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import Home from './pages/Home';
import ProtectedRoute from './context/ProtectedRoute';
import { AuthProvider } from './context/AuthContext';
import NavBar from './components/NavBar';
import MisEventos from './pages/MisEventos';
import VistaEvento from './pages/VistaEvento';

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/login' element={<Login />} />
          <Route path='/misEventos' element={<ProtectedRoute><MisEventos /></ProtectedRoute>} />
          <Route path='/evento/:id' element={<VistaEvento />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
