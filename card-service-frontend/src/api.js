import axios from 'axios';

const API_BASE = 'http://localhost:8080/api/v1/client';

export const createClient = (client) =>
  axios.post(`${API_BASE}`, client);

export const getClient = (oib) =>
  axios.get(`${API_BASE}/${oib}`);

export const deleteClient = (oib) =>
  axios.delete(`${API_BASE}/${oib}`);

export const sendCardStatus = (oib, status) =>
  axios.post(`${API_BASE}/card-status`, null, {
    params: { oib, status }
  });
