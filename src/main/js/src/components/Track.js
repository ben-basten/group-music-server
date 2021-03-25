import { useState } from 'react';
import AddIcon from "../assets/icons/playlist.svg";
import SuccessIcon from "../assets/icons/added.svg";

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
                }, 3000);
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
            <img
                src={showSuccess ? SuccessIcon : AddIcon}
                alt="Add to queue"
                className="button"
                onClick={() => addToQueue(track.id)}
            />
        </li>
    );
}

export default Track;