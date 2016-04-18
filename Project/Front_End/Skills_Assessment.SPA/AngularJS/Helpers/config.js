var config = {
    apiurl: "http://127.0.0.1:4567/",
    weburl: "http://localhost:16178/",

    generateApiUrl: function (serviceUrl) {
        return config.apiurl + serviceUrl;
    }
}