import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Lobby from '../pages/Lobby';
import Room from '../pages/Room';
import NotFound from '../pages/NotFound';
import '../scss/main.scss';

function Router() {
    return (
      <BrowserRouter>
        <Switch>
          <Route exact path="/" component={Lobby} />
          <Route path="/room/:roomId" component={Room} />
          <Route component={NotFound} />
        </Switch>
      </BrowserRouter>
    );
  }
  
  export default Router;  