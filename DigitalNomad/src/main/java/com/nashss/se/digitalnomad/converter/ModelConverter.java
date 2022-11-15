package com.nashss.se.digitalnomad.converter;

import com.nashss.se.digitalnomad.dynamoDb.models.Category;
import com.nashss.se.digitalnomad.models.CategoryModel;

import java.util.List;

/**
 * Converts between Data and Coral models.
 */
public class ModelConverter {
    /**
     * Converts a provided  into a representation.
     *
     * @param category the playlist to convert
     * @return the converted playlist
     */
    public CategoryModel toPlaylistModel(Category category) {
        List<String> tags = null;
//        if (category.getTags() != null) {
//            tags = new ArrayList<>(category.getTags());
//        }

//        return CategoryModel.builder()
//            .withId(playlist.getId())
//            .withName(playlist.getName())
//            .withCustomerId(playlist.getCustomerId())
//            .withSongCount(playlist.getSongCount())
//            .withTags(tags)
//            .build();
//    }

        /**
         * Converts a provided AlbumTrack into a SongModel representation.
         * @param albumTrack the AlbumTrack to convert to SongModel
         * @return the converted SongModel with fields mapped from albumTrack
         */
    public CategoryModel toSongModel(AlbumTrack albumTrack) {
        return SongModel.builder()
            .withAsin(albumTrack.getAsin())
            .withTrackNumber(albumTrack.getTrackNumber())
            .withAlbum(albumTrack.getAlbumName())
            .withTitle(albumTrack.getSongTitle())
            .build();

    }

    /**
     * Converts a list of AlbumTracks to a list of SongModels.
     * @param albumTracks The AlbumTracks to convert to SongModels
     * @return The converted list of SongModels
     */
    public List<SongModel> toSongModelList(List<AlbumTrack> albumTracks) {
        List<SongModel> songModels = new ArrayList<>();
        for (AlbumTrack albumTrack : albumTracks) {
            songModels.add(toSongModel(albumTrack));
        }

        return null;
    }
    }
