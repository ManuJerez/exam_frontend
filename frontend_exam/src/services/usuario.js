import axios from 'axios';

const URL_USUARIO = process.env.REACT_APP_API_REST_URL + 'usuario';

const loginUsuario = async (user) => {
  try {
    await axios.post(URL_USUARIO + '/registroGoogle', user);
  } catch (error) {
    console.error("Error al registrar usuario: ", error);
  }
}

const autenticarUsuario = async (user) => {
  const authAxios = axios.create({
    baseURL: URL_USUARIO,
    headers: {
      Authorization: `Bearer ${user.accessToken}`
    }
  });

  try {
    await authAxios.get('/autenticacion')
  } catch (error) {
    console.error("Usuario no autenticado: ", error);
  }
}

export const usuarioService = { loginUsuario, autenticarUsuario }