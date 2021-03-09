function Button({text, action}) {
    return (
        <button type="button" className="btn btn-success" onClick={action}>{text}</button>
    );
}

export default Button;