function NowPlaying({track, roomId}) {
    return (
        <div className="now-playing">
            <h3>{track.title ? track.title : "Queue is empty."}</h3>
            <p>{track.artist}</p>
            <p>{track.album}</p>

            <audio controls src={`/api/gms/room/now-playing?roomId=${roomId}`}>
                Your browser does not support the <code>audio</code> element.
            </audio>
        </div>
    );
}

export default NowPlaying;