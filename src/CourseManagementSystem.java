import java.util.*;
import model.*;
import repository.*;
import manager.*;
import exception.*;

public class CourseManagementSystem {
    private Scanner scanner;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private InstructorRepository instructorRepository;
    private EnrollmentManager enrollmentManager;
    
    public CourseManagementSystem() {
        this.scanner = new Scanner(System.in);
        this.courseRepository = new CourseRepository();
        this.studentRepository = new StudentRepository();
        this.instructorRepository = new InstructorRepository();
        this.enrollmentManager = new EnrollmentManager();
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        Instructor instructor1 = new Instructor("I001", "Dr. Budi Santoso", "budi@email.com", "Java Programming");
        Instructor instructor2 = new Instructor("I002", "Prof. Marcel Ahmad", "marcel@email.com", "Web Development");
        instructorRepository.add(instructor1);
        instructorRepository.add(instructor2);
        
        OnlineCourse course1 = new OnlineCourse("C001", "Java Fundamentals", 500000, instructor1, "zoom.us/java");
        OfflineCourse course2 = new OfflineCourse("C002", "Web Design Workshop", 750000, instructor2, "Gedung A, Ruang 301");
        courseRepository.add(course1);
        courseRepository.add(course2);
    }
    
    public void run() {
        boolean running = true;
        
        while (running) {
            displayMainMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        manageStudents();
                        break;
                    case 2:
                        manageCourses();
                        break;
                    case 3:
                        manageEnrollments();
                        break;
                    case 4:
                        displayReports();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Terima kasih telah menggunakan sistem!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Input harus berupa angka!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
    
    private void displayMainMenu() {
        System.out.println("=== SISTEM MANAJEMEN KURSUS ONLINE ===");
        System.out.println("1. Kelola Mahasiswa");
        System.out.println("2. Kelola Kursus");
        System.out.println("3. Kelola Pendaftaran");
        System.out.println("4. Lihat Laporan");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }
    
    private void manageStudents() {
        System.out.println("\n=== KELOLA MAHASISWA ===");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Tampilkan Semua Mahasiswa");
        System.out.println("3. Cari Mahasiswa");
        System.out.print("Pilih: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus berupa angka!");
        }
    }
    
    private void addStudent() {
        System.out.print("ID Mahasiswa: ");
        String id = scanner.nextLine();
        System.out.print("Nama: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Jurusan: ");
        String major = scanner.nextLine();
        
        Student student = new Student(id, name, email, major);
        studentRepository.add(student);
        System.out.println("Mahasiswa berhasil ditambahkan!");
    }
    
    private void displayAllStudents() {
        List<Student> students = studentRepository.getAll();
        
        if (students.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }
        
        System.out.println("\n=== DAFTAR MAHASISWA ===");
        for (Student student : students) {
            student.displayInfo();
            System.out.println("------------------------");
        }
    }
    
    private void searchStudent() {
        System.out.print("Masukkan ID Mahasiswa: ");
        String id = scanner.nextLine();
        
        try {
            Student student = studentRepository.findById(id);
            System.out.println("\n=== DATA MAHASISWA ===");
            student.displayInfo();
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void manageCourses() {
        System.out.println("\n=== KELOLA KURSUS ===");
        System.out.println("1. Tambah Kursus Online");
        System.out.println("2. Tambah Kursus Offline");
        System.out.println("3. Tampilkan Semua Kursus");
        System.out.println("4. Cari Kursus");
        System.out.print("Pilih: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    addOnlineCourse();
                    break;
                case 2:
                    addOfflineCourse();
                    break;
                case 3:
                    displayAllCourses();
                    break;
                case 4:
                    searchCourse();
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus berupa angka!");
        }
    }
    
    private void addOnlineCourse() {
        System.out.print("ID Kursus: ");
        String id = scanner.nextLine();
        System.out.print("Nama Kursus: ");
        String name = scanner.nextLine();
        System.out.print("Harga: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("ID Instruktur: ");
        String instructorId = scanner.nextLine();
        System.out.print("Link Meeting: ");
        String meetingLink = scanner.nextLine();
        
        try {
            Instructor instructor = instructorRepository.findById(instructorId);
            OnlineCourse course = new OnlineCourse(id, name, price, instructor, meetingLink);
            courseRepository.add(course);
            System.out.println("Kursus online berhasil ditambahkan!");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void addOfflineCourse() {
        System.out.print("ID Kursus: ");
        String id = scanner.nextLine();
        System.out.print("Nama Kursus: ");
        String name = scanner.nextLine();
        System.out.print("Harga: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("ID Instruktur: ");
        String instructorId = scanner.nextLine();
        System.out.print("Lokasi: ");
        String location = scanner.nextLine();
        
        try {
            Instructor instructor = instructorRepository.findById(instructorId);
            OfflineCourse course = new OfflineCourse(id, name, price, instructor, location);
            courseRepository.add(course);
            System.out.println("Kursus offline berhasil ditambahkan!");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void displayAllCourses() {
        List<Course> courses = courseRepository.getAll();
        
        if (courses.isEmpty()) {
            System.out.println("Belum ada data kursus.");
            return;
        }
        
        System.out.println("\n=== DAFTAR KURSUS ===");
        for (Course course : courses) {
            course.displayInfo();
            System.out.println("------------------------");
        }
    }
    
    private void searchCourse() {
        System.out.print("Masukkan ID Kursus: ");
        String id = scanner.nextLine();
        
        try {
            Course course = courseRepository.findById(id);
            System.out.println("\n=== DATA KURSUS ===");
            course.displayInfo();
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void manageEnrollments() {
        System.out.println("\n=== KELOLA PENDAFTARAN ===");
        System.out.println("1. Daftarkan Mahasiswa ke Kursus");
        System.out.println("2. Tampilkan Semua Pendaftaran");
        System.out.println("3. Lihat Kursus Mahasiswa");
        System.out.print("Pilih: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    displayAllEnrollments();
                    break;
                case 3:
                    displayStudentCourses();
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus berupa angka!");
        }
    }
    
    private void enrollStudent() {
        System.out.print("ID Mahasiswa: ");
        String studentId = scanner.nextLine();
        System.out.print("ID Kursus: ");
        String courseId = scanner.nextLine();
        
        try {
            Student student = studentRepository.findById(studentId);
            Course course = courseRepository.findById(courseId);
            
            Enrollment enrollment = new Enrollment(student, course);
            enrollmentManager.addEnrollment(enrollment);
            
            System.out.println("Pendaftaran berhasil!");
            System.out.println("Total biaya: Rp " + course.getPrice());
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DuplicateEnrollmentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void displayAllEnrollments() {
        List<Enrollment> enrollments = enrollmentManager.getAllEnrollments();
        
        if (enrollments.isEmpty()) {
            System.out.println("Belum ada pendaftaran.");
            return;
        }
        
        System.out.println("\n=== DAFTAR PENDAFTARAN ===");
        for (Enrollment enrollment : enrollments) {
            enrollment.displayInfo();
            System.out.println("------------------------");
        }
    }
    
    private void displayStudentCourses() {
        System.out.print("Masukkan ID Mahasiswa: ");
        String studentId = scanner.nextLine();
        
        try {
            Student student = studentRepository.findById(studentId);
            List<Enrollment> enrollments = enrollmentManager.getEnrollmentsByStudent(student);
            
            if (enrollments.isEmpty()) {
                System.out.println("Mahasiswa belum terdaftar di kursus manapun.");
                return;
            }
            
            System.out.println("\n=== KURSUS YANG DIAMBIL ===");
            System.out.println("Mahasiswa: " + student.getName());
            for (Enrollment enrollment : enrollments) {
                System.out.println("- " + enrollment.getCourse().getName() + 
                                 " (Status: " + enrollment.getStatus() + ")");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void displayReports() {
        System.out.println("\n=== LAPORAN ===");
        System.out.println("Total Mahasiswa: " + studentRepository.getAll().size());
        System.out.println("Total Kursus: " + courseRepository.getAll().size());
        System.out.println("Total Pendaftaran: " + enrollmentManager.getAllEnrollments().size());
        
        double totalRevenue = enrollmentManager.calculateTotalRevenue();
        System.out.println("Total Pendapatan: Rp " + totalRevenue);
    }
}