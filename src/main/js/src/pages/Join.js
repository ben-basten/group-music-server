import Button from '../components/Button';

function Join() {
    return (
        <div className="join">
            <h1>Join</h1>
            <input type="text" placeholder="Room ID" />
            <Button text={"Join now!"} />
        </div>
    );
}

export default Join;