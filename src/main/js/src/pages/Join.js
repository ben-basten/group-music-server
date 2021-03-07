import { useState } from 'react';
import Button from '../components/Button';

function Join(props) {

    const [userInput, setUserInput] = useState("")

    const attemptJoin = () => {
        fetch('/api/gms/join', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userInput)
        })
            .then(response => response.json())
            .then(response => {
                if(response.roomId) {
                    props.history.push({
                        pathname: `/room/${response.roomId}`
                        // state: { error: `Invalid Room ID: ${props.match.params.roomId}` }
                    });
                }
            })
            .catch(error => {
                console.error("Something went wrong while adding a song to the queue.");
            });
    }

    return (
        <div className="join">
            <h1>Join</h1>
            <input type="text" placeholder="Room ID" value={userInput} onChange={event => setUserInput(event.target.value)} />
            <Button text={"Join now!"} action={attemptJoin} />
        </div>
    );
}

export default Join;