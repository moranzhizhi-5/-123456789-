import java.time.LocalDateTime;

public class Movie {
    private String title;
    private String director;
    private String cast;
    private String description;
    private int duration;
    private double rating;

    public Movie(String title, String director, String cast, String description, int duration, double rating) {
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.description = description;
        this.duration = duration;
        this.rating = rating;
    }

    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public String getCast() { return cast; }
    public String getDescription() { return description; }
    public int getDuration() { return duration; }
    public double getRating() { return rating; }
}