package quanlyphim;

import java.util.ArrayList;
import java.util.List;

public class MovieManager<T extends Movie> {
    private ArrayList<T> movieList;

    public MovieManager() {
        this.movieList = new ArrayList<>();
    }

    public boolean addMovie(T movie) {
        for (T m : movieList) {
            if (m.getId().equals(movie.getId())) {
                return false;
            }
        }
        return movieList.add(movie);
    }

    public boolean updateMovie(String id, T updatedMovie) {
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getId().equals(id)) {
                movieList.set(i, updatedMovie);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMovie(String id) {
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getId().equals(id)) {
                movieList.remove(i);
                return true;
            }
        }
        return false;
    }

    public void displayAllMovies() {
        if (movieList.isEmpty()) {
            System.out.println("Danh sách phim trống!");
        } else {
            System.out.println("Danh sách phim:");
            for (T movie : movieList) {
                System.out.println(movie);
            }
        }
    }

    public List<T> searchByTitle(String keyword) {
        List<T> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (T movie : movieList) {
            if (movie.getTitle().toLowerCase().contains(lowerKeyword)) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<T> filterByRating(double minRating) {
        List<T> result = new ArrayList<>();

        for (T movie : movieList) {
            if (movie.getRating() > minRating) {
                result.add(movie);
            }
        }
        return result;
    }

    public boolean existsById(String id) {
        for (T movie : movieList) {
            if (movie.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public T getMovieById(String id) {
        for (T movie : movieList) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    public ArrayList<T> getAllMovies() {
        return movieList;
    }
}