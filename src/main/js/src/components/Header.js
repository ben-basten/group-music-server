import { NavLink } from 'react-router-dom';

function Header() {
    return (
      <nav>
          <h1 className="title">Listening Party</h1>
          <NavLink activeClassName="nav-link" className="nav-link" to="/">Lobby</NavLink>
          <span className="spacer">&middot;</span>
          <NavLink activeClassName="nav-link" className="nav-link" to="/join">Join</NavLink> 
          <span className="spacer">&middot;</span>
          <a href="https://github.com/ben-basten/group-music-server" className="nav-link" target="_blank">GitHub</a>
      </nav>
    );
  }
  
  export default Header;