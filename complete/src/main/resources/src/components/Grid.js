import React, {Component} from 'react'
import Row from "./Row";
import './style.css'


export default class Grid extends Component {
    constructor(props) {
        super(props)

        this.state = {
            rows: props.rows,
            whose: props.whose,
            ships: props.ship
        }
    }



    render() {
        return(
            <div className="grid">
                {this.state.rows.map((row, index) =>
                    <Row
                        whose={this.state.whose}
                        cells={row}
                        key={index}
                    />
                )}
            </div>
        )
    }
}