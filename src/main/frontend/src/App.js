import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import Login from './components/Login'
import './App.scss';

const App = () => {
	return (
		<Router>
			<div className="App">
				<Switch>
					<Route exact path="/" component={Login} />
				</Switch>
			</div>
		</Router>
	);
}

export default App;
