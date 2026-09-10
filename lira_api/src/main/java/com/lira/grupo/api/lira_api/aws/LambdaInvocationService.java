package com.lira.grupo.api.lira_api.aws;


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


            SdkBytes payload = SdkBytes.fromUtf8String(jsonPayload);


            InvokeRequest request = InvokeRequest.builder()
                    .functionName(functionName)
                    .payload(payload)
                    .build();


            InvokeResponse response = lambdaClient.invoke(request);
            return response.payload().asUtf8String();

        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke Lambda function", e);
        }
    }
}


