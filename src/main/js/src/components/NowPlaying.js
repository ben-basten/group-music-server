function NowPlaying({track, roomId}) {
    return (
        <div className="now-playing">
            <h3>{track && track.title}</h3>
            <p>{track && track.artist}</p>
            <p>{track && track.album}</p>

            {track ?
                <audio controls src={`/api/gms/room/now-playing?roomId=${roomId}`}>
                    Your browser does not support the <code>audio</code> element.
                </audio>
            : null}
        </div>
    );
}

export default NowPlaying;