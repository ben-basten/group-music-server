export default {
    joinRoom: (roomId) => { // TODO: handle 404's
        return fetch('/api/gms/join', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(roomId)
        })
            .then(response => response.json())
            .catch(error => {
                console.error("Something went wrong while trying to join the room.");
            });
    }
}