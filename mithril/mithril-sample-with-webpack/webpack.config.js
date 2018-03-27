const path = require('path');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    resolve: {
        modules: [
            path.resolve('./build/kotlin-js-min/main'),
            'node_modules'
        ]
    },

    entry: {
        'mithril-sample-with-webpack': [
            './build/resources/main/style.css',
            'mithril-sample-with-webpack.js'
        ]
    },

    output: {
        filename: '[name].[hash].js',
        chunkFilename: '[name].[hash].js',
        path: path.resolve('./dist/')
    },

    module: {
        rules: [
            {test: /\.css$/, loaders: ['style-loader', 'css-loader']}
        ]
    },

    plugins: [
        new CleanWebpackPlugin([
            path.resolve('./dist')
        ]),
        new HtmlWebpackPlugin()
    ],

    devServer: {
        port: 5000
    }
};
