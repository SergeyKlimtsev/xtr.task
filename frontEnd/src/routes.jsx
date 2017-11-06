import React from 'react';
import {IndexRoute, Route}  from 'react-router';
import App from './App';
import Vacancies from './Vacancies'
import Settings from './Settings'


export default (
    <Route component={App} path='/'>
        <IndexRoute component={Vacancies}/>
        <Route component={Settings} path='settings'/>
        <Route component={Vacancies} path='vacancies'/>
    </Route>
);