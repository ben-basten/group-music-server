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
            .catch(() => {
                console.error("Something went wrong while going to the next track.");
            });
    }

    return (
        <div className="now-playing">
            {track ?
                <>
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">{track.title}</h4>
                            <h6 className="card-subtitle artist">{track.artist}</h6>
                            <h6 className="card-subtitle album">{track.album}</h6>
                        </div>
                    </div>
                    <AudioPlayer
                        autoPlay
                        src={`/api/gms/room/now-playing?roomId=${roomId}&trackId=${track.id}`} // add track id param to force refresh
                        showSkipControls={true}
                        showJumpControls={false}
                        onEnded={nextTrack}
                        onClickNext={nextTrack}
                        className="player"
                    />
                </>
            : null}
        </div>
    );
}

export default NowPlaying;