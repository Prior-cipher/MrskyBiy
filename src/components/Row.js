import React, {Component} from 'react'
import './style.css'
import Cell from './Cell'

export default class Row extends Component {
    constructor(props) {
        super(props);

        this.state = {
            rowCells: props.rowCells,
            handleClick: props.handleClick
        }
    }


    render() {
        return (
            <ul className="row">
                {this.state.rowCells.map((cell, index) => {
                    return (<Cell
                        x={cell.x}
                        y={cell.y}
                        isShip={cell.containsShip}
                        wasShot={cell.shot}
                        isShipVisible={cell.isShipVisible}
                        key={index}
                        onClick={
                            () => this.state.handleClick(
                                cell.x, cell.y
                            )
                        }
                    />)
                })}
            </ul>
        )
    }
}