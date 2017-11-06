import React, {Component, PropTypes} from 'react';
/*import Grid  from 'react-bootstrap/lib/Grid';*/
import {Row, Col, Grid, Jumbotron, Button} from 'react-bootstrap';

import {Link} from 'react-router';

import {connect} from 'react-redux';
import NavigationBar from '../NavigationBar';
import './bootstrap.css';
import './customStyle.css';

const propTypes = {
    children: PropTypes.node
};

class App extends Component {
    render() {
        return (
            <div>
                <NavigationBar/>
                <Grid fluid>
                    <Row>
                        <Col className="main-container">{this.props.children}</Col>
                    </Row>
                </Grid>
            </div>
        );
    }
}


App.propTypes = propTypes;

export default App;

