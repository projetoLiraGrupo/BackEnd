package com.lira.grupo.api.lira_api.aws;

import com.lira.grupo.api.lira_api.exception.*;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

@Service
public class LambdaInvocationService {

    public String callExternalLambda(String functionName, String jsonPayload) {
        try (LambdaClient lambdaClient = LambdaClient.builder()
                .region(Region.US_EAST_1)
                .build()) {

            InvokeRequest request = InvokeRequest.builder()
                    .functionName(functionName)
                    .payload(SdkBytes.fromUtf8String(jsonPayload))
                    .build();

            InvokeResponse response = lambdaClient.invoke(request);
            return response.payload().asUtf8String();
        } catch (RuntimeException exception) {
            throw new ExternalServiceException("Falha ao invocar a função Lambda.", exception);
        }
    }
}
