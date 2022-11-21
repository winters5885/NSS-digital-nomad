package com.nashss.se.digitalnomad.activity.results;

import com.nashss.se.digitalnomad.models.DestinationModel;

import java.util.ArrayList;
import java.util.List;

public class GetDestinationsResult {
    private final List<DestinationModel> destinationList;

    private GetDestinationsResult(List<DestinationModel> destinationList) {
        this.destinationList = destinationList;
    }

    public List<DestinationModel> getDestinationList() {
        return new ArrayList<>(destinationList);
    }

    @Override
    public String toString() {
        return "GetDestinationsResult{" +
                "destinationList=" + destinationList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<DestinationModel> destinationList;

        public Builder withDestinationList(List<DestinationModel> destinationList) {
            this.destinationList = new ArrayList<>(destinationList);
            return this;
        }

        public GetDestinationsResult build() {
            return new GetDestinationsResult(destinationList);
        }
    }
}
