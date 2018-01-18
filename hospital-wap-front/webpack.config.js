var pkg = require("./package.json");
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require("extract-text-webpack-plugin");
// var ChunkManifestPlugin = require('chunk-manifest-webpack-plugin');
var OfflinePlugin = require('offline-plugin');
var ImageminPlugin = require('imagemin-webpack-plugin').default;
var BellOnBundlerErrorPlugin = require('bell-on-bundler-error-plugin');
var BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;

var path = require('path');
//
// parameters
var env = "DEV";
var outputPath = "build";


var base = __dirname;

var appName = 'app';





var vendorCssPlugin = new ExtractTextPlugin({
    filename: '[name].css'
});
var appCssPlugin = new ExtractTextPlugin({
    filename: '[name].scss.css'
});

const entry = {
    "index": [
        "./src/app/index.js"
    ],
    "commons": [
        'babel-polyfill',
        // "jquery",
        "animate.css/animate.css",
        "swiper/dist/css/swiper.css",
        "swiper",

        "angular",
        "angular-material",
        "angular-material/angular-material.css",
        "angular-ui-router",
        "angular-swiper",

        "ui-router-extras",

        'weui',
        'store'
    ]
};
Object.assign(entry);

const plugins = [
    // new webpack.ProvidePlugin({
    //     $: 'jquery',
    //     jQuery: 'jquery',
    //     'window.jQuery': 'jquery'
    // }),
    new BundleAnalyzerPlugin({
        analyzerMode: 'static',
        reportFilename: 'report.html',
        openAnalyzer: false,
        generateStatsFile: true,
        statsFilename: 'report.json'
    }),
    new BellOnBundlerErrorPlugin(),
    // new webpack.BannerPlugin("copyright @ kingsilk.net"),
    // new ChunkManifestPlugin({
    //     filename: "manifest.json",
    //     manifestVariable: "webpackManifest"
    // }),
    new webpack.optimize.CommonsChunkPlugin({
        name: "commons",
        minChunks: 2
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

// if (isProd) {
//     plugins.push(
//         new webpack.optimize.UglifyJsPlugin({
//             compress: {
//                 warnings: false
//             },
//             mangle: {
//                 except: ['$super', '$', 'exports', 'require']
//             },
//             output: {
//                 comments: false
//             },
//             sourceMap: true
//         }),
//         new webpack.optimize.OccurrenceOrderPlugin()
//     );
// }

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
                test: /src\/app\/states\/.*\.css$/,
                use: [
                    "style-loader",
                    {
                        loader: 'css-loader',
                        options: {
                            minimize: true,
                            sourcemap: true,
                            discardComments: {
                                removeAll: true
                            },
                            calc: false
                        }
                    }
                ],
            },
            {
                test: /src\/app\/.*\.scss$/,
                use: [
                    "style-loader",
                    {
                        loader: 'css-loader',
                        options: {
                            minimize: true,
                            sourcemap: true,
                            discardComments: {
                                removeAll: true
                            },
                            calc: false
                        }
                    },
                    "sass-loader"
                ],
            },
            // { test: /\.scss$/, loader: "style-loader!css-loader!sass-loader" },
            {
                test: /(node_modules|src\/app\/ag-iconfont).*\.css$/,
                loader: vendorCssPlugin.extract({
                    fallback: 'style-loader',
                    use: {
                        loader: 'css-loader',
                        // XXX : 需要关注 https://github.com/webpack/css-loader/pull/400
                        options: {
                            minimize: true,
                            sourceMap: true,
                            discardComments: {
                                removeAll: true
                            },
                            calc: false
                        }

                    }
                })
            },
            {
                test: /(node_modules).*\.scss$/,
                loader: appCssPlugin.extract(
                    {
                        fallback: 'style-loader',
                        use: [
                            {
                                loader: 'css-loader',
                                // XXX : 需要关注 https://github.com/webpack/css-loader/pull/400
                                options: {
                                    minimize: true,
                                    sourceMap: true,
                                    discardComments: {
                                        removeAll: true
                                    },
                                    calc: false
                                }
                            },
                            {
                                loader: 'sass-loader',
                                options: {
                                    sourceMap: true
                                }
                            }
                        ]
                    })
            },
            {
                test: /\.(woff|woff2|ttf|eot|svg)(\?]?.*)?$/,
                use: [
                    {
                        loader: "file-loader",
                        options: {
                            name: "assets/fonts/[name]-[hash].[ext]",
                            publicPath: './'　//重新指定访问路径　　参考　https://github.com/webpack-contrib/file-loader
                        }
                    }
                ]
            },

            /*
             * 这种打包字体的方式会直接以hash的方式重命名
             * */
            // {
            //     test: /\.(woff|woff2|ttf|eot|svg)(\?]?.*)?$/,
            //     loader: 'file-loader?publicPath=./&outputPath=assets/fonts/'
            // },
            /*
             * 下面的方式可用
             * */
            // {
            //     test: /\.(woff|woff2|ttf|eot|svg)(\?]?.*)?$/,
            //     loader: 'file-loader',
            //     options: {
            //         name: "assets/fonts/[name]-[hash].[ext]",
            //         publicPath: './'
            //     }
            // },
            {
                test: /\.(jpg|jepg|png|gif)(\?]?.*)?$/,
                use: [
                    {
                        loader: "file-loader",
                        options: {
                            name: "assets/imgs/[name]-[hash].[ext]"
                        }
                    }
                ]
            }
        ]
    },
    plugins: plugins,
    devtool: "#source-map",
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
        port: 8099,
        inline: true,
        hot: true,
        open: true,
        clientLogLevel: "info",
        compress: false,
        quiet: false
    },
};

module.exports = config;
