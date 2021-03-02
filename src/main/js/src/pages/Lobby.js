function Lobby(props) {
  window.history.replaceState(null, ''); // clear the error message on refresh

  return (
    <div className="lobby">
      <h1>Lobby</h1>
      {props.location.state  &&
        <div className="alert alert-warning" role="alert">{props.location.state.error}</div>
      }
    </div>
  );
}

export default Lobby;