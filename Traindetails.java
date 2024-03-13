//package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Traindetails extends JFrame {
    private JTextField passengerFirstNameField, passengerLastNameField, passengerEmailField, passengerPhoneField, passengerAddressField;
    private JTextField trainNameField, sourceStationField, destinationStationField, departureTimeField, arrivalTimeField, totalSeatsField;
    private JTextField reservationPassengerIdField, reservationTrainIdField, reservationDateField, reservationStatusField, reservationTotalFareField;
    private JTextField reservationDetailReservationIdField, reservationDetailSeatNumberField, reservationDetailCoachNumberField;

    public Traindetails() {
        setTitle("Train Reservation System");
        setSize(400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel passengerLabel = new JLabel("Passenger Information");
        JLabel passengerFirstNameLabel = new JLabel("First Name:");
        JLabel passengerLastNameLabel = new JLabel("Last Name:");
        JLabel passengerEmailLabel = new JLabel("Email:");
        JLabel passengerPhoneLabel = new JLabel("Phone:");
        JLabel passengerAddressLabel = new JLabel("Address:");

        passengerFirstNameField = new JTextField(20);
        passengerLastNameField = new JTextField(20);
        passengerEmailField = new JTextField(20);
        passengerPhoneField = new JTextField(15);
        passengerAddressField = new JTextField(30);

        JLabel trainLabel = new JLabel("Train Information");
        JLabel trainNameLabel = new JLabel("Train Name:");
        JLabel sourceStationLabel = new JLabel("Source Station:");
        JLabel destinationStationLabel = new JLabel("Destination Station:");
        JLabel departureTimeLabel = new JLabel("Departure Time:");
        JLabel arrivalTimeLabel = new JLabel("Arrival Time:");
        JLabel totalSeatsLabel = new JLabel("Total Seats:");

        trainNameField = new JTextField(20);
        sourceStationField = new JTextField(20);
        destinationStationField = new JTextField(20);
        departureTimeField = new JTextField(20);
        arrivalTimeField = new JTextField(20);
        totalSeatsField = new JTextField(5);

        JLabel reservationLabel = new JLabel("Reservation Information");
        JLabel reservationPassengerIdLabel = new JLabel("Passenger ID:");
        JLabel reservationTrainIdLabel = new JLabel("Train ID:");
        JLabel reservationDateLabel = new JLabel("Date of Reservation (YYYY-MM-DD):");
        JLabel reservationStatusLabel = new JLabel("Status:");
        JLabel reservationTotalFareLabel = new JLabel("Total Fare:");

        reservationPassengerIdField = new JTextField(5);
        reservationTrainIdField = new JTextField(5);
        reservationDateField = new JTextField(10);
        reservationStatusField = new JTextField(20);
        reservationTotalFareField = new JTextField(10);

        JLabel reservationDetailLabel = new JLabel("Reservation Detail Information");
        JLabel reservationDetailReservationIdLabel = new JLabel("Reservation ID:");
        JLabel reservationDetailSeatNumberLabel = new JLabel("Seat Number:");
        JLabel reservationDetailCoachNumberLabel = new JLabel("Coach Number:");

        reservationDetailReservationIdField = new JTextField(5);
        reservationDetailSeatNumberField = new JTextField(5);
        reservationDetailCoachNumberField = new JTextField(5);

        JButton savePassengerButton = new JButton("Save Passenger");
        savePassengerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePassenger();
            }
        });

        JButton saveTrainButton = new JButton("Save Train");
        saveTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTrain();
            }
        });

        JButton saveReservationButton = new JButton("Save Reservation");
        saveReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReservation();
            }
        });

        JButton saveReservationDetailButton = new JButton("Save Reservation Detail");
        saveReservationDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReservationDetail();
            }
        });

        setLayout(new GridLayout(28, 2));
        add(passengerLabel);
        add(new JLabel());
        add(passengerFirstNameLabel);
        add(passengerFirstNameField);
        add(passengerLastNameLabel);
        add(passengerLastNameField);
        add(passengerEmailLabel);
        add(passengerEmailField);
        add(passengerPhoneLabel);
        add(passengerPhoneField);
        add(passengerAddressLabel);
        add(passengerAddressField);
        add(new JLabel());
        add(savePassengerButton);

        add(trainLabel);
        add(new JLabel());
        add(trainNameLabel);
        add(trainNameField);
        add(sourceStationLabel);
        add(sourceStationField);
        add(destinationStationLabel);
        add(destinationStationField);
        add(departureTimeLabel);
        add(departureTimeField);
        add(arrivalTimeLabel);
        add(arrivalTimeField);
        add(totalSeatsLabel);
        add(totalSeatsField);
        add(new JLabel());
        add(saveTrainButton);

        add(reservationLabel);
        add(new JLabel());
        add(reservationPassengerIdLabel);
        add(reservationPassengerIdField);
        add(reservationTrainIdLabel);
        add(reservationTrainIdField);
        add(reservationDateLabel);
        add(reservationDateField);
        add(reservationStatusLabel);
        add(reservationStatusField);
        add(reservationTotalFareLabel);
        add(reservationTotalFareField);
        add(new JLabel());
        add(saveReservationButton);

        add(reservationDetailLabel);
        add(new JLabel());
        add(reservationDetailReservationIdLabel);
        add(reservationDetailReservationIdField);
        add(reservationDetailSeatNumberLabel);
        add(reservationDetailSeatNumberField);
        add(reservationDetailCoachNumberLabel);
        add(reservationDetailCoachNumberField);
        add(new JLabel());
        add(saveReservationDetailButton);

        setVisible(true);
    }

    private void savePassenger() {
        String firstName = passengerFirstNameField.getText();
        String lastName = passengerLastNameField.getText();
        String email = passengerEmailField.getText();
        String phone = passengerPhoneField.getText();
        String address = passengerAddressField.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsproject", "root", "eerav");
            String sql = "INSERT INTO Passenger(FirstName, LastName, Email, Phone, Address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Passenger saved successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error saving passenger.");
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private void saveTrain() {
        String trainName = trainNameField.getText();
        String sourceStation = sourceStationField.getText();
        String destinationStation = destinationStationField.getText();
        String departureTime = departureTimeField.getText();
        String arrivalTime = arrivalTimeField.getText();
        int totalSeats = Integer.parseInt(totalSeatsField.getText());

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        try {
            Date departureDate = timeFormat.parse(departureTime);
            Date arrivalDate = timeFormat.parse(arrivalTime);

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsproject", "root", "eerav");
                String sql = "INSERT INTO Train(TrainName, SourceStation, DestinationStation, DepartureTime, ArrivalTime, TotalSeats) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, trainName);
                preparedStatement.setString(2, sourceStation);
                preparedStatement.setString(3, destinationStation);
                preparedStatement.setObject(4, new java.sql.Time(departureDate.getTime()));
                preparedStatement.setObject(5, new java.sql.Time(arrivalDate.getTime()));
                preparedStatement.setInt(6, totalSeats);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Train information saved successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error saving train information.");
                }

                preparedStatement.close();
                connection.close();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid time format. Use HH:mm.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "MySQL JDBC Driver not found.");
        }
    }

    private void saveReservation() {
        int passengerId = Integer.parseInt(reservationPassengerIdField.getText());
        int trainId = Integer.parseInt(reservationTrainIdField.getText());
        String dateOfReservationStr = reservationDateField.getText();
        String status = reservationStatusField.getText();
        double totalFare = Double.parseDouble(reservationTotalFareField.getText());

        Date dateOfReservation = null;
        try {
            dateOfReservation = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfReservationStr);
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsproject", "root", "eerav");
            String sql = "INSERT INTO Reservation(PassengerId, TrainId, DateOfReservation, Status, TotalFare) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, passengerId);
            preparedStatement.setInt(2, trainId);
            preparedStatement.setDate(3, new java.sql.Date(dateOfReservation.getTime()));
            preparedStatement.setString(4, status);
            preparedStatement.setDouble(5, totalFare);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Reservation information saved successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error saving reservation information.");
            }

            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "JDBC driver class not found. Check your JDBC driver configuration.");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void saveReservationDetail() {
        int reservationId = Integer.parseInt(reservationDetailReservationIdField.getText());
        String seatNumber = reservationDetailSeatNumberField.getText();
        String coachNumber = reservationDetailCoachNumberField.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsproject", "root", "eerav");
            String sql = "INSERT INTO Reservationdetail(ReservationId, SeatNumber, CoachNumber) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, reservationId);
            preparedStatement.setString(2, seatNumber);
            preparedStatement.setString(3, coachNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Reservation detail information saved successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error saving reservation detail information.");
            }

            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "JDBC driver class not found. Check your JDBC driver configuration.");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Traindetails();
            }
        });
    }
}
