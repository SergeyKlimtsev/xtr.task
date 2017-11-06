import React, {Component, PropTypes} from 'react';

import {Row, Col, Button, NavDropdown, MenuItem} from 'react-bootstrap';
import Nav from 'react-bootstrap/lib/Nav';
import Navbar from 'react-bootstrap/lib/Navbar';
import NavItem  from 'react-bootstrap/lib/NavItem';
import {Link, NavLink} from 'react-router';
import LinkContainer from 'react-router-bootstrap/lib/LinkContainer';
import {connect} from 'react-redux';




class NavigationBar extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Navbar fluid>
                <Navbar.Header>
                    <Navbar.Brand>
                        <Link to={'/vacancies'}>xtr.task</Link>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>
                <Navbar.Collapse>
                    <Nav navbar>
                        <LinkContainer to='/vacancies'>
                            <NavItem>Вакансии</NavItem>
                        </LinkContainer>
                        <LinkContainer to='/settings'>
                            <NavItem>Настройки</NavItem>
                        </LinkContainer>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        );
    }
}


export default connect()(NavigationBar);






