function init() {
    var env = karate.env;
    karate.log('karate.env selected environment was:', env);
    if (!env) {
        env = 'local';
    }
    var config = {
        env: env,
        clientId: 'karate-quarkus-demo',
        clientSecret: '19f745ce-5452-467f-bad8-ee14184240e5',
        authUrl: 'http://localhost:8180/auth/realms/karate-quarkus-demo-realm/protocol/openid-connect/token',
        apiBaseUrl: 'http://localhost:8080/api'
    };
    if (env == 'dev') {
        config.clientId= 'karate-quarkus-demo',
        config.clientSecret= '29f745af-5452-487f-bad8-ee14354141a9',
        config.authUrl= 'http://192.168.1.17:8180/auth/realms/karate-quarkus-demo-realm/protocol/openid-connect/token',
        config.apiBaseUrl= 'http://192.168.1.17:8080/api'
    } else if (env == 'qa') {
        //...
    }
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);
    return config;
}
