import { useState, useEffect } from 'react';
import {withRouter} from 'react-router-dom';
import Header from '../components/Header';
import AvailableMusic from '../components/AvailableMusic';
import NowPlaying from '../components/NowPlaying';
import Queue from '../components/Queue';
import API from "../utils/API";

function Room(props) {
    window.history.replaceState(null, '');

    const [musicList, setMusicList] = useState([]);
    const [queue, setQueue] = useState([]);

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

    const attemptJoin = () => {
        API.joinRoom(props.match.params.roomId)
            .then(response => {
                console.log(response)
                if(!response.roomId) {
                    // room doesn't exist, go to home page
                    props.history.push({
                        pathname: '/',
                        state: { error: `Invalid Room ID: ${props.match.params.roomId}` }
                    });
                } else {
                    setQueue(response.queue)
                }
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
        if(props.location.state && props.location.state.room) {
            // the user came here from the join page
            setQueue(props.location.state.room.queue);
        } else {
            // the user navigated directly to the room page in the browser
            attemptJoin();
        }
        getMusicListings();
        // getQueue();
    }, [])

    return (
        <div className="room">
            <Header />
            <div className="room-content">
                <Queue music={queue} />
                <div className="room-details">
                    <h2>Room {props.match.params.roomId}</h2>
                    <p className="info">Share this link with your friends to join!</p>
                    <p className="info">{window.location.href}</p>
                    <NowPlaying track={queue[0]} roomId={props.match.params.roomId} />
                </div>
                <AvailableMusic music={musicList} roomId={props.match.params.roomId} />
            </div>
        </div>
    );
}

export default withRouter(Room);