global.Promise = require('bluebird');

var webpack = require('webpack');
var path = require('path');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var CleanWebpackPlugin = require('clean-webpack-plugin');

var cssName = process.env.NODE_ENV === 'production' ? 'styles-[hash].css' : 'styles.css';
var jsName = process.env.NODE_ENV === 'production' ? 'bundle-[hash].js' : 'bundle.js';

var plugins = [
    new webpack.DefinePlugin({
        'process.env': {
            BROWSER: JSON.stringify(true),
            NODE_ENV: JSON.stringify(process.env.NODE_ENV || 'development')
        }
    }),
    new ExtractTextPlugin(cssName)
];

if (process.env.NODE_ENV === 'production') {
    plugins.push(
        new CleanWebpackPlugin(['public/assets/'], {
            root: __dirname,
            verbose: true,
            dry: false
        })
    );
    plugins.push(new webpack.optimize.DedupePlugin());
    plugins.push(new webpack.optimize.OccurenceOrderPlugin());
}

module.exports = {
    entry: {
        app: ['babel-polyfill', './src/index.js']
    },
    debug: process.env.NODE_ENV !== 'production',
    resolve: {
        root: path.join(__dirname, 'src'),
        modulesDirectories: ['node_modules'],
        extensions: ['', '.js', '.jsx', '.json']
    },
    plugins,
    output: {
        path: path.resolve(__dirname, "build"),
        filename: jsName
    },
    module: {
        loaders: [
            {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract('style-loader', 'css-loader!postcss-loader')
            },
            {
                test: /\.scss$/,
                loader: ExtractTextPlugin.extract('style-loader', 'css-loader!resolve-url!sass-loader?sourceMap')
            },
            {test: /\.gif$/, loader: 'url-loader?limit=10000&mimetype=image/gif'},
            {test: /\.jpg$/, loader: 'url-loader?limit=10000&mimetype=image/jpg'},
            {test: /\.png$/, loader: 'url-loader?limit=10000&mimetype=image/png'},
            {test: /\.svg/, loader: 'url-loader?limit=26000&mimetype=image/svg+xml'},
            {test: /\.(woff|woff2|ttf|eot)/, loader: 'url-loader?limit=1'},
            {
                test: /\.jsx?$/,
                loader: process.env.NODE_ENV !== 'production' ? 'react-hot!babel' : 'babel',
                exclude: [/node_modules/, /public/]
            },
            {test: /\.json$/, loader: 'json-loader'},
            {
                test: /\.html$/,
                loader: "file?name=[name].[ext]",
            }
        ]
    },
    devtool: process.env.NODE_ENV !== 'production' ? 'source-map' : null,
    devServer: {
        contentBase: "./build",
        hot: true,
        historyApiFallback: true,
        headers: {'Access-Control-Allow-Origin': '*'/*, 'Access-Control-Expose-Headers': 'token'*/},
        proxy: [{
            context: ['/rest/**'],
            target: 'http://localhost:4545',
            changeOrigin: true
        }]
    }
};
