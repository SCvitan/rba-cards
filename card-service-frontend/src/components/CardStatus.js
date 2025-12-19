import React, { useState } from 'react';
import { sendCardStatus } from '../api';

const CardStatus = () => {
  const [oib, setOib] = useState('');
  const [status, setStatus] = useState('PENDING');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await sendCardStatus(oib, status);
      setMessage('Card status update successfully sent!');
    } catch (err) {
      setMessage('Error updating card status');
    }
  };

  return (
    <div>
      <h2>Update Card Status</h2>
      <form onSubmit={handleSubmit}>
        <input placeholder="OIB" value={oib} onChange={e => setOib(e.target.value)} required />
        <select value={status} onChange={e => setStatus(e.target.value)}>
          <option value="PENDING">PENDING</option>
          <option value="APPROVED">APPROVED</option>
          <option value="REJECTED">REJECTED</option>
        </select>
        <button type="submit">Update Status</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default CardStatus;
