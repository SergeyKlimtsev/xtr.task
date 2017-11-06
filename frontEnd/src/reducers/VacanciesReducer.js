import {SET_VACANCIES, SET_PAGE, SET_PAGE_SIZE, SET_PAGE_COUNT} from '../actions/VacanciesActions';

const initialState = {
    vacancies: [],
    pageSize: 10,
    pageCount: 1,
    page: 1
};

export default function (state = initialState, action) {
    switch (action.type) {
        case SET_VACANCIES:
            return {...state, vacancies: action.vacancies};
        case SET_PAGE:
            return {...state, page: action.page};
        case SET_PAGE_SIZE:
            return {...state, pageSize: action.pageSize};
        case SET_PAGE_COUNT:
            return {...state, pageCount: action.pageCount};
        default:
            return state;
    }
}