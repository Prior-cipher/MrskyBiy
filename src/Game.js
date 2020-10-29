import React, {Component} from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import Grid from "./components/Grid";


export default class Game extends Component {

  constructor(props) {
    super(props);

    this.state = {
        playersField: [],
        enemyShips: [...props.enemyShips],
        playerShips: [...props.playerShips],
        isEnemyGrid: props.isEnemyGrid,
        gameOver: false,
        areEnemyShipsInvisible: true
    }
  }

  fillingGrid = () => {
      for (let i = 0; i < 10; i++) {
          this.state.playersField.push([])

          for (let j = 0; j < 10; j++) {
              this.state.playersField[i].push({
                  x: j,
                  y: i,
                  containsShip: false,
                  shot: false,
                  isShipVisible: false
              });
          }
      }

      if(this.isEnemyGrid) {
          this.drawShips(
              this.state.playersField,
              this.enemyShips
          )
      } else {
          this.drawShips(
              this.state.playersField,
              this.playerShips
          )
      }

  }

  drawShips = (grid, locationShips) => {

  }

  render() {
    return (
      <div className="container">
          <h1>Морской Бой</h1>

          <div className="row">
              <Grid />
          </div>

      </div>
    )
  }

}