import { NavLink } from 'react-router-dom';

function Header() {
    return (
      <nav>
          <NavLink className="title" to="/">Listening Party</NavLink>
          <div>
              <NavLink activeClassName="nav-link" className="nav-link" to="/join">Join</NavLink>
              <span className="spacer">&middot;</span>
              <a href="https://github.com/ben-basten/group-music-server" className="nav-link" target="_blank">GitHub</a>
          </div>
      </nav>
    );
  }
  
  export default Header;