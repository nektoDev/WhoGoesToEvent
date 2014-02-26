package ru.nektodev.whogoestoevent
import org.scribe.model.Token
import uk.co.desirableobjects.oauth.scribe.OauthService

class AuthenticationController {
    def grailsApplication;
    OauthService oauthService;

    def index() {
        def url = oauthService.getAuthorizationUrl("vkontakte", new Token("", ""))
        redirect (url: url)
        //TODO if fail
    }

    def success() {
        if (grailsApplication.mainContext.getBean('tokenService').getVkAccessToken().isEmpty() && params.code != null) {
            grailsApplication.mainContext.getBean('tokenService').requestToken(params.code);
            //TODO if fail
        }
        redirect(controller: "search");
    }
}
