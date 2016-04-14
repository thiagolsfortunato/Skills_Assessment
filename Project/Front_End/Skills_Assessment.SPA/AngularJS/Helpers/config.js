var config = {
    apiurl: "http://172.16.1.132:4567/"/*"http://172.16.24.72:4567/"*/,
    weburl: "http://localhost:16178/",

    generateApiUrl: function (serviceUrl) {
        return config.apiurl + serviceUrl;
    }
}