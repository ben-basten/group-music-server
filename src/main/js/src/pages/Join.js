import { useState } from 'react';
import API from '../utils/API';
import Button from '../components/Button';

function Join(props) {

    const [userInput, setUserInput] = useState("")

    const attemptJoin = () => {
        API.joinRoom(userInput)
            .then(response => {
                if(response.roomId) {
                    props.history.push({
                        pathname: `/room/${response.roomId}`,
                        state: { room: response }
                    });
                }
            })
    }

    return (
        <div className="lobby card">
            <div className="card-body">
                <h1 className="card-title">Join</h1>
                <input type="text" placeholder="Room ID" value={userInput} onChange={event => setUserInput(event.target.value)} />
                <Button text={"Join now!"} action={attemptJoin} />
            </div>
        </div>
    );
}

export default Join;