function Button({text}) {
    return (
        <div className="button">
            <input type="button" value={text} />
        </div>
    );
}

export default Button;