package student_management_system;

import java.util.*;

class Student implements Comparable<Student> {
    private String name;
    private String id;
    public static final List<String> COURSES = Arrays.asList("OOP", "SAD", "DLD", "NME", "DM");
    private Map<String, Integer> absences;
    private Map<String, Double> grades;

    public List<String> getCourses() {
        return COURSES;
    }

    public Map<String, Integer> getAbsences() {
        return absences;
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        absences = new HashMap<>();
        grades = new HashMap<>();
        for (String course : COURSES) {
            absences.put(course + ".Abs", 0);
            grades.put(course, 4.0); // Initialize all grades to 4 by default
        }
    }

    public void updateAbsences(String course, int count) {
        absences.put(course + ".Abs", count);
    }

    public void updateGrades(String course, double grade) {
        grades.put(course, grade);
    }

    public double calculateAverageGrade() {
        if (COURSES.isEmpty()) {
            return 0.0;
        }

        double totalGrades = grades.values().stream().mapToDouble(Double::doubleValue).sum();
        return totalGrades / COURSES.size();
    }

    public int calculateTotalAbsences() {
        return absences.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void displayDetails() {
        System.out.println("ID: " + id + ", Name: " + name + ", Courses: " + COURSES +
                ", Grades: " + grades + ", Absences: " + absences +
                ", Average Grade: " + calculateAverageGrade() + ", Total Absences: " + calculateTotalAbsences());
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }
}
