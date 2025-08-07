import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private Theater theater;
    private LocalDateTime startTime;
    private double price;

    public Screening(Movie movie, Theater theater, LocalDateTime startTime, double price) {
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public double getPrice() {
        return price;
    }
}