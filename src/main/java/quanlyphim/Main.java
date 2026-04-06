package quanlyphim;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MovieManager<Movie> movieManager = new MovieManager<>();
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = getIntInput("Chọn chức năng: ");
            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    deleteMovie();
                    break;
                case 3:
                    updateMovie();
                    break;
                case 4:
                    movieManager.displayAllMovies();
                    break;
                case 5:
                    searchMovieByTitle();
                    break;
                case 6:
                    filterMoviesByRating();
                    break;
                case 7:
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ! Vui lòng chọn từ 1-7.");
            }
        } while (choice != 7);
    }

    private static void displayMenu() {
        System.out.println("\n========== MENU QUẢN LÝ PHIM ==========");
        System.out.println("1. Thêm phim");
        System.out.println("2. Xóa phim");
        System.out.println("3. Sửa phim");
        System.out.println("4. Hiển thị phim");
        System.out.println("5. Tìm kiếm phim theo tên");
        System.out.println("6. Lọc phim theo rating");
        System.out.println("7. Thoát");
    }

    private static void addMovie() {
        String id = getStringInput("Nhập ID phim: ");

        if (movieManager.existsById(id)) {
            System.out.println("ID phim đã tồn tại! Vui lòng nhập ID khác.");
            return;
        }

        String title = getStringInput("Nhập tiêu đề phim: ");
        String director = getStringInput("Nhập đạo diễn: ");
        Date releaseDate = getDateInput("Nhập ngày phát hành (dd-MM-yyyy): ");
        double rating = getDoubleInput("Nhập rating (0-10): ");

        Movie newMovie = new Movie(id, title, director, releaseDate, rating);

        if (movieManager.addMovie(newMovie)) {
            System.out.println("Thêm phim thành công!");
        } else {
            System.out.println("Thêm phim thất bại!");
        }
    }

    private static void deleteMovie() {
        String id = getStringInput("Nhập ID phim cần xóa: ");

        if (movieManager.deleteMovie(id)) {
            System.out.println("Phim đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy phim muốn xóa!");
        }
    }

    private static void updateMovie() {
        String id = getStringInput("Mời nhập ID phim muốn sửa: ");

        Movie existingMovie = movieManager.getMovieById(id);
        if (existingMovie == null) {
            System.out.println("Không tìm thấy phim với id = " + id);
            return;
        }

        System.out.println("Thông tin phim hiện tại:");
        System.out.println(existingMovie);
        System.out.println("Nhập thông tin mới:");

        String newTitle = getStringInput("Nhập tiêu đề phim mới (hiện tại: " + existingMovie.getTitle() + "): ");
        if (!newTitle.trim().isEmpty()) {
            existingMovie.setTitle(newTitle);
        }

        String newDirector = getStringInput("Nhập đạo diễn mới (hiện tại: " + existingMovie.getDirector() + "): ");
        if (!newDirector.trim().isEmpty()) {
            existingMovie.setDirector(newDirector);
        }

        String dateInput = getStringInput("Nhập ngày phát hành mới dd-MM-yyyy (hiện tại: " +
                dateFormatter.format(existingMovie.getReleaseDate()) + "): ");
        if (!dateInput.trim().isEmpty()) {
            try {
                Date newDate = dateFormatter.parse(dateInput);
                existingMovie.setReleaseDate(newDate);
            } catch (ParseException e) {
                System.out.println("Định dạng ngày không hợp lệ! Giữ nguyên ngày cũ.");
            }
        }

        String ratingInput = getStringInput("Nhập rating mới (hiện tại: " + existingMovie.getRating() + "): ");
        if (!ratingInput.trim().isEmpty()) {
            try {
                double newRating = Double.parseDouble(ratingInput);
                if (newRating >= 0 && newRating <= 10) {
                    existingMovie.setRating(newRating);
                } else {
                    System.out.println("Rating phải từ 0-10! Giữ nguyên rating cũ.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Rating không hợp lệ! Giữ nguyên rating cũ.");
            }
        }

        if (movieManager.updateMovie(id, existingMovie)) {
            System.out.println("Cập nhật phim thành công!");
        } else {
            System.out.println("Cập nhật phim thất bại!");
        }
    }

    private static void searchMovieByTitle() {
        String keyword = getStringInput("Nhập tiêu đề phim để tìm kiếm: ");

        List<Movie> results = movieManager.searchByTitle(keyword);

        if (results.isEmpty()) {
            System.out.println("Không tìm thấy phim!");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Movie movie : results) {
                System.out.println(movie);
            }
        }
    }

    private static void filterMoviesByRating() {
        double minRating = getDoubleInput("Nhập rating tối thiểu để lọc: ");

        List<Movie> results = movieManager.filterByRating(minRating);

        if (results.isEmpty()) {
            System.out.println("Không có phim nào có rating > " + minRating);
        } else {
            System.out.println("Phim có rating lớn hơn " + minRating + ":");
            for (Movie movie : results) {
                System.out.println(movie);
            }
        }
    }

    private static String getStringInput(String input) {
        System.out.print(input);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String input) {
        while (true) {
            try {
                System.out.print(input);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên!");
            }
        }
    }

    private static double getDoubleInput(String input) {
        while (true) {
            try {
                System.out.print(input);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0 || value > 10) {
                    System.out.println("Rating phải từ 0 đến 10!");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số thực hợp lệ!");
            }
        }
    }

    private static Date getDateInput(String input) {
        dateFormatter.setLenient(false);

        while (true) {
            try {
                System.out.print(input);
                String dateStr = scanner.nextLine().trim();
                return dateFormatter.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Định dạng ngày không hợp lệ! Vui lòng nhập theo dd-MM-yyyy");
            }
        }
    }
}