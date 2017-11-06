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
                <div>
                    URL
                </div>
                <div>
                    <FormControl value={url}
                                 onChange={this.onChangeUrl}/>
                </div>
            </Row>
            <Row>
                <div>
                    Ключевые слова для поиска
                </div>
                <div>
                    <FormControl value={keyWords}
                                 onChange={this.onChangeKeyWords}/>
                </div>
            </Row>
            <Row>
                <div>
                    Город
                </div>
                <div>
                    <FormControl value={city}
                                 onChange={this.onChangeCity}/>
                </div>
            </Row>
            <Row>
                <div style={{paddingTop:20}}>
                    <ButtonToolbar>
                        <Button onClick={submitSettings}>Сохранить настройки</Button>
                    </ButtonToolbar>
                </div>
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