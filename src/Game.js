import React, {Component} from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import Grid from "./components/Grid";
import coordsShips from "./coordsShips"


export default class Game extends Component {

    constructor(props) {
        super(props);

        this.state = {
            playersField: props.playersField,
            enemyShips: coordsShips.enemy,
            playerShips: coordsShips.player,
            isEnemyGrid: props.isEnemyGrid,
            gameOver: false,
            areEnemyShipsInvisible: true
        }


    }

    handleClick = (x, y) => {
        // return await fetch('', {
        //     method: 'POST',
        //     headers: 'application/json;charset=utf-8',
        //      body: JSON.stringify({
        //          'x': this.state.x,
        //          'y': this.state.y
        //      })
        // })

        console.log({
            'x': x,
            'y': y
        })
    }

    drawShips = (grid, posShips) => {
        for (let i = 0; i < posShips.length; i++) {
            grid[posShips[i]['x'] - 1][posShips[i]['y'] - 1] = posShips[i];
        }
    }

    render() {
        return (
            <div className="container">
                <h1>Морской Бой</h1>

                <Grid
                    className="grid"
                    playersField={this.state.playersField}
                    onClick={(x, y) => this.handleClick(x, y)}

                />
                <button
                    onClick={
                        this.drawShips(
                            this.state.playersField,
                            this.state.enemyShips
                        )
                    }
                >
                    Сгенерировать
                </button>
                <Grid
                    className="grid"
                    playersField={this.state.playersField}
                    onClick={(x, y) => this.handleClick(x, y)}
                />
            </div>
        )
    }
}