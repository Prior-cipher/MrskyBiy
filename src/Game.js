import React, {Component} from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import Grid from "./components/Grid";


export default class Game extends Component {

  constructor(props) {
    super(props);

    this.state = {
        playersField: props.playersField,
        // enemyShips: [...props.enemyShips],
        // playerShips: [...props.playerShips],
        isEnemyGrid: props.isEnemyGrid,
        gameOver: false,
        areEnemyShipsInvisible: true
    }
  }

  fillingGrid = () => {
      // if(this.isEnemyGrid) {
      //     this.drawShips(
      //         this.state.playersField,
      //         this.enemyShips
      //     )
      // } else {
      //     this.drawShips(
      //         this.state.playersField,
      //         this.playerShips
      //     )
      // }
  }

  drawShips = (grid, locationShips) => {
      grid.forEach(cell => {
          // cell
      })
  }

  render() {
      return (
          <div className="container">
              <h1>Морской Бой</h1>

              <div className="grid">
                  <Grid
                      playersField={this.state.playersField}
                  />
                  {/*<button onClick={this.drawShips}>*/}
                  {/*    Сгенерировать*/}
                  {/*</button>*/}
                  {/*<Grid />*/}
              </div>
          </div>
        )
  }

}