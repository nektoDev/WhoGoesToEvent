package whogoestoevent

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.springframework.web.context.request.RequestContextHolder
import ru.nektodev.whogoestoevent.exception.NeedAuthorizationException

class TokenService implements Serializable {
    static scope = "session"

    def grailsApplication

    private String accessToken;
    def session
    HTTPBuilder http;

    public TokenService() {
        http = new HTTPBuilder("https://oauth.vk.com/");
        http.handler.failure = { resp ->
            "Unexpected failure: ${resp.statusLine}"
            //TODO
        }

        session = RequestContextHolder.currentRequestAttributes().getSession();
    }

    public String getVkAccessToken() throws NeedAuthorizationException {
        if (accessToken == null) {
            accessToken = ""
        }
        return accessToken
    }

    public def requestToken(String code) {

        http.request(Method.GET) { req ->
            uri.path = 'access_token'
            def vkontakte = grailsApplication.config.oauth.providers.vkontakte
            uri.query = [client_id: vkontakte.key, client_secret: vkontakte.secret, redirect_uri: vkontakte.callback, code: code]
            response.success = { resp, json ->
                accessToken = json.access_token

            }
            response.failure = { resp, json ->
                //TODO
            }

        }
    }

    public boolean isToken() {
        return !(accessToken == null || accessToken.isEmpty())
    }

}
