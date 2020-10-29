import React, {Component} from 'react'
import './style.css'
import Cell from './Cell'

export default class Column extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <li><Cell /></li>
        )
    }
}