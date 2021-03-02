import Button from '../components/Button';

function Lobby(props) {
  window.history.replaceState(null, ''); // clear the error message on refresh

  return (
    <div className="lobby">
        <h1>Lobby</h1>
        <Button text="Create Room" />
        <Button text="Join Room" />

        {props.location.state  &&
            <div className="alert alert-warning" role="alert">{props.location.state.error}</div>
        }
    </div>
  );
}

export default Lobby;