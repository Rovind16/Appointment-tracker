// Schedule.java

package Appoinment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Schedule {
    private String name;
    private String user_email;
    private String date;
    private String time;
    private String details;

    public Schedule(String name, String user_email, String date, String time, String details) {
        this.name = name;
        this.user_email = user_email;
        this.date = date;
        this.time = time;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuser_email() {
        return user_email;
    }

    public void setuser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean saveAppointment() {
        String DB_URL = "jdbc:mysql://localhost:3306/it";
        String USER = "root";
        String PASS = "1234";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String insertQuery = "INSERT INTO Schedule (name, email, DATE, time, details) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, this.name);
                preparedStatement.setString(2, this.user_email);
                preparedStatement.setString(3, this.date);
                preparedStatement.setString(4, this.time);
                preparedStatement.setString(5, this.details);

                preparedStatement.executeUpdate();
                return true; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }

    public boolean sendEmail() {
        String to = this.user_email;
        String from = "whiteking1409@gmail.com"; 
        final String username = "whiteking1409@gmail.com";
        final String password = "rovind1409"; 
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Schedule Details");

            String emailContent = "Schedule Details:\n" +
                    "Name: " + this.name + "\n" +
                    "Date: " + this.date + "\n" +
                    "Time: " + this.time + "\n" +
                    "Details: " + this.details;

            message.setText(emailContent);

            Transport.send(message);
            System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException mex) {
            System.out.println("Sent message not successfully....");
            mex.printStackTrace();
            return false;
        }
    }

    
    public String toString() {
        return "Schedule{" +
                "name='" + name + '\'' +
                ", user_email='" + user_email + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
