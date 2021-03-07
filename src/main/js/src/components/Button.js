function Button({text, action}) {
    return (
        <div className="button">
            <input type="button" value={text} onClick={action} />
        </div>
    );
}

export default Button;