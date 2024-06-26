import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter} from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap';
import '@fortawesome/fontawesome-free/css/fontawesome.css';
import '@fortawesome/fontawesome-free/css/solid.css';


import './index.css';
import registerServiceWorker from './registerServiceWorker';

import { App } from "./modules/app";
import {Provider} from 'react-redux';
import store from './store';

ReactDOM.render(
	<BrowserRouter>
	 	<Provider store={store}>
	 		<App />
	 	</Provider>
	 </BrowserRouter>
	 	, document.getElementById('root'));
registerServiceWorker();