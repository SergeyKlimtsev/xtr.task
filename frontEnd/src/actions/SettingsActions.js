import axios from 'axios';

export const SET_URL = 'SET_URL';
export const SET_KEYWORDS = 'SET_KEYWORDS';
export const SET_CITY = 'SET_CITY';

export const setUrl = url=>({type: SET_URL, url});

export const setKeyWords = keyWords=>({type: SET_KEYWORDS, keyWords});

export const setCity = city=>({type: SET_CITY, city});


const SETTINGS_URL = '/rest/settings';

export const fetchSettings = () => dispatch => {
    axios.get(SETTINGS_URL)
        .then(response => {
            const {data}=response;
            dispatch(setUrl(data.url));
            dispatch(setCity(data.city));
            dispatch(setKeyWords(data.keyWords));
        })
        .catch(error => console.log(error));
};

export const submitSettings = () => (dispatch, getState) => {
    const {url, city, keyWords} = getState().settings;
    axios.post(SETTINGS_URL, {url, city, keyWords})
        .catch(error=>console.log(error));
};