import axios from 'axios';

const API_REST_URL = process.env.REACT_APP_API_REST_URL;

const loginUsuario = async (user) => {
  try {
    await axios.post(API_REST_URL + 'usuario/registroGoogle', user);
  } catch (error) {
    console.error("Error al registrar usuario: ", error);
  }
}

const autenticarUsuario = async (user) => {
  const authAxios = axios.create({
    baseURL: API_REST_URL,
    headers: {
      Authorization: `Bearer ${user.accessToken}`
    }
  });

  try {
    await authAxios.get('usuario/autenticacion')
  } catch (error) {
    console.error("Usuario no autenticado: ", error);
  }
}

const getLugaresByUsuario = async (user, setLugares) => {
  const res = await axios.get(API_REST_URL + 'lugar/' + user);
  setLugares(res.data);
}

const addLugar = async (user, direccion) => {
  await axios.put(API_REST_URL + 'lugar?emailUsuario=' + user + '&direccion=' + direccion);
}

export const usuarioService = { loginUsuario, autenticarUsuario, getLugaresByUsuario, addLugar }