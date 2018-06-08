const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const entry = {
  'counter-sample': [
    'counter-sample'
  ]
};

const output = {
  path: path.resolve('./dist'),
  filename: 'scripts/[name].js',
  chunkFilename: 'scripts/[name].js',
  publicPath: '/'
};

const rules = [
  {
    test: /\.js$/,
    exclude: /node_modules|bower_components/,
    use: [
      'source-map-loader'
    ],
    enforce: 'pre'
  }
];

const resolve = {
  modules: [
    path.resolve('./build/kotlin-js-min/main'),
    'node_modules',
  ]
};

const plugins = [
  new HtmlWebpackPlugin()
];

module.exports = {
  entry,
  output,
  module: {
    rules
  },
  resolve,
  plugins
};
