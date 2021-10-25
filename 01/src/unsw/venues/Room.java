/**
 *
 */
package unsw.venues;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;


public class Room {
    private String room_name;
    private String size;
    private List<Booking> room_booking_list;

    public Room(String room_name, String size) {
        this.room_name = room_name;
        this.size = size;
        this.room_booking_list = new ArrayList<Booking>();
    }

    /**
     *
     * @return name of the room
     */
    public String getRoom_name() {
        return room_name;
    }

    /**
     *
     * @return size of the room
     */
    public String getSize() {
        return size;
    }

    /**
     *
     * @param booking booking add to the room's booking list
     */
    // add the booking session to the booking_list by order everytime
    public void add_booking(Booking booking) {
        LocalDate newStart = booking.getStart();
        // if the booking list is empty
        if (room_booking_list.isEmpty()) {
            room_booking_list.add(booking);
            return;
        // if the start date of the first booking is after the newstart
        // new booking become the first in the list
        } else if (room_booking_list.get(0).getStart().isAfter(newStart)) {
            room_booking_list.add(0, booking);
            return;
        // the new start date is after the last element in the list
        // new booking become the last in the list
        } else if (room_booking_list.get(room_booking_list.size()-1).getStart().isBefore(newStart)){
            room_booking_list.add(booking);
            return;
        // insert the booking in the middle of the list
        } else {
            for (Booking b: room_booking_list) {
                if (b.getStart().isAfter(newStart)) {
                    room_booking_list.add(room_booking_list.indexOf(b), booking);
                    break;
                }
            }
        }
    }

    /**
     *
     * @param booking remove the booking from the room booking list
     */
    // cancel the booking session from the booking_list
    public void cancel_booking(Booking booking) {
        this.room_booking_list.remove(booking);
    }

    /**
     *
     * @param s start date of the new booking request
     * @param e end date of the new booking request
     * @return true if the room is reserved already, false if not
     */
    // check if this booking of the room is get clashed with the other booked time of this room
    public boolean is_reserved(LocalDate s, LocalDate e) {
        for (Booking b: room_booking_list) {
            if ((!s.isAfter(b.getStart()) && !e.isBefore(b.getStart())) || (!s.isAfter(b.getEnd()) && !e.isBefore(b.getStart()))) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return information of the bookings in the room_booking_list
     */
    // get the room information as an JSONObject
    public JSONObject get_info() {
        JSONObject info = new JSONObject();
        JSONArray reservations = new JSONArray();

        for (Booking b: room_booking_list) {
            JSONObject res = b.get_booking_info();
            reservations.put(res);
        }

        info.put("room", room_name);
        info.put("reservations", reservations);
        return info;
    }
}