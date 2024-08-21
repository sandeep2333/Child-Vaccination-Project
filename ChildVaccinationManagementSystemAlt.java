import java.util.*;

public class ChildVaccinationManagementSystemAlt {
    private static Map<String, Child> childrenMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nChild Vaccination Management System");
            System.out.println("1. Register Child");
            System.out.println("2. Book Vaccination Appointment");
            System.out.println("3. View Vaccination Records");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    registerChild();
                    break;
                case 2:
                    bookAppointment();
                    break;
                case 3:
                    viewRecords();
                    break;
                case 4:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void registerChild() {
        System.out.print("Enter child's ID: ");
        String id = scanner.nextLine();
        if (childrenMap.containsKey(id)) {
            System.out.println("Child with this ID is already registered.");
            return;
        }
        
        System.out.print("Enter child's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter child's age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter child's gender: ");
        String gender = scanner.nextLine();

        Child child = new Child(id, name, age, gender);
        childrenMap.put(id, child);
        System.out.println("Child registered successfully.");
    }

    private static void bookAppointment() {
        System.out.print("Enter child's ID: ");
        String id = scanner.nextLine();
        Child child = childrenMap.get(id);
        
        if (child == null) {
            System.out.println("Child not found.");
            return;
        }

        System.out.print("Enter vaccine name: ");
        String vaccineName = scanner.nextLine();
        System.out.print("Enter appointment date (yyyy-mm-dd): ");
        String dateInput = scanner.nextLine();
        
        if (!isValidDate(dateInput)) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            return;
        }

        VaccinationAppointment appointment = new VaccinationAppointment(vaccineName, dateInput);
        child.addAppointment(appointment);
        System.out.println("Appointment booked successfully.");
    }

    private static void viewRecords() {
        System.out.print("Enter child's ID: ");
        String id = scanner.nextLine();
        Child child = childrenMap.get(id);
        
        if (child == null) {
            System.out.println("Child not found.");
            return;
        }

        System.out.println("Vaccination Records for " + child.getName() + ":");
        child.displayAppointments();
    }

    private static boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}

class Child {
    private String id;
    private String name;
    private int age;
    private String gender;
    private List<VaccinationAppointment> appointments;

    public Child(String id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.appointments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addAppointment(VaccinationAppointment appointment) {
        appointments.add(appointment);
    }

    public void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        for (VaccinationAppointment appointment : appointments) {
            System.out.println("Vaccine: " + appointment.getVaccineName() + ", Date: " + appointment.getDate());
        }
    }
}

class VaccinationAppointment {
    private String vaccineName;
    private String date;

    public VaccinationAppointment(String vaccineName, String date) {
        this.vaccineName = vaccineName;
        this.date = date;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getDate() {
        return date;
    }
}
