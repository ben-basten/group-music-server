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
    const [queue, setQueue] = useState([]);

    const getMusicListings = () => {
        console.log("called getMusicListings");
        fetch('/api/gms/tracks', {
            method: 'GET'
        })
            .then(response => response.json())
            .then(response => setMusicList(response))
            .catch(error => {
                console.error("Something went wrong fetching the music listings.");
            });
    }

    const getQueue= () => {
        fetch('/api/gms/room/queue', {
            method: 'GET',
            headers: {
                'roomId': props.match.params.roomId
            }
        })
            .then(response => response.json())
            .then(response => setQueue(response))
            .catch(error => {
                console.error("Something went wrong fetching the room queue.");
            });
    }

    useEffect(() => {
        console.log("called useEffect");
        getMusicListings();
        getQueue();
    }, [])

    return (
        <div className="room">
            <Header />
            <div className="room-content">
                <div>
                    <h3>Queue</h3>
                    <MusicList music={queue} />
                </div>
                <div className="room-info">
                    <h2>Room {props.match.params.roomId}</h2>
                    <p>Share this link with your friends to join!</p>
                    <p>{window.location.href}</p>
                    <audio controls src="/api/gms/room/now-playing">
                        Your browser does not support the <code>audio</code> element.
                    </audio>
                </div>
                <div>
                    <h3>Available Music</h3>
                    <MusicList music={musicList} />
                </div>
            </div>
        </div>
    );
}

export default withRouter(Room);