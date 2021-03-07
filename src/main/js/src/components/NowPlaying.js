import AudioPlayer from 'react-h5-audio-player';
import 'react-h5-audio-player/lib/styles.css';

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
                <AudioPlayer
                    autoPlay
                    src={`/api/gms/room/now-playing?roomId=${roomId}`}
                    showSkipControls={true}
                    showJumpControls={false}
                    onEnded={nextTrack}
                    onClickNext={nextTrack}
                    className="player"
                />
            : null}
        </div>
    );
}

export default NowPlaying;