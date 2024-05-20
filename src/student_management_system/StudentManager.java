package student_management_system;

import java.util.*;

class StudentManager {
    private Set<Student> students;

    public StudentManager() {
        students = new TreeSet<>();
        loadStudents();
    }

    private void loadStudents() {
        Random random = new Random();

       
        Student johnDoe = new Student("John Doe", "123");
        for (String course : Student.COURSES) {
            int randomGrade = 5 + random.nextInt(6);
            int randomAbsence = random.nextInt(5);
            johnDoe.updateGrades(course, randomGrade);
            johnDoe.updateAbsences(course, randomAbsence);
        }
        students.add(johnDoe);

        
        Student janeSmith = new Student("Jane Smith", "456");
        for (String course : Student.COURSES) {
            int randomGrade = 5 + random.nextInt(6);
            int randomAbsence = random.nextInt(5);
            janeSmith.updateGrades(course, randomGrade);
            janeSmith.updateAbsences(course, randomAbsence);
        }
        students.add(janeSmith);
    }

    public void addStudent(Student student) {
       
        boolean studentExists = students.stream().anyMatch(s -> s.getId().equals(student.getId()));

        if (studentExists) {
            System.out.println("A student with the same ID already exists.");
        } else {
            students.add(student);
            System.out.println("Student added successfully.");
        }
    }

    public void displayStudents() {
        for (Student student : students) {
            student.displayDetails();
        }
    }

    public void updateAbsences(String studentId, String course, int count) {
        Student student = findStudentById(studentId);
        if (student != null) {
            if (student.getCourses().contains(course)) {
                student.updateAbsences(course, count);
                System.out.println("Absences updated successfully.");
            } else {
                System.err.println("Error: Course does not exist.");
            }
        } else {
            System.err.println("Error: Student with ID " + studentId + " not found.");
        }
    }

    public void updateGrades(String studentId, String course, double grade) {
        Student student = findStudentById(studentId);
        if (student != null) {
            if (student.getCourses().contains(course)) {
                student.updateGrades(course, grade);
                System.out.println("Grades updated successfully.");
            } else {
                System.err.println("Error: Course does not exist.");
            }
        } else {
            System.err.println("Error: Student with ID " + studentId + " not found.");
        }
    }

    private Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null; 
    }

    public void displayAverageGrades() {
        for (Student student : students) {
            double averageGrade = student.calculateAverageGrade();
            System.out.println("Student ID: " + student.getId() + ", Average Grade: " + averageGrade);
        }
    }

    public void displayTotalAbsences() {
        for (Student student : students) {
            int totalAbsences = student.calculateTotalAbsences();
            System.out.println("Student ID: " + student.getId() + ", Total Absences: " + totalAbsences);
        }
    }

    public void displayCourseGradesAndAbsences() {
        for (String course : Student.COURSES) {
            double courseAverageGrade = students.stream()
                    .mapToDouble(student -> student.getGrades().getOrDefault(course, 0.0))
                    .average()
                    .orElse(0.0);

            int totalCourseAbsences = students.stream()
                    .map(student -> student.getAbsences().get(course + ".Abs"))
                    .filter(Objects::nonNull)
                    .mapToInt(Integer::intValue)
                    .sum();

            System.out.println("Course: " + course + ", Average Grade: " + courseAverageGrade +
                    ", Total Absences: " + totalCourseAbsences);
        }
    }
}
