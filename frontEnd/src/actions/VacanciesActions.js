import axios from 'axios';

export const SET_VACANCIES = 'SET_VACANCIES';
export const SET_PAGE_SIZE = 'SET_PAGE_SIZE';
export const SET_PAGE = 'SET_PAGE';
export const SET_PAGE_COUNT = 'SET_PAGE_COUNT';

export const setVacancies = vacancies => ({type: SET_VACANCIES, vacancies});
export const setPageSize = pageSize => ({type: SET_PAGE_SIZE, pageSize});
export const setPage = page => ({type: SET_PAGE, page});
export const setPageCount = pageCount => ({type: SET_PAGE_COUNT, pageCount});


const VACANCIES_URL = '/rest/vacancies/pages';

const fetchVacancies = (page, pageSize) => dispatch =>
    axios.get(VACANCIES_URL, {
        params: {
            pageNumber: page,
            pageSize
        }
    })
        .then(response => {
            const {data} = response;
            dispatch(setPageCount(data.totalPages));
            dispatch(setVacancies(data.content))
        })
        .catch(error => console.log(error));


export const changePageSize = pageSize=>(dispatch, getState)=> {
    dispatch(setPageSize(pageSize));
    const {page} = getState().vacancies;
    dispatch(fetchVacancies(page, pageSize));
};

export const changePage = page=>(dispatch, getState)=> {
    dispatch(setPage(page));
    const {pageSize} = getState().vacancies;
    dispatch(fetchVacancies(page, pageSize));
};

export const refreshVacancies = ()=>(dispatch, getState)=> {
    const {page, pageSize} = getState().vacancies;
    dispatch(fetchVacancies(page, pageSize));
};