import React from 'react';
import {render} from 'react-dom';
import Game from './Game';
import grid from './gridCells'


render(
    <Game
        playersField={grid}
    />,
    document.getElementById('root')
);

