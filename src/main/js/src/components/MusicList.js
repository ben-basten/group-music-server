function MusicList({name, music}) {
    return (
        <div className="music-list list-group">
            <h3>{name}</h3>
            <ul>
            {music ? music.map(track => (
                <li key={track.id} className="list-group-item">{track.prettyName}</li>
            )) : <li>Nothing to see here... yet!</li>}
            </ul>
        </div>
    );
}

export default MusicList;