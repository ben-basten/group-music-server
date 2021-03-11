import QueueIcon from '../assets/icons/playlist.svg'

function AvailableMusic({music, roomId, setQueue, socketClient}) {

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
        <div className="music-list list-group">
            <h3 className="column-header">Available Music</h3>
            <ul>
            {music && music.length > 0 ? music.map(track => (
                <li key={track.id} className="list-group-item">
                    <div>
                        {track.title} <br/>
                        <small className="text-muted">{track.artist} &middot; {track.album}</small><br/>
                    </div>
                    <img src={QueueIcon} alt="Add to queue" onClick={() => addToQueue(track.id)} />
                </li>
            )) : null}
            </ul>
        </div>
    );
}

export default AvailableMusic;