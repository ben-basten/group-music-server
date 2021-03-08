const { createProxyMiddleware } = require('http-proxy-middleware');

// proxies API calls from port 3000 to port 8080 where server is running
// prevents errors with CORS and makes API calls easier
module.exports = function(app) {
    app.use(createProxyMiddleware('/api/gms', { target: 'http://localhost:8080/' }));
};