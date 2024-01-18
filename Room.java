import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

class Room {
    int roomNumber;
    boolean isOccupied;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " - " + (isOccupied ? "Occupied" : "Available");
    }
}

class Reservation {
    String guestName;
    int roomNumber;

    public Reservation(String guestName, int roomNumber) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
    }
}

 class HotelManagementSystem extends Frame implements ActionListener {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private TextArea outputArea;

    public HotelManagementSystem() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            rooms.add(new Room(i));
        }

        Label label = new Label("Hotel Management System");
        Button checkAvailabilityButton = new Button("Check Room Availability");
        Button reserveRoomButton = new Button("Reserve Room");
        Button viewReservationsButton = new Button("View Reservations");

        outputArea = new TextArea();

        setLayout(new FlowLayout());

        add(label);
        add(checkAvailabilityButton);
        add(reserveRoomButton);
        add(viewReservationsButton);
        add(outputArea);

        checkAvailabilityButton.addActionListener(this);
        reserveRoomButton.addActionListener(this);
        viewReservationsButton.addActionListener(this);

        setTitle("Hotel Management System");
        setSize(400, 300);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Check Room Availability":
                checkRoomAvailability();
                break;
            case "Reserve Room":
                reserveRoom();
                break;
            case "View Reservations":
                viewReservations();
                break;
        }
    }

    private void checkRoomAvailability() {
        outputArea.setText("Room Availability:\n");
        for (Room room : rooms) {
            outputArea.append(room + "\n");
        }
    }

    private void reserveRoom() {
        String guestName = JOptionPane.showInputDialog(this, "Enter guest name:");
        if (guestName != null) {
            String roomNumberStr = JOptionPane.showInputDialog(this, "Enter room number:");
            if (roomNumberStr != null) {
                try {
                    int roomNumber = Integer.parseInt(roomNumberStr);
                    if (roomNumber >= 1 && roomNumber <= 10) {
                        Room room = rooms.get(roomNumber - 1);
                        if (!room.isOccupied) {
                            room.isOccupied = true;
                            reservations.add(new Reservation(guestName, roomNumber));
                            JOptionPane.showMessageDialog(this, "Room reserved successfully.");
                        } else {
                            JOptionPane.showMessageDialog(this, "Room is already occupied.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid room number.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid room number.");
                }
            }
        }
    }

    private void viewReservations() {
        outputArea.setText("Reservations:\n");
        for (Reservation reservation : reservations) {
            outputArea.append("Guest: " + reservation.guestName + ", Room: " + reservation.roomNumber + "\n");
        }
    }

    public static void main(String[] args) {
        new HotelManagementSystem();
    }
}
