import axios from 'axios';

const URI_USUARIO = 'http://localhost:8080/usuario';

const loginUsuario = async (user) => {
  try {
    await axios.post(URI_USUARIO, user);
  } catch (error) {
    console.error("Error al registrar usuario: ", error);
  }
}

export const usuarioService = { loginUsuario }