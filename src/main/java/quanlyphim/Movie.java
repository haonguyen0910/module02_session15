package quanlyphim;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie {
    private String id;
    private String title;
    private String director;
    private Date releaseDate;
    private double rating;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public Movie(String id, String title, String director, Date releaseDate, double rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        String formattedDate = (releaseDate != null) ? DATE_FORMAT.format(releaseDate) : "null";
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseDate=" + formattedDate +
                ", rating=" + rating +
                '}';
    }
}