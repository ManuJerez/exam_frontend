import axios from "axios"

const URL_EVENTO = process.env.REACT_APP_API_REST_URL + 'evento';

const getEvento = async (id, setEvento) => {
  try {
    const evento = await axios.get(URL_EVENTO + "/" + id);
    setEvento(evento.data);
  } catch (error) {
    console.error("Error al obtener evento: ", error);
  }
}

const getEventosProximos = async (direccion, setEventos) => {
  try {
    const resEventos = await axios.get(URL_EVENTO + "/proximos?direccion=" + direccion);
    setEventos(resEventos.data);
  }catch (error) {
    console.error("Error al obtener eventos: ", error);
  }
}

const getEventoByOrganizador = async (organizador, setEventos) => {
  try {
    const resEventos = await axios.get(URL_EVENTO + "/organizador/" + organizador);
    setEventos(resEventos.data);
  }catch (error) {
    console.error("Error al obtener eventos: ", error);
  }
}

export const eventoService = { getEvento, getEventosProximos, getEventoByOrganizador }