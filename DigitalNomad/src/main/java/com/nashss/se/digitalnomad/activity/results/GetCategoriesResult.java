package com.nashss.se.digitalnomad.activity.results;

import com.nashss.se.musicplaylistservice.models.PlaylistModel;

public class GetCategoriesResult {
    private final PlaylistModel playlist;

    private GetCategoriesResult(PlaylistModel playlist) {
        this.playlist = playlist;
    }

    public PlaylistModel getPlaylist() {
        return playlist;
    }

    @Override
    public String toString() {
        return "GetPlaylistResult{" +
                "playlist=" + playlist +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PlaylistModel playlist;

        public Builder withPlaylist(PlaylistModel playlist) {
            this.playlist = playlist;
            return this;
        }

        public GetCategoriesResult build() {
            return new GetCategoriesResult(playlist);
        }
    }
}
