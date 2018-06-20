const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;

const production = process.argv.indexOf("-p") >= 0;

const entry = {
  'counter-sample': [
    './index.js'
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
  ],
  alias: !production ? {} : {
    "mobx-react-devtools": path.resolve("./no-devtool.js")
  }
};

const plugins = [
  // new BundleAnalyzerPlugin(),
  new HtmlWebpackPlugin({
    filename: "index.html",
    template: "index.hbs"
  })
];

module.exports = {
  mode: production ? "production" : "development",
  entry,
  output,
  module: {
    rules
  },
  resolve,
  plugins
};
