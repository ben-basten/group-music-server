import { useState } from 'react';
import Filter from "./Filter";
import Track from "./Track";

function TrackList({music, roomId, setQueue, socketClient}) {

    const [filterInput, setFilterInput] = useState();

    return (
        <div className="music-list list-group grid-item">
            <h3 className="column-header">Available Music</h3>
            <Filter filterInput={filterInput} setFilterInput={setFilterInput} />
            <ul>
            {music && music.length > 0 ? music
                .filter(track => // filters the song titles/artists/albums by the user inputted filter
                    !filterInput
                    || track.title.toLowerCase().indexOf(filterInput.toLowerCase()) > -1
                    || track.artist.toLowerCase().indexOf(filterInput.toLowerCase()) > -1
                    || track.album.toLowerCase().indexOf(filterInput.toLowerCase()) > -1)
                .map(track => (
                    <Track
                        track={track}
                        roomId={roomId}
                        setQueue={setQueue}
                        socketClient={socketClient}
                    />
            )) :
                <div className="spinner-border text-success spinner" role="status">
                    <span className="sr-only">Loading...</span>
                </div>
            }
            </ul>
        </div>
    );
}

export default TrackList;