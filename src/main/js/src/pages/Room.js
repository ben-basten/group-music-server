import { useState, useEffect } from 'react';
import {withRouter} from 'react-router-dom';
import Header from '../components/Header';
import AvailableMusic from '../components/AvailableMusic';
import NowPlaying from '../components/NowPlaying';
import Queue from '../components/Queue';
import API from "../utils/API";

function Room(props) {
    window.history.replaceState(null, '');
    const Stomp = require('stompjs');
    const SockJS = require('sockjs-client');

    const [musicList, setMusicList] = useState([]);
    const [queue, setQueue] = useState([]);

    const getMusicListings = () => {
        fetch('/api/gms/tracks', {
            method: 'GET'
        })
            .then(response => response.json())
            .then(response => setMusicList(response))
            .catch(() => {
                console.error("Something went wrong fetching the music listings.");
            });
    }

    const attemptJoin = () => {
        API.joinRoom(props.match.params.roomId)
            .then(response => {
                if(!response || !response.roomId) {
                    // room doesn't exist, go to home page
                    props.history.replace({
                        pathname: '/',
                        state: { error: 'Invalid room ID. Please create or join a different one.' }
                    });
                } else {
                    setQueue(response.queue)
                }
            });
    }

    const connectToSocket = () => {
        let socket = new SockJS('/api/gms/connect', null, { timeout: 5000 });
        let stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log(`Connected: ${frame}`);
            stompClient.subscribe('/topic/greetings', function(greeting) {
               console.log("received greeting");
               console.log(greeting);
            });
            stompClient.send("/api/gms/ws/hello", {}, JSON.stringify("hello world"));
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
            .catch(() => {
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
        connectToSocket();
    }, [])

    return (
        <div className="room">
            <Header />
            <div className="room-content">
                <Queue music={queue} />
                <div className="room-details">
                    <h2>Room {props.match.params.roomId}</h2>
                    <div className="card">
                        <div className="card-body">
                            <h5 className="card-title">{window.location.href}</h5>
                            <h6 className="card-subtitle">Share this link for others to join the room!</h6>
                        </div>
                    </div>
                    <NowPlaying track={queue[0]} roomId={props.match.params.roomId} setQueue={setQueue} />
                </div>
                <AvailableMusic music={musicList} roomId={props.match.params.roomId} setQueue={setQueue} />
            </div>
        </div>
    );
}

export default withRouter(Room);