import QueueIcon from '../assets/icons/playlist.svg'

function MusicList({name, music, roomId}) {

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
            .then(response => console.log(response))
            .catch(error => {
                console.error("Something went wrong while adding a song to the queue.");
            });
    }

    return (
        <div className="music-list list-group">
            <h3>{name}</h3>
            <ul>
            {music ? music.map(track => (
                <li key={track.id} className="list-group-item">
                    <div>
                        {track.title} <br/>
                        <small className="text-muted">{track.artist} &middot; {track.album}</small><br/>
                    </div>
                    <img src={QueueIcon} alt="Add to queue" onClick={() => addToQueue(track.id)} />
                </li>
            )) : <li>Nothing to see here... yet!</li>}
            </ul>
        </div>
    );
}

export default MusicList;