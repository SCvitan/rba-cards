import React, { useState } from 'react';
import { getClient } from '../api';

const GetClient = () => {
  const [oib, setOib] = useState('');
  const [client, setClient] = useState(null);
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await getClient(oib);
      setClient(response.data);
      setMessage('');
    } catch (err) {
      setClient(null);
      setMessage('Client not found');
    }
  };

  return (
    <div>
      <h2>Find Client by OIB</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Enter OIB"
          value={oib}
          onChange={(e) => setOib(e.target.value)}
          required
        />
        <button type="submit">Find</button>
      </form>

      {message && <p style={{ color: 'red' }}>{message}</p>}

      {client && (
        <div style={{ marginTop: 10 }}>
          <h3>Client Details:</h3>
          <p><strong>First Name:</strong> {client.ime}</p>
          <p><strong>Last Name:</strong> {client.prezime}</p>
          <p><strong>OIB:</strong> {client.oib}</p>
          <p><strong>Status:</strong> {client.statusKartice}</p>
        </div>
      )}
    </div>
  );
};

export default GetClient;
