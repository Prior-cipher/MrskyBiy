var packageJSON = require('./package.json');
var path = require('path');
var webpack = require('webpack');
module.exports = {
    entry: './index.js',
    output: {
        path: path.join(__dirname, 'generated'),
        filename: 'app-bundle.js'},
    resolve: {extensions: ['.js', '.jsx']},
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader',
                exclude: /node_modules/
            }
        ]
    }
}