import React, {Component} from 'react'
import './style.css'
import Cell from './Cell'

export default class Row extends Component {
    constructor(props) {
        super(props)

        this.state = {
            cells: props.cells,
            whose: props.whose
        }
    }

    render() {
        return (
            <ul className="row">
                {this.state.cells.map(cell =>
                    <Cell
                        x={cell.x}
                        y={cell.y}
                        isShip={cell.containsShip}
                        whose={this.state.whose}
                        key={`${cell.x}${cell.y}`}
                    />
                )}
            </ul>
        )
    }
}