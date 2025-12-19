import './App.css';
import SaveClient from './components/SaveClient';
import CardStatus from './components/CardStatus';
import GetClient from './components/GetClient';
import DeleteClient from './components/DeleteClient';

function App() {
  return (
    <div style={{ padding: 20 }}>
      <h1>Card Service Demo</h1>
      <SaveClient/>
      <hr />
      <GetClient/>
      <hr/>
      <CardStatus/>
      <hr/>
      <DeleteClient/>
    </div>
  );
}

export default App;
