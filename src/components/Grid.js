import React, {Component} from 'react'
import Column from "./Column";
import './style.css'

export default class Grid extends Component {
    constructor(props) {
        super(props);

        this.state = {

        }
    }

    render() {
        return (
            <div className="grid">
                <Column />
            </div>
        )
    }
}