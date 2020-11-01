import React, {Component} from 'react'
import './style.css'

export default class Cell extends Component {
    constructor(props) {
        super(props)

        this.state = {
            x: props.x,
            y: props.y,
            isShip: props.isShip,
            isShipVisible: props.whose === 'player',
            wasShot: false,
            marker: '',
            whose: props.whose
        }
    }

    shot = () => {
        // return await fetch('', {
        //     method: 'POST',
        //     headers: 'application/json;charset=utf-8',
        //      body: JSON.stringify({
        //          'x': this.state.x,
        //          'y': this.state.y
        //      })
        // })

        this.setState(state => {
            return {
                wasShot: true,
                isShipVisible: true,
                marker: state.isShip ? 'X' : '·'
            }
        })
    }

    render() {
        return (
            <li>
                <button
                    onClick={this.shot}
                    className={`cell ${
                        this.state.isShipVisible &&
                        this.state.isShip    
                            ? 'ship' : ''
                    }`}
                >
                    {this.state.marker}
                </button>
            </li>
        )
    }
}