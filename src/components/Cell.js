import React, {Component} from 'react'
import './style.css'

export default class Cell extends Component {
    constructor(props) {
        super(props)

        this.state = {
            x: props.x,
            y:props.y,
            isShip: props.typeCell,
            isShot: props.shot
        }
    }

    sendCoords = async () => {
        return await fetch('', {
            method: 'POST',
            headers: 'application/json;charset=utf-8',
             body: JSON.stringify({
                 x: this.state.x,
                 y: this.state.y
             })
        })


    }

    render() {
        return (
            <button onClick={this.sendCoords}>
                <div className="cell"></div>
            </button>
        )
    }
}