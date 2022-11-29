package com.nashss.se.digitalnomad.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.digitalnomad.activity.requests.SaveFavoritesRequest;
import com.nashss.se.digitalnomad.activity.results.SaveFavoritesResult;

public class SaveFavoritesLambda extends LambdaActivityRunner<SaveFavoritesRequest, SaveFavoritesResult>
        implements RequestHandler<LambdaRequest<SaveFavoritesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<SaveFavoritesRequest> input, Context context) {
        return super.runActivity(() -> input.fromBody((path, query) ->
                SaveFavoritesRequest.builder()
                                .withUserId(path.get("userId"))
                                .withFavoriteDestinations(path.get(favoriteDestinations))
                                .build()), (request, serviceComponent) ->
                serviceComponent.provideSaveFavoritesActivity().handleRequest(request)
        );
    }
}
//create playlist

