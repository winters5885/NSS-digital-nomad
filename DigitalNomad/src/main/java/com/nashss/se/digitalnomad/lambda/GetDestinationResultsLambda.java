package com.nashss.se.digitalnomad.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.digitalnomad.activity.requests.GetDestinationsRequest;
import com.nashss.se.digitalnomad.activity.results.GetDestinationsResult;

public class GetDestinationsLambda extends LambdaActivityRunner<GetDestinationsRequest, GetDestinationsResult>
        implements RequestHandler<LambdaRequest<GetDestinationsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetDestinationsRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPathAndQuery((path, query) ->
                        GetDestinationsRequest.builder()
                                .withCategory(path.get("category"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetDestinationsActivity().handleRequest(request)
        );
    }
}
