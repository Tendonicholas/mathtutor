package main.java;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.*;

public class LoginPage extends JPanel {

    private static String url = "jdbc:mysql://aws.connect.psdb.cloud/mathtutor?sslMode=VERIFY_IDENTITY"; // Database URL
    private static String user = "wtrqh01o5xs1z0mh3ozm"; // Database username
    private static String dbPassword = "pscale_pw_UxzbcIBGiKhpgkeD9KAvSMncuTiO29tsfVYh9nlqBqO"; // Database password


    private static JTextField username, password;

    private static User player;
    public LoginPage(CardLayout cardLayout, JPanel panelContainer){
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);

        setLayout(null);

        username = new JTextField(16);
        username.setBounds(250, 50, 100, 50);

        password = new JTextField(16);
        password.setBounds(250, 250, 100, 50);


        JButton submitButton = getSubmitButton();


        add(username);
        add(password);
        add(submitButton);


    }

    private static JButton getSubmitButton() {
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(400, 50, 100, 50);
        submitButton.addActionListener(e ->{


            if (checkUserExists(username.getText())) {
                System.out.println("User exists. Retrieving data...");
                retrieveData(username.getText()); // Call your retrieveData method here
            } else {
                System.out.println("User does not exist.");
                insertData(username.getText(), password.getText());
            }


        });
        return submitButton;
    }

    private static void insertData(String username, String password) {

        String query = "INSERT INTO users (username, password) VALUES (?, ?);";
        try {
            // Establishing connection
            Connection con = DriverManager.getConnection(url, user, dbPassword);

            // Creating a PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(query);

            // Setting parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Executing update
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected");

            // Closing connections
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void retrieveData(String username) {
        String query = "SELECT username, password FROM users WHERE username = ?;"; // Modify this query to match your schema
        try {
            // Establishing connection
            Connection con = DriverManager.getConnection(url, user, dbPassword);

            // Creating a PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(query);

            // Setting parameter
            pstmt.setString(1, username);

            // Executing query
            ResultSet rs = pstmt.executeQuery();

            // Iterating through the result set
            while (rs.next()) {
                String name = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(name + " " + password);
            }

            // Closing resources
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkUserExists(String username) {

        String query = "SELECT COUNT(*) FROM users WHERE username = ?;";

        try (Connection con = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            // Setting parameter
            pstmt.setString(1, username);

            // Executing query
            ResultSet rs = pstmt.executeQuery();

            // Checking if user exists
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    // User exists
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // User does not exist
        return false;
    }

}
