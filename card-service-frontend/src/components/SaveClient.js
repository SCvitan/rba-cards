import React, { useState } from 'react';
import { createClient } from '../api';

const SaveClient = () => {
  const [ime, setIme] = useState('');
  const [prezime, setPrezime] = useState('');
  const [oib, setOib] = useState('');
  const [statusKartice, setStatusKartice] = useState('PENDING');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createClient({ ime, prezime, oib, statusKartice });
      setMessage('Client created successfully!');
    } catch (err) {
      setMessage(err.response?.data?.message || 'Error creating client');
    }
  };

  return (
    <div>
      <h2>Create Client</h2>
      <form onSubmit={handleSubmit}>
        <input placeholder="First Name" value={ime} onChange={e => setIme(e.target.value)} required />
        <input placeholder="Last Name" value={prezime} onChange={e => setPrezime(e.target.value)} required />
        <input placeholder="OIB" value={oib} onChange={e => setOib(e.target.value)} required />
        <select value={statusKartice} onChange={e => setStatusKartice(e.target.value)}>
          <option value="PENDING">PENDING</option>
          <option value="APPROVED">APPROVED</option>
          <option value="REJECTED">REJECTED</option>
        </select>
        <button type="submit">Create</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default SaveClient;
