import React, {Component} from 'react'
import './style.css'

export default class Cell extends Component {
    constructor(props) {
        super(props)

        this.classes = {

            isShip: props.typeCell,
            isShot: props.shot
        }







        this.marker = 'X'
        this.marker = '·'





    }

    sendCoords = () => {


        return {x, y}
    }

    render() {
        return (
            <button onClick={this.sendCoords}>
                <div className="marker cell">{this.marker}</div>
            </button>
        )
    }
}