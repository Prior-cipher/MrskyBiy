import React, {Component} from 'react'
import Row from "./Row";
import './style.css'

export default class Grid extends Component {
    constructor(props) {
        super(props);

        this.state = {
            playersField : props.playersField
        }
    }

    render() {
        return (
            this.state.playersField.map((row, index) =>
                <Row
                    key={index}
                    className="row"
                    rowCells={row}
                />
            )
        )
    }
}