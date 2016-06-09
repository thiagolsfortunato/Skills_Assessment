var config = {
    apiurl: "http://localhost:8585/fatec/map/",			//teste-inacio.rhcloud.com
    weburl: "http://localhost:16178/",

    generateApiUrl: function (serviceUrl) {
        return config.apiurl + serviceUrl;
    }
}