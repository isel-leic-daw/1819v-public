const CopyWebpackPlugin = require('copy-webpack-plugin')

module.exports = {
  mode: 'development',
  devServer: {
    contentBase: './dist',
    historyApiFallback: true
  },
  plugins: [
    new CopyWebpackPlugin([{
      from: './*.html'
    }])
  ],
  module: {
    rules: [
      {
        test: /\.(js)$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader'
        }
      }
    ]
  }
}
