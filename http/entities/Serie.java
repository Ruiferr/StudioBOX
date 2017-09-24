package movies.flag.pt.moviesapp.http.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiferrao on 14/09/2017.
 */

public class Serie {

    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("first_air_date")
    @Expose
    private String airDate;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = new ArrayList<>();
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    /**
     *
     * @return
     *     The posterPath
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     *
     * @param posterPath
     *     The poster_path
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    /**
     *
     * @return
     *     The adult
     */
    public Boolean getAdult() {
        return adult;
    }

    /**
     *
     * @param adult
     *     The adult
     */
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    /**
     *
     * @return
     *     The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     *
     * @param overview
     *     The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     *
     * @return
     *     The releaseDate
     */
    public String getAirDate() {
        return airDate;
    }

    /**
     *
     * @param airDate
     *     The release_date
     */
    public void setReleaseDate(String airDate) {
        this.airDate = airDate;
    }

    /**
     *
     * @return
     *     The genreIds
     */
    public List<Integer> getGenreIds() {
        return genreIds;
    }

    /**
     *
     * @param genreIds
     *     The genre_ids
     */
    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    /**
     *
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The originalTitle
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     *
     * @param originalTitle
     *     The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalName = originalName;
    }

    /**
     *
     * @return
     *     The originalLanguage
     */
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     *
     * @param originalLanguage
     *     The original_language
     */
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    /**
     *
     * @return
     *     The title
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.name = title;
    }

    /**
     *
     * @return
     *     The backdropPath
     */
    public String getBackdropPath() {
        return backdropPath;
    }

    /**
     *
     * @param backdropPath
     *     The backdrop_path
     */
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    /**
     *
     * @return
     *     The popularity
     */
    public Double getPopularity() {
        return popularity;
    }

    /**
     *
     * @param popularity
     *     The popularity
     */
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    /**
     *
     * @return
     *     The voteCount
     */
    public Integer getVoteCount() {
        return voteCount;
    }

    /**
     *
     * @param voteCount
     *     The vote_count
     */
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    /**
     *
     * @return
     *     The voteAverage
     */
    public Double getVoteAverage() {
        return voteAverage;
    }

    /**
     *
     * @param voteAverage
     *     The vote_average
     */
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
