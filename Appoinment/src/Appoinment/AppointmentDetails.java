package Appoinment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AppointmentDetails {
    private String email;
    private String name;
    private String date;
    private String time;
    private String details;

    public AppointmentDetails(String email) {
        this.email = email;
        fetchDataFromDatabase();
    }
    
    private void fetchDataFromDatabase() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/it", "root", "1234");

            String sql = "SELECT name, email, DATE, time, details FROM Schedule WHERE email=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
                email = resultSet.getString("email");
                date = resultSet.getString("DATE");
                time = resultSet.getString("time");
                details = resultSet.getString("details");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDetails() {
        return details;
    }
}
