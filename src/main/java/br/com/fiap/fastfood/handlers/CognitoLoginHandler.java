package br.com.fiap.fastfood.handlers;

import java.util.HashMap;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.InitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.InitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.fiap.fastfood.models.dto.LoginParamsDto;
import br.com.fiap.fastfood.models.dto.LoginResponseDto;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CognitoLoginHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final String CLIENT_ID = System.getenv("CLIENT_ID");

	private static final ObjectMapper objectMapper = new ObjectMapper();

	//private static final String CLIENT_ID = "1ouemvqkchdc5q70cl3vdmold";

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

		try {
			String body = event.getBody();

			LoginParamsDto loginParams = objectMapper.readValue(body, LoginParamsDto.class);


			HashMap<String, String> params = new HashMap<String, String>();
			params.put("USERNAME", loginParams.getUsername());
			params.put("PASSWORD", loginParams.getPassword());

			AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.defaultClient();

			InitiateAuthRequest authRequest = new InitiateAuthRequest()
					.withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
					.withClientId(CLIENT_ID)
					.withAuthParameters(params);

			InitiateAuthResult authResult = cognitoClient.initiateAuth(authRequest);


			LoginResponseDto responseDto = new LoginResponseDto(
					200,
					authResult.getAuthenticationResult().getIdToken(),
					authResult.getAuthenticationResult().getAccessToken(),
					authResult.getAuthenticationResult().getRefreshToken(),
					"Login successful"
					);

			String responseBody = objectMapper.writeValueAsString(responseDto);
			response.setStatusCode(200);
			response.setBody(responseBody);
			return response;
		} catch (NotAuthorizedException e) {
			response.setStatusCode(401);
			response.setBody("Unauthorized");
			return response;
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setBody("Internal Server Error: " + e.getMessage());
			return response;
		}

	}

}
