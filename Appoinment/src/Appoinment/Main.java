// Main.java

package Appoinment;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------");
        System.out.println("Welcome To Appointment Tracker Application");
        System.out.println("If you are a new user, enter 1 else 2");
        int user = sc.nextInt();
        sc.nextLine();

        String name = "";
        String user_email = "";
        String appointments_details = "";
        if (user == 1) {
            System.out.println("Enter your Name: ");
            name = sc.nextLine();
            System.out.println("Enter your Email: ");
            user_email = sc.nextLine();
        } else {
            System.out.println("Enter your Email: ");
            user_email = sc.nextLine();
                System.out.println("Would you want to see your appointments? Type 'yes': ");
                appointments_details = sc.nextLine();
                int flag = 0;
                if ("yes".equalsIgnoreCase(appointments_details)) {
                    AppointmentDetails appointmentDetails = new AppointmentDetails(user_email);
                    flag = 1;
                    System.out.println("Name: " + appointmentDetails.getName());
                    System.out.println("Email: " + appointmentDetails.getEmail());
                    System.out.println("Date: " + appointmentDetails.getDate());
                    System.out.println("Time: " + appointmentDetails.getTime());
                    System.out.println("Details: " + appointmentDetails.getDetails());
                }
                if (flag == 0) {
                    System.out.println("There is no record in your email");
                }
            
        }

        System.out.print("Would you like to Schedule any appointment? Type 'yes': ");
        String scheduleCheck = sc.next();

        if ("yes".equalsIgnoreCase(scheduleCheck)) {
            System.out.println("Enter appointment date (yyyy-MM-dd): ");
            String date = sc.next();

            System.out.println("Enter appointment time: ");
            String time = sc.next();

            System.out.println("Enter the details for the appointment: ");
            String details = sc.next();

            Schedule schedule = new Schedule(name, user_email, date, time, details);

            System.out.println("Appointment Scheduled:");
            System.out.println("Name: " + schedule.getName());
            System.out.println("Email: " + schedule.getuser_email());
            System.out.println("Date: " + schedule.getDate());
            System.out.println("Time: " + schedule.getTime());
            System.out.println("Details: " + schedule.getDetails());

            boolean saved = schedule.saveAppointment();
            if (saved) {
                System.out.println("Appointment saved successfully!");
            } else {
                System.out.println("Failed to save appointment.");
            }
            boolean emailSent = schedule.sendEmail();
            if (emailSent) {
                System.out.println("Email sent successfully!");
            } else {
                System.out.println("Failed to send email.");
            }
        } else {
            System.out.println("Thank You!!!");
        }
    }
}

