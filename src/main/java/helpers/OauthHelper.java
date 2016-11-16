package helpers;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

/**
 * Singleton para facilitar a utiliza��od da biblioteca Apache Oltu.
 */
public class OauthHelper {

	/**
	 * Helper para obten��o dos token e execu��o de consultas.
	 */
	private static OauthHelper oauthHelper;

	private OAuthClientRequest oAuthClientRequest;

	private OAuthClient oAuthClient;
	
	/**
	 * Mant�m o access token requisitado.
	 */
	private static String accessToken;

	private OauthHelper() {
		oAuthClient = new OAuthClient(new URLConnectionClient());
	}

	/**
	 * M�todo para inst�ncia do helper;
	 * 
	 * @return
	 */
	public static OauthHelper getInstance() {
		if (oauthHelper == null)
			oauthHelper = new OauthHelper();
		return oauthHelper;
	}

	/**
	 * M�todo para obten��o do token de autoriza��o de apps.
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
			accessToken = oAuthClient.accessToken(oAuthClientRequest, OAuth.HttpMethod.POST).getAccessToken();
			return accessToken;
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		} catch (OAuthProblemException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Mpetodo para obten��o do token de autoriza��o e autentica��o.
	 * 
	 * @param tokenLocation
	 * @param clientId
	 * @param clientSecret
	 * @param user
	 * @param password
	 * @return
	 */
	public String requestTokenClient(String tokenLocation, String clientId, String clientSecret, String user,
			String password) {
		try {
			oAuthClientRequest = OAuthClientRequest.tokenLocation(tokenLocation).setGrantType(GrantType.AUTHORIZATION_CODE)
					.setClientId(clientId).setClientSecret(clientSecret).setUsername(user).setPassword(password)
					.buildQueryMessage();
			accessToken = oAuthClient.accessToken(oAuthClientRequest, OAuth.HttpMethod.POST).getAccessToken();
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
	private String requestResource(String resourceUrl, String requestMethod) {

		try {
			OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(resourceUrl)
					.setAccessToken(getAccessToken()).buildQueryMessage();
			
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
	public String requestResourceGet(String resourceUrl) {
		return requestResource(resourceUrl, OAuth.HttpMethod.GET);
	}
	
	/**
	 * M�todo para requisitar um servi�o por Post.
	 * @param resourceUrl
	 * @return
	 */
	public String requestResourcePost(String resourceUrl) {
		return requestResource(resourceUrl, OAuth.HttpMethod.POST);
	}

	public static String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public static void setAccessToken(String accessToken) {
		OauthHelper.accessToken = accessToken;
	}
	

}
