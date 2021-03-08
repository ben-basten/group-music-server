import Button from '../components/Button';

function Lobby(props) {
    window.history.replaceState(null, ''); // clear the error message on refresh

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

    return (
        <div className="lobby card">
            <div className="card-body">
                <h1 className="card-title">Listening Party</h1>
                <Button text="Create Room" action={createRoom} />
                <Button text="Join Room" action={joinRoom} />
                {props.location.state  &&
                    <div className="alert alert-warning" role="alert">{props.location.state.error}</div>
                }
            </div>
        </div>
    );
}

export default Lobby;