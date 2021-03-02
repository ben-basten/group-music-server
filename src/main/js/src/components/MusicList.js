function MusicList({music}) {
    return (
        <div className="music-list">
            <ul>
            {music ? music.map((name, index) => (
                <li key={index}>{name}</li>
            )) : null}
            </ul>
        </div>
    );
}

export default MusicList;