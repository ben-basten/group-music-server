import PlayIcon from '../assets/icons/play.svg';

function Queue({music}) {
    return (
        <div className="music-list list-group grid-item">
            <h3 className="column-header">Queue</h3>
            <ul className="queue">
            {music && music.length > 0 ? music.map((track, index) => (
                <li key={index * -1} className="list-group-item">
                    <div>
                        {track.title} <br/>
                        <small className="text-muted">{track.artist} &middot; {track.album}</small><br/>
                    </div>
                    <small className="text-muted tip">Up next</small>
                    <img src={PlayIcon} alt="Play icon" />
                </li>
            )) : <li className={"list-group-item message"}>Nothing to see here... yet!</li>}
            </ul>
        </div>
    );
}

export default Queue;