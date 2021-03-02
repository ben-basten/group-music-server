import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Lobby from '../pages/Lobby';
import Room from '../pages/Room';
import Join from '../pages/Join';
import NotFound from '../pages/NotFound';
import '../scss/main.scss';

function Router() {
    return (
      <BrowserRouter>
        <Switch>
          <Route exact path="/" component={Lobby} />
          <Route path="/room/:roomId" component={Room} />
          <Route path="/join" component={Join} />
          <Route component={NotFound} />
        </Switch>
      </BrowserRouter>
    );
  }
  
  export default Router;  