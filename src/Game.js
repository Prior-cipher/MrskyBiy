import React, {Component} from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import Grid from "./components/Grid";
import ships from "./coordsShips"
import arr_rows from './gridCells'

export default class Game extends Component {
    constructor(props) {
        super(props)

        this.state = {
            playerShips: ships.player,
            enemyShips: ships.enemy,
            gameOver: false,
            grid: [...arr_rows]
        }
    }

    drawShips = () => {
        this.setState(state => state.grid[0][0].isShip = true)
    }

    render() {
        return(
            <div className="container">
                <h1>Морской бой</h1>
                <Grid
                    whose="player"
                    ships={this.state.playerShips}
                    rows={this.state.grid}
                />
                <button onClick={this.drawShips}>Сгенерировать</button>
                <Grid
                    whose="enemy"
                    ships={this.state.enemyShips}
                    rows={this.state.grid}
                />
            </div>
        )
    }
}