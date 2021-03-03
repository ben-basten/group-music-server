function MusicList({music}) {
    return (
        <div className="music-list">
            <ul>
            {music ? music.map(track => (
                <li key={track.id}>{track.prettyName}</li>
            )) : null}
            </ul>
        </div>
    );
}

export default MusicList;