import React, { useState } from 'react';
import { deleteClient } from '../api';

const DeleteClient = () => {
  const [oib, setOib] = useState('');
  const [message, setMessage] = useState('');

  const handleDelete = async (e) => {
    e.preventDefault();
    try {
      await deleteClient(oib);
      setMessage(`Client with OIB ${oib} deleted successfully!`);
      setOib('');
    } catch (err) {
      setMessage(err.response?.data?.message);
    }
  };

  return (
    <div>
      <h2>Delete Client by OIB</h2>
      <form onSubmit={handleDelete}>
        <input
          type="text"
          placeholder="Enter OIB"
          value={oib}
          onChange={(e) => setOib(e.target.value)}
          required
        />
        <button type="submit">Delete</button>
      </form>

      {message}
    </div>
  );
};

export default DeleteClient;
