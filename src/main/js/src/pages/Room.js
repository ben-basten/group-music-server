import { useState } from 'react';
import {withRouter} from 'react-router-dom';
import Header from '../components/Header';

function Room(props) {
  // props.history.push({
  //   pathname: '/',
  //   state: { error: `Invalid Room ID: ${props.match.params.roomId}` }
  // });

  const[isPlaying, setIsPlaying] = useState(true);
  
  function playPause(e) {
    e.preventDefault();
    setIsPlaying(!isPlaying);
  }

  return (
    <div className="room">
      <Header />
      <div className="room-info">
        <h2>Room {props.match.params.roomId}</h2>
        <p>Share this link with your friends to join!</p>
        <p>{window.location.href}</p>
      </div>
      <div className="media-controls">
        <input type="button" value={isPlaying ? "Pause" : "Play"} onClick={playPause} />
      </div>
    </div>
  );
}

export default withRouter(Room);