import {applyMiddleware, createStore} from 'redux';
import thunk from 'redux-thunk';
import rootReducer from './reducers/RootReducer';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;



const enhancer = function () {
    return composeEnhancers(applyMiddleware( thunk))
};


export default function (initialState = {}) {
    return createStore(rootReducer, initialState, enhancer());
}