package quanlymonhoc;

import java.util.ArrayList;
import java.util.List;

public class SubjectManager<T extends Subject> {
    private ArrayList<T> subjectList;

    public SubjectManager() {
        this.subjectList = new ArrayList<>();
    }

    public boolean addSubject(T subject) {
        for (T s : subjectList) {
            if (s.getCode().equalsIgnoreCase(subject.getCode())) {
                return false;
            }
        }
        return subjectList.add(subject);
    }

    public boolean deleteSubject(String code) {
        for (int i = 0; i < subjectList.size(); i++) {
            if (subjectList.get(i).getCode().equalsIgnoreCase(code)) {
                subjectList.remove(i);
                return true;
            }
        }
        return false;
    }

    public void displayAllSubjects() {
        if (subjectList.isEmpty()) {
            System.out.println("Danh sách môn học trống!");
        } else {
            System.out.println("\n=== DANH SÁCH MÔN HỌC ===");
            for (T subject : subjectList) {
                System.out.println(subject);
            }
        }
    }

    public T searchByName(String name) {
        for (T subject : subjectList) {
            if (subject.getName().equalsIgnoreCase(name)) {
                return subject;
            }
        }
        return null;
    }

    public List<T> filterByCredits() {
        List<T> result = new ArrayList<>();
        for (T subject : subjectList) {
            if (subject.getCredits() > 3) {
                result.add(subject);
            }
        }
        return result;
    }

    public boolean existsByCode(String code) {
        for (T subject : subjectList) {
            if (subject.getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<T> getAllSubjects() {
        return subjectList;
    }
}
