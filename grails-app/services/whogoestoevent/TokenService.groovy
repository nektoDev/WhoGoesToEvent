package whogoestoevent

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.springframework.web.context.request.RequestContextHolder
import ru.nektodev.whogoestoevent.exception.NeedAuthorizationException

class TokenService implements Serializable {
    static scope = "session"

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
            uri.query = [client_id: '3973980', client_secret: 'EAyHomAjJrkNHc5XOj5E', redirect_uri: 'http://localhost:8080/WhoGoesToEvent/authentication/success', code: code]
            response.success = { resp, json ->
                accessToken = json.access_token

            }
            response.failure = { resp, json ->
                //TODO
            }

        }
    }


}
