import { useState, useEffect } from 'react';
import {withRouter} from 'react-router-dom';
import Header from '../components/Header';
import MusicList from '../components/MusicList';

function Room(props) {
  // props.history.push({
  //   pathname: '/',
  //   state: { error: `Invalid Room ID: ${props.match.params.roomId}` }
  // });

    const [musicList, setMusicList] = useState([]);

    const getMusicListings = () => {
        fetch('http://localhost:8080/api/gms/tracks', {
            method: 'GET'
            // headers: {
            //     'Content-Type': 'application/json'
            // }
        })
            .then(response => response.json())
            .then(response => setMusicList(response))
            .catch(error => {
                console.error("Something went wrong fetching the music listings.");
            });
    }

    useEffect(() => {
        getMusicListings();
    }, [])

    return (
        <div className="room">
          <Header />
          <div className="room-info">
              <h2>Room {props.match.params.roomId}</h2>
              <p>Share this link with your friends to join!</p>
              <p>{window.location.href}</p>
          </div>
          <div className="media-controls">
              <audio controls src="http://localhost:8080/api/gms/current/track">
                  Your browser does not support the <code>audio</code> element.
              </audio>
          </div>
          <MusicList music={musicList} />
        </div>
    );
}

export default withRouter(Room);