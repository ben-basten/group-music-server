import { useState } from 'react';
import AddIcon from "../assets/icons/playlist.svg";
import SuccessIcon from "../assets/icons/added.svg";
import PlayIcon from "../assets/icons/play.svg";

function Track({track, roomId, setQueue, socketClient}) {

    const [showSuccess, setShowSuccess] = useState(false);

    const addToQueue = (trackId) => {
        fetch('/api/gms/room/queue/add', {
            method: 'POST',
            headers: {
                'roomId': roomId,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(trackId)
        })
            .then(response => response.json())
            .then(response => {
                setShowSuccess(true);
                setTimeout(function(){
                    setShowSuccess(false);
                }, 1000);
                if(socketClient.connected) {
                    socketClient.publish({destination: '/api/gms/ws/queue/update', body: roomId});
                } else {
                    setQueue(response);
                }
            })
            .catch(() => {
                console.error("Something went wrong while adding a song to the queue.");
            });
    }

    return (
        <li className="list-group-item track">
            <div>
                {track.title} <br/>
                <small className="text-muted">{track.artist} &middot; {track.album}</small><br/>
            </div>
            { setQueue ? // track list - add to queue button
                <img
                    src={showSuccess ? SuccessIcon : AddIcon}
                    alt="Add to queue"
                    className="button"
                    onClick={() => addToQueue(track.id)}
                />
                : // queue - decorative icons
                <>
                    <small className="text-muted tip">Up next</small>
                    <img src={PlayIcon} alt="Play icon" />
                </>
            }
        </li>
    );
}

export default Track;