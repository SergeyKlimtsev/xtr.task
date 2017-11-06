import React from 'react';
import {
    Table,
    Button,
    ButtonToolbar,
    DropdownButton,
    MenuItem,
    Row,
    Col,
    Pagination
} from 'react-bootstrap';
import {refreshVacancies, changePageSize, changePage} from '../actions/VacanciesActions'
import {connect} from 'react-redux';


const Vacancies = props => {
    const {vacancies, pageSize, page, pageCount, refreshVacancies, changePageSize, changePage} = props;
    return (<div className="content">
        <Row>
            <ButtonToolbar>
                <Button onClick={refreshVacancies}>Обновить</Button>
            </ButtonToolbar>
        </Row>
        <Row>
            <Table hover>
                <thead>
                <tr>
                    <th>Вакансия</th>
                    <th>Зарплата</th>
                    <th>Компания</th>
                    <th>URL</th>
                </tr>
                </thead>
                <tbody>{
                    vacancies.map((item, i)=>(<tr key={'row_'+i}>
                        <td>{item.name}</td>
                        <td>{`От ${item.salaryFrom} до ${item.salaryTo}`}</td>
                        <td>{item.employer && item.employer.name}</td>
                        <td><a href={item.url}>{item.url}</a></td>
                    </tr>))
                } </tbody>
            </Table>
        </Row>
        <Row>
            <Col md={4}>
                <ButtonToolbar className="pull-left">
                    <DropdownButton title={pageSize} onSelect={changePageSize}
                                    id="dropdown-size-medium">
                        <MenuItem eventKey="5">5</MenuItem>
                        <MenuItem eventKey="10">10</MenuItem>
                        <MenuItem eventKey="20">20</MenuItem>
                    </DropdownButton>
                </ButtonToolbar>
            </Col>
            <Col mdOffset={6} md={6}>
                <Pagination
                    className="pull-right"
                    prev
                    next
                    first
                    last
                    ellipsis
                    boundaryLinks
                    items={pageCount}
                    maxButtons={5}
                    activePage={page}
                    onSelect={changePage}
                />
            </Col>
        </Row>
    </div>)
};

export default connect(state=>state.vacancies, {refreshVacancies, changePageSize, changePage})(Vacancies);