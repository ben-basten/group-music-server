import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './Header';
import Lobby from '../pages/Lobby';
import Room from '../pages/Room';
import NotFound from '../pages/NotFound';
import Header from './Header';
import '../scss/main.scss';

function Router() {
    return (
      <div className="router">
        <Header />
        <BrowserRouter>
          <Switch>
            <Route exact path="/" component={Lobby} />
            <Route path="/room/:roomId" component={Room} />
            <Route component={NotFound} />
          </Switch>
        </BrowserRouter>
      </div>
    );
  }
  
  export default Router;  