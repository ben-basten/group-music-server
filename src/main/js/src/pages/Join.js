import {useState, useRef, useEffect} from 'react';
import API from '../utils/API';
import Button from '../components/Button';

function Join(props) {

    const [userInput, setUserInput] = useState("");
    const [error, setError] = useState("");

    const roomIdRef = useRef();

    const attemptJoin = () => {
        let reg = new RegExp('^\\d{4}$');
        if(userInput === "") {
            setError("Please enter an ID.");
        } else if(!reg.test(userInput)) {
            setError("ID must be a 4 digit number.");
        } else {
            API.joinRoom(userInput)
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
        }
    }

    const goToLobby = () => {
        props.history.push({
            pathname: '/'
        });
    }

    const handleKeyDown = (e) => {
        if(e.key === 'Enter') attemptJoin();
    }

    useEffect(() => {
        roomIdRef.current.focus();
    }, []);

    return (
        <div className="lobby card">
            <a onClick={goToLobby}>&lt; back</a>
            <div className="card-body">
                <h1 className="card-title">Join</h1>
                <input
                    ref={roomIdRef}
                    type="text"
                    placeholder="4-Digit Room ID"
                    maxLength={4}
                    value={userInput}
                    onKeyDown={handleKeyDown}
                    onChange={event => setUserInput(event.target.value)}
                />
                <Button text={"Join now!"} action={attemptJoin} />
                <p className={error ? "error" : "error hidden"}>{error ? error : "placeholder"}</p>
            </div>
        </div>
    );
}

export default Join;