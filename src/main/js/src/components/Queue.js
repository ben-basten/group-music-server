import Track from "./Track";

function Queue({music}) {
    return (
        <div className="music-list list-group grid-item">
            <h3 className="column-header">Queue</h3>
            <ul className="queue">
            {music && music.length > 0 ? music.map((track, index) => (
                <Track
                    key={index * -1}
                    track={track}
                />
            )) : <li className={"list-group-item message"}>Nothing to see here... yet!</li>}
            </ul>
        </div>
    );
}

export default Queue;