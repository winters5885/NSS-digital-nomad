package com.nashss.se.digitalnomad.lambda;

import com.nashss.se.digitalnomad.activity.requests.GetFavoritesRequest;
import com.nashss.se.digitalnomad.activity.results.GetFavoritesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetFavoritesLambda extends LambdaActivityRunner<GetFavoritesRequest, GetFavoritesResult>
        implements RequestHandler<LambdaRequest<GetFavoritesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetFavoritesRequest> input, Context context) {
        return super.runActivity(() -> input.fromPathAndQuery((path, query) ->
                GetFavoritesRequest.builder()
                        .withUserId(path.get("userId"))
                        .build()), (request, serviceComponent) ->
                serviceComponent.provideGetFavoritesActivity().handleRequest(request)
        );
    }
}
