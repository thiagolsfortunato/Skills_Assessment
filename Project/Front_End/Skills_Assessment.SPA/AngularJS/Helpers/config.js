var config = {
    apiurl: "http://127.0.0.1:4567/map/",			//teste-inacio.rhcloud.com
   //weburl: "http://localhost:16178/",

    generateApiUrl: function (serviceUrl) {
        return config.apiurl + serviceUrl;
    }
}