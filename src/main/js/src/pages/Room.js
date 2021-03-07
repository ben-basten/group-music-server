import { useState, useEffect } from 'react';
import {withRouter} from 'react-router-dom';
import Header from '../components/Header';
import MusicList from '../components/MusicList';
import NowPlaying from "../components/NowPlaying";

function Room(props) {
  // props.history.push({
  //   pathname: '/',
  //   state: { error: `Invalid Room ID: ${props.match.params.roomId}` }
  // });

    const [musicList, setMusicList] = useState([]);
    const [queue, setQueue] = useState([]);
    const [currentTrack, setCurrentTrack] = useState([]);

    const getMusicListings = () => {
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
        getMusicListings();
        // getQueue();
    }, [])

    return (
        <div className="room">
            <Header />
            <div className="room-content">
                <MusicList name={"Queue"} music={queue} />
                <div className="room-details">
                    <h2>Room {props.match.params.roomId}</h2>
                    <p className="info">Share this link with your friends to join!</p>
                    <p className="info">{window.location.href}</p>
                    <NowPlaying track={currentTrack} roomId={props.match.params.roomId} />
                </div>
                <MusicList name={"Available Music"} music={musicList} roomId={props.match.params.roomId} />
            </div>
        </div>
    );
}

export default withRouter(Room);