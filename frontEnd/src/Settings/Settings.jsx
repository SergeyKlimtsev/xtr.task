import React from 'react';
import {
    Button,
    ButtonToolbar,
    FormControl,
    Row,
    Col,
} from 'react-bootstrap';
import {connect} from 'react-redux';
import {setUrl, setKeyWords, setCity, fetchSettings, submitSettings} from '../actions/SettingsActions';

class Settings extends React.Component {
    constructor(props) {
        super(props);
        props.fetchSettings();
        this.onChangeUrl = this.onChangeUrl.bind(this);
        this.onChangeKeyWords = this.onChangeKeyWords.bind(this);
        this.onChangeCity = this.onChangeCity.bind(this);
    }

    onChangeUrl(e) {
        this.props.setUrl(e.target.value);
    }

    onChangeKeyWords(e) {
        this.props.setKeyWords(e.target.value);
    }

    onChangeCity(e) {
        this.props.setCity(e.target.value);
    }

    render() {
        const {url, keyWords, city, submitSettings} = this.props;
        return <div className="content">
            <Row>
                <Col>
                    URL
                </Col>
                <Col>
                    <FormControl value={url}
                                 onChange={this.onChangeUrl}/>
                </Col>
            </Row>
            <Row>
                <Col>
                    Ключевые слова для поиска
                </Col>
                <Col>
                    <FormControl value={keyWords}
                                 onChange={this.onChangeKeyWords}/>
                </Col>
            </Row>
            <Row>
                <Col>
                    Город
                </Col>
                <Col>
                    <FormControl value={city}
                                 onChange={this.onChangeCity}/>
                </Col>
            </Row>
            <Row>
                <ButtonToolbar>
                    <Button onClick={submitSettings}>Сохранить настройки</Button>
                </ButtonToolbar>
            </Row>
        </div>
    };
}


export default connect(state=>state.settings, {
    setUrl,
    setKeyWords,
    setCity,
    fetchSettings,
    submitSettings
})(Settings);