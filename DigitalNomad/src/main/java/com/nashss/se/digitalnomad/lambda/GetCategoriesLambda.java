package com.nashss.se.digitalnomad.lambda;

import com.nashss.se.digitalnomad.activity.requests.GetCategoriesRequest;
import com.nashss.se.digitalnomad.activity.results.GetCategoriesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetCategoriesLambda
        extends LambdaActivityRunner<GetCategoriesRequest, GetCategoriesResult>
        implements RequestHandler<LambdaRequest<GetCategoriesRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetCategoriesRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(() -> input.fromPath(path ->
                GetCategoriesRequest.builder()
                        .build()), (request, serviceComponent) ->
                        serviceComponent.provideGetCategoriesActivity().handleRequest()
        );
    }
}
