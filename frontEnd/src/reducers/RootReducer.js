import {combineReducers} from 'redux';
import VacanciesReducer from './VacanciesReducer';
import SettingsReducer from './SettingsReducer';


export default combineReducers({
    vacancies: VacanciesReducer,
    settings: SettingsReducer
})