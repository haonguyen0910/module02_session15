package quanlymonhoc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SubjectManager<Subject> subjectManager = new SubjectManager<>();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = getIntInput("Chọn chức năng: ");

            switch (choice) {
                case 1:
                    displayAllSubjects();
                    break;
                case 2:
                    addSubject();
                    break;
                case 3:
                    deleteSubject();
                    break;
                case 4:
                    searchSubjectByName();
                    break;
                case 5:
                    filterSubjectsByCredits();
                    break;
                case 6:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ! Vui lòng chọn từ 1-6.");
            }
        } while (choice != 6);
    }

    private static void displayMenu() {
        System.out.println("\n========== MENU QUẢN LÝ MÔN HỌC ==========");
        System.out.println("1. Hiển thị danh sách môn học");
        System.out.println("2. Thêm môn học");
        System.out.println("3. Xóa môn học");
        System.out.println("4. Tìm kiếm môn học theo tên");
        System.out.println("5. Lọc môn học theo tín chỉ (credits > 3)");
        System.out.println("6. Thoát");
    }

    private static void displayAllSubjects() {
        subjectManager.displayAllSubjects();
    }

    private static void addSubject() {
        String code = getStringInput("Nhập mã môn học: ");

        if (subjectManager.existsByCode(code)) {
            System.out.println("Mã môn học đã tồn tại!");
            return;
        }

        String name = getStringInput("Nhập tên môn học: ");
        int credits = getCreditsInput("Nhập số tín chỉ (1-10): ");
        Date startDate = getDateInput("Nhập ngày bắt đầu (dd/MM/yyyy): ");
        Subject newSubject = new Subject(code, name, credits, startDate);

        if (subjectManager.addSubject(newSubject)) {
            System.out.println("Thêm môn học thành công!");
        } else {
            System.out.println("Thêm môn học thất bại!");
        }
    }

    private static void deleteSubject() {
        String code = getStringInput("Nhập mã môn học cần xóa: ");

        if (subjectManager.deleteSubject(code)) {
            System.out.println("Xóa môn học thành công!");
        } else {
            System.out.println("Không tìm thấy môn học với mã '" + code + "'!");
        }
    }

    private static void searchSubjectByName() {
        String name = getStringInput("Nhập tên môn học cần tìm: ");

        Subject result = subjectManager.searchByName(name);

        if (result != null) {
            System.out.println("Kết quả tìm kiếm:");
            System.out.println(result);
        } else {
            System.out.println("Không có môn học phù hợp!");
        }
    }

    private static void filterSubjectsByCredits() {
        List<Subject> filteredSubjects = subjectManager.filterByCredits();

        if (filteredSubjects.isEmpty()) {
            System.out.println("Không có môn học nào có số tín chỉ > 3!");
        } else {
            System.out.println("Các môn học có số tín chỉ > 3:");

            for (Subject subject : filteredSubjects) {
                System.out.println(subject);
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
                System.out.println("Vui lòng nhập một số nguyên hợp lệ!");
            }
        }
    }

    private static int getCreditsInput(String input) {
        while (true) {
            try {
                System.out.print(input);
                int credits = Integer.parseInt(scanner.nextLine().trim());

                if (credits <= 0) {
                    throw new IllegalArgumentException("Số tín chỉ phải lớn hơn 0!");
                }
                if (credits > 10) {
                    throw new IllegalArgumentException("Số tín chỉ không được vượt quá 10!");
                }
                return credits;

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Date getDateInput(String input) {
        DATE_FORMAT.setLenient(false);

        while (true) {
            try {
                System.out.print(input);
                String dateStr = scanner.nextLine().trim();
                return DATE_FORMAT.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Định dạng ngày không hợp lệ! Vui lòng nhập theo dd/MM/yyyy");
            }
        }
    }
}