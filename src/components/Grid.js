import React, {Component} from 'react'
import Row from "./Row";
import './style.css'

export default class Grid extends Component {
    constructor(props) {
        super(props);

        this.state = {
            playersField : props.playersField,
            handleClick: props.handleClick
        }
    }

    render() {
        return (
            this.state.playersField.map((row, index) =>
                <Row
                    key={index}
                    className="row"
                    rowCells={row}
                    onClick={
                        (x, y) => this.state.handleClick(x, y)
                    }
                />
            )
        )
    }
}