var pkg = require("./package.json");
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require("extract-text-webpack-plugin");
// var ChunkManifestPlugin = require('chunk-manifest-webpack-plugin');
var OfflinePlugin = require('offline-plugin');
var ImageminPlugin = require('imagemin-webpack-plugin').default;
var BellOnBundlerErrorPlugin = require('bell-on-bundler-error-plugin');
// var FaviconsWebpackPlugin = require('favicons-webpack-plugin');
var BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;
// var getStateEntries = require("./webpack.getStateEntries");
// var GenFutureStates = require("./webpack.GenFutureStates");

var path = require('path');
// parameters
var env = "DEV";
var outputPath = "build";

var base = __dirname;
var appName = 'app';


var vendorCssPlugin = new ExtractTextPlugin({
    filename:  '[name].css'
});
var appCssPlugin = new ExtractTextPlugin({
    filename: '[name].scss.css'
});

const entry = {
    "index": [
        "./src/app/index.js"
    ],
    "commons": [
        "normalize-css/normalize.css",
        "animate.css/animate.css",
        // "swiper/dist/css/swiper.css",
        "angular",
        "angular-material",
        "angular-material/angular-material.css",
        "angular-ui-router",

        "ui-router-extras",
        'cropper/dist/cropper.css',
        'bootstrap/dist/css/bootstrap.css',
        'bootstrap/dist/css/bootstrap-theme.css',
        'bootstrap-datepicker/dist/css/bootstrap-datepicker3.standalone.css',
        'webuploader/dist/webuploader.css',
        "angular-material-sidenav/angular-material-sidenav.css",
        "angular-material-data-table/dist/md-data-table.css",
        "angular-hotkeys/build/hotkeys.css",
        "angular-ui-tree/dist/angular-ui-tree.css",

    ]
};
Object.assign(entry);

const plugins = [
    new BundleAnalyzerPlugin({
        analyzerMode: 'static',
        reportFilename: 'report.html',
        openAnalyzer: false,
        generateStatsFile: true,
        statsFilename: 'report.json',
    }),
    new BellOnBundlerErrorPlugin(),
    new webpack.optimize.CommonsChunkPlugin({
        name: "commons",
        minChunks: 2
        //filename: isProd ? 'commons.[hash].js' : 'commons.js',
        //minChunks: 3,
        // children: true,
        // async: true,
    }),
    new webpack.DefinePlugin({
        __ENV__: JSON.stringify(env),
        __APP__: JSON.stringify(pkg.name),
        __VERSION__: JSON.stringify(pkg.version)
    }),
    vendorCssPlugin,
    appCssPlugin,



    new HtmlWebpackPlugin({
        template: './src/app/index.html',
        filename: '../index.html',
        chunks: ['commons', 'index'],
        favicon: 'favicon.ico',
        appName: appName,
        hash: true,
        minify: {
            html5: true,
            removeComments: true,
            collapseWhitespace: true,
            preserveLineBreaks: true,
            minifyCSS: true
        },
    }),

    // Make sure that the plugin is after any plugins that add images
    new ImageminPlugin({
        disable: true,
        pngquant: {
            quality: '95-100'
        }
    }),

    new webpack.HotModuleReplacementPlugin()
];

const config = {
    target: "web",
    externals: {
        jquery: 'window.$'
    },
    resolve: {
        modules: [
            'node_modules',
            'bower_components',
        ]
    },
    entry: entry,
    output: {
        path: path.resolve(base, `${outputPath}/${pkg.name}`),
        filename: '[name].js',
        chunkFilename: '[name].chunk.js',
        crossOriginLoading: "anonymous",
        sourceMapFilename: '[file].map',
        publicPath: `${pkg.name}/`
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                use: [
                    {
                        loader: 'babel-loader',
                        options: {
                            cacheDirectory: path.resolve(base, '.tmp')
                        }
                    }
                ],
                exclude: /node_modules/
            },

            {
                test: /\.scss$/,
                use: [{
                    loader: "style-loader"
                }, {
                    loader: "css-loader"
                }, {
                    loader: "sass-Loader",
                    options: {

                    }
                }]
            },{
                test: /\.css$/,
                use: [{
                    loader: "style-loader"
                }, {
                    loader: "css-loader"
                }]
            },



            {
                test: /\.(gif|jpg|png|woff|svg|eot|ttf)\??.*$/,
                use: [{
                    loader: "file-loader",
                    options: {
                        name: "assets/[name]-[hash].[ext]"
                    }
                }]}
        ]
    },
    plugins: plugins,
    devtool: "source-map",
    devServer: {
        contentBase: path.resolve(base, outputPath),
        historyApiFallback: true,
        stats: {
            modules: false,
            cached: false,
            colors: true,
            chunk: false
        },
        host: '127.0.0.1',
        port: 8098,
        inline: true,
        hot: true,
        open: true,
        clientLogLevel: "info",
        compress: false,
        quiet: false
    },
};

module.exports = config;
