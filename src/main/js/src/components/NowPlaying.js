import Skip from '../assets/icons/next.svg'

function NowPlaying({track, roomId, setQueue}) {

    const nextTrack = () => {
        fetch('/api/gms/room/next-track', {
            method: 'POST',
            headers: {
                'roomId': roomId,
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.json())
            .then(response => setQueue(response))
            .catch(error => {
                console.error("Something went wrong while going to the next track.");
            });
    }

    return (
        <div className="now-playing">
            <h3>{track && track.title}</h3>
            <p>{track && track.artist}</p>
            <p>{track && track.album}</p>

            {track ?
                <>
                    <audio controls src={`/api/gms/room/now-playing?roomId=${roomId}`}>
                        Your browser does not support the <code>audio</code> element.
                    </audio>
                    <img src={Skip} alt="Skip button" className="skip" onClick={nextTrack} />
                </>
            : null}
        </div>
    );
}

export default NowPlaying;