// Vue.config.js 配置选项

module.exports = {
    // 基本路径
    publicPath: process.env.NODE_ENV === 'production' ? '/my-app/' : '/',
    outputDir: 'dist',
    assetsDir: 'assets',
    devServer: {
        port: 8090,
        host: '0.0.0.0',
        open: true,
        proxy:{
            "/api":{
                target:'http://47.98.58.79:8080',
                secure:false,
                changeOrigin:true,
                pathRewrite:{
                    '^/api':''
                },
            }
        },
        disableHostCheck:true
    },
}
