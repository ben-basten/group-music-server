import Button from '../components/Button';
import {useEffect, useState} from "react";

function Lobby(props) {
    window.history.replaceState(null, ''); // clear the error message on refresh

    const [error, setError] = useState("");

    const createRoom = () => {
        fetch('/api/gms/create', {
            method: 'POST'
        })
            .then(response => response.json())
            .then(response => {
                if(response.roomId) {
                    props.history.push({
                        pathname: `/room/${response.roomId}`,
                        state: { room: response }
                    });
                } else {
                    setError(response.message);
                }
            })
            .catch(() => {
                console.error("Something went wrong while creating a room.");
            });
    }

    const joinRoom = () => {
        props.history.push({
            pathname: '/join'
        });
    }

    useEffect(() => {
        if(props.location.state && props.location.state.error) {
            // the user came here from the join page
            setError(props.location.state.error);
        }
    }, [])

    return (
        <div className="lobby card">
            <div className="card-body">
                <h1 className="card-title">Listening Party</h1>
                <Button text="Create Room" action={createRoom} />
                <Button text="Join Room" action={joinRoom} />
                <p className={error ? "error" : "error hidden"}>{error ? error : "placeholder"}</p>
            </div>
        </div>
    );
}

export default Lobby;