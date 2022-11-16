package com.nashss.se.digitalnomad.converter;

/**
 * Converts between Data and Coral models.
 */
public class ModelConverter {
    /**
     * Converts a provided category into a representation.
     *
     * @param category the category to convert
     * @return the converted category
     */
    /*
    public CategoryModel toCategoryModel(Category category) {
        String categoryType = null;

        if (category.getCategory() != null) {
            categoryType = category.getCategory();
        }

        return CategoryModel.builder()
            .withCategory(categoryType)
            .build();
    }
     */
    /**
         * Converts a provided AlbumTrack into a SongModel representation.
         * @param albumTrack the AlbumTrack to convert to SongModel
         * @return the converted SongModel with fields mapped from albumTrack
         */

    /**
    public CategoryModel toSongModel(AlbumTrack albumTrack) {
        return SongModel.builder()
            .withAsin(albumTrack.getAsin())
            .withTrackNumber(albumTrack.getTrackNumber())
            .withAlbum(albumTrack.getAlbumName())
            .withTitle(albumTrack.getSongTitle())
            .build();

    }
     */

    /**
     * Converts a list of AlbumTracks to a list of SongModels.
     * @param albumTracks The AlbumTracks to convert to SongModels
     * @return The converted list of SongModels
    */

    /**
    public List<SongModel> toSongModelList(List<AlbumTrack> albumTracks) {
        List<SongModel> songModels = new ArrayList<>();
        for (AlbumTrack albumTrack : albumTracks) {
            songModels.add(toSongModel(albumTrack));
        }

        return null;
    }
     */

}
