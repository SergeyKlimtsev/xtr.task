import {SET_CITY, SET_KEYWORDS, SET_URL} from '../actions/SettingsActions';

const initialState = {
    city: '',
    keyWords: '',
    url: ''
};

export default function (state = initialState, action) {
    switch (action.type) {
        case SET_CITY:
            return {...state, city: action.city};
        case SET_KEYWORDS:
            return {...state, keyWords: action.keyWords};
        case SET_URL:
            return {...state, url: action.url};
        default:
            return state;
    }
}