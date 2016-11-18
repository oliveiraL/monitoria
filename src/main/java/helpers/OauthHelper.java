package helpers;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import dominio.Usuario;

/**
 */
public class OauthHelper {

	/**
	 */
	private static OauthHelper oauthHelper;

	private OAuthClientRequest oAuthClientRequest;

	private OAuthClient oAuthClient;
	
	private OauthHelper() {
		oAuthClient = new OAuthClient(new URLConnectionClient());
	}

	/**
	 * 
	 * @return
	 */
	public static OauthHelper getInstance() {
		if (oauthHelper == null)
			oauthHelper = new OauthHelper();
		return oauthHelper;
	}

	/**
	 * 
	 * @param tokenLocation
	 * @param clientId
	 * @param clientSecret
	 * @return
	 */
	public String requestTokenApp(String tokenLocation, String clientId, String clientSecret) {

		try {
			oAuthClientRequest = OAuthClientRequest.tokenLocation(tokenLocation)
					.setGrantType(GrantType.CLIENT_CREDENTIALS).setClientId(clientId).setClientSecret(clientSecret)
					.buildQueryMessage();
			String accessToken = oAuthClient.accessToken(oAuthClientRequest, OAuth.HttpMethod.POST).getAccessToken();
			return accessToken;
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		} catch (OAuthProblemException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	/**
	 * M�todo para requisitar um servi�o.
	 * @param resourceUrl
	 * @param requestMethod
	 * @return
	 */
	private String requestResource(String resourceUrl, String requestMethod, Usuario usuario) {

		try {
			OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(resourceUrl)
					.setAccessToken(usuario.getAccessToken()).buildQueryMessage();
			
			return oAuthClient.resource(bearerClientRequest, requestMethod, OAuthResourceResponse.class).getBody();
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		} catch (OAuthProblemException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	/**
	 * M�todo para requisitar um servi�o por Get.
	 * @param resourceUrl
	 * @return 
	 */
	public String requestResourceGet(String resourceUrl, Usuario usuario) {
		return requestResource(resourceUrl, OAuth.HttpMethod.GET,usuario);
	}
	
	/**
	 * M�todo para requisitar um servi�o por Post.
	 * @param resourceUrl
	 * @return
	 */
	public String requestResourcePost(String resourceUrl, Usuario usuario) {
		return requestResource(resourceUrl, OAuth.HttpMethod.POST,usuario);
	}
	

}
