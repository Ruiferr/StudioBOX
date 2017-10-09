package movies.flag.pt.moviesapp.databases;

import com.orm.SugarRecord;


public class MovieDatabase extends SugarRecord {

    private int movieID;
    private String MovieTitle;
    private String MovieOverview;
    private String MovieReleaseDate;
    private String MovieImg;
    private Double MovieVote;

    public MovieDatabase(){

        //Foi necessário adicionar este constructor default para evitar o err: "java.lang.NoSuchMethodException: <init>"

    }


    public MovieDatabase(int id, String title, String overview, String releaseDate, String imgLink, Double vote){
        this.movieID = id;
        this.MovieTitle = title;
        this.MovieOverview = overview;
        this.MovieReleaseDate = releaseDate;
        this.MovieImg = imgLink;
        this.MovieVote = vote;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getMovieImg() {
        return MovieImg;
    }

    public String getMovieOverview() {
        return MovieOverview;
    }

    public String getMovieReleaseDate() {
        return MovieReleaseDate;
    }

    public String getMovieTitle() {
        return MovieTitle;
    }

    public Double getMovieVote() {
        return MovieVote;
    }

    public void setMovieOverview(String movieOverview) {
        MovieOverview = movieOverview;
    }

    public void setMovieImg(String movieImg) {
        MovieImg = movieImg;
    }

    public void setMovieVote(Double movieVote) {
        MovieVote = movieVote;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public void setMovieTitle(String movieTitle) {
        MovieTitle = movieTitle;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        MovieReleaseDate = movieReleaseDate;
    }
}
