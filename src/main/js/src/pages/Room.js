import { useState, useEffect } from 'react';
import { withRouter } from 'react-router-dom';
import { Client } from '@stomp/stompjs';
import Header from '../components/Header';
import AvailableMusic from '../components/AvailableMusic';
import NowPlaying from '../components/NowPlaying';
import Queue from '../components/Queue';
import Button from '../components/Button';
import API from "../utils/API";

function Room(props) {
    window.history.replaceState(null, '');

    const [musicList, setMusicList] = useState([]);
    const [queue, setQueue] = useState([]);
    const [client] = useState(new Client());

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
                    setQueue(response.queue);
                }
            });
    }

    const goToLobby = () => {
        props.history.push({
            pathname: '/'
        });
    }

    const connectToSocket = () => {
        client.configure({
            brokerURL: `ws://${window.location.host}/api/gms/ws-connect`,
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
        if(props.location.state && props.location.state.room) {
            // the user came here from the join page
            setQueue(props.location.state.room.queue);
        } else {
            // the user navigated directly to the room page in the browser or refreshed
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
                <AvailableMusic
                    music={musicList}
                    roomId={props.match.params.roomId}
                    setQueue={setQueue}
                    socketClient={client}
                />
            </div>
            <Button
                text={"Go to lobby"}
                action={goToLobby}
                className="mobile-reveal"
            />
        </div>
    );
}

export default withRouter(Room);