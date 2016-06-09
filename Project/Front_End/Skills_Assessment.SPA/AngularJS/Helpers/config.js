var config = {
    apiurl: "http://localhost:4567/",			//teste-inacio.rhcloud.com
   //weburl: "http://localhost:16178/",

    generateApiUrl: function (serviceUrl) {
        return config.apiurl + serviceUrl;
    }
}