import { useState, useEffect } from 'react';
import { withRouter } from 'react-router-dom';
import { Client } from '@stomp/stompjs';
import Header from '../components/Header';
import TrackList from '../components/TrackList';
import NowPlaying from '../components/NowPlaying';
import Queue from '../components/Queue';
import API from "../utils/API";

function Room(props) {
    window.history.replaceState(null, '');

    const [musicList, setMusicList] = useState([]);
    const [queue, setQueue] = useState([]);
    const [client] = useState(new Client());

    const getMusicListings = async () => {
        return fetch('/api/gms/tracks', {
            method: 'GET'
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
                    setQueue(response.queue);
                }
            });
    }

    const connectToSocket = () => {
        let websocketUrl;
        if(window.location.host.includes(":3000")) { // Development
            websocketUrl = `ws://${window.location.hostname}:8080/api/gms/ws-connect`;
        } else { // Production
            websocketUrl = `ws://${window.location.hostname}/api/gms/ws-connect`;
        }
        client.configure({
            brokerURL: websocketUrl,
            onConnect: () => {
                client.subscribe(`/topic/room/${props.match.params.roomId}/queue`, message => {
                    setQueue(JSON.parse(message.body));
                })
            },
            debug: str => {
                console.log(new Date(), str);
            }
        });

        client.activate();
    }

    useEffect(() => {
        // Step 1: figure out where the user came from
        if(props.location.state && props.location.state.room) {
            // the user came here from the join page
            setQueue(props.location.state.room.queue);
        } else {
            // the user navigated directly to the room page in the browser or refreshed
            attemptJoin();
        }

        // Step 2: asynchronously get the available music listings
        getMusicListings()
            .then(response => response.json())
            .then(response => setMusicList(response))
            .catch(() => {
                console.error("Something went wrong fetching the music listings.");
            });

        // Step 3: connect to the WebSocket to make the room responsive!
        connectToSocket();
    }, []);

    return (
        <div className="room">
            <Header />
            <div className="room-content">
                <Queue music={queue} />
                <div className="room-details grid-item">
                    <h2>Room {props.match.params.roomId}</h2>
                    <div className="card">
                        <div className="card-body">
                            <h5 className="card-title">{window.location.href}</h5>
                            <h6 className="card-subtitle">Share this link for others to join the room!</h6>
                        </div>
                    </div>
                    <NowPlaying
                        track={queue[0]}
                        roomId={props.match.params.roomId}
                        setQueue={setQueue}
                        socketClient={client}
                    />
                </div>
                <TrackList
                    music={musicList}
                    roomId={props.match.params.roomId}
                    setQueue={setQueue}
                    socketClient={client}
                />
            </div>
        </div>
    );
}

export default withRouter(Room);