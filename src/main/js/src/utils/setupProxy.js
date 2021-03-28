const { createProxyMiddleware } = require('http-proxy-middleware');

// proxies API calls from port 3000 to port 8080 where server is running
// prevents errors with CORS and allows use of relative URLs for API calls
module.exports = function(app) {
    app.use(createProxyMiddleware('/api/gms', { target: 'http://localhost:8080/' }));
};