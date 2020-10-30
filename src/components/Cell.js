import React, {Component} from 'react'
import './style.css'

export default class Cell extends Component {
    constructor(props) {
        super(props)

        this.state = {
            x: props.x,
            y:props.y,
            isShip: props.isShip,
            wasShot: props.wasShot,
            isShipVisible: props.isShipVisible,
            handleClick: props.handleClick
        }
    }

    render() {
        return (
            <button onClick={this.state.handleClick}>
                <div className={
                    `cell ${this.state.isShip && this.state.wasShot ? 'ship' : ''}`
                }>
                    {this.state.wasShot ? (this.state.isShip ? 'X' : '·') : ''}
                </div>
            </button>
        )
    }
}