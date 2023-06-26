const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    '/api/v1',
    createProxyMiddleware({
      target:
        'http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080',
      changeOrigin: true,
    }),
  );
};
