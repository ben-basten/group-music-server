function Filter({filterInput, setFilterInput}) {
    return (
        <div className="filter">
            <input
                type="text"
                className="filter"
                placeholder="Filter music"
                value={filterInput || ''}
                onChange={event => setFilterInput(event.target.value)}
            />
            <a onClick={() => setFilterInput('')} className="ui-button">x</a>
        </div>
    );
}

export default Filter;