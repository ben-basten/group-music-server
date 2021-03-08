function Button({text, action}) {
    return (
        <button type="button" className="btn btn-primary" onClick={action}>{text}</button>
    );
}

export default Button;