const { createProxyMiddleware } = require('http-proxy-middleware');

// proxies API calls from port 3000 to port 8080 where server is running
// prevents errors with CORS and makes API calls easier
module.exports = function(app) {
    // proxies http requests
    app.use(createProxyMiddleware('/api/gms', { target: 'http://localhost:8080/' }));
    // proxies websocket requests
    app.use(createProxyMiddleware('/api/gms/ws-connect', { ws: true, target: 'http://localhost:8080/', changeOrigin: true }));
};