/**
 *
 */
package unsw.venues;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Booking {
    // request id
    private String id;
    // start and end of the reservation
    private LocalDate start, end;
    // size of the rooms for booking
    private int small, medium, large;

    // list of the room that are booked by the booking
    private List<Room> booked_room_list;

    public Booking(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.booked_room_list = new ArrayList<Room>();
    }

    /**
     *
     * @param room room add to the booking's room list
     */
    public void add_Room(Room room) {
        this.booked_room_list.add(room);
    }

    /**
     * reset the booking and remove this booking from all the booked rooms
     */
    // remove all the booking from each booked room's booking list
    // and reset the booking
    public void reset() {
        this.id = null;
        this.start = null;
        this.end = null;
        this.small = 0;
        this.medium = 0;
        this.large = 0;
        for (Room r: this.booked_room_list) {
            r.cancel_booking(this);
        }
    }

    /**
     *
     * @return information of the booking(id, start date, end date)
     */
    public JSONObject get_booking_info() {
        JSONObject info = new JSONObject();
        info.put("id", id);
        info.put("start", start);
        info.put("end", end);
        return info;
    }

    /**
     *
     * @return id of the booking
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return start date of the booking
     */
    public LocalDate getStart() {
        return start;
    }

    /**
     *
     * @return end date of the booking
     */
    public LocalDate getEnd() {
        return end;
    }

    /**
     *
     * @return num of the small rooms booked
     */
    public int getSmall() {
        return small;
    }

    /**
     *
     * @return num of the medium rooms booked
     */
    public int getMedium() {
        return medium;
    }

    /**
     *
     * @return num of the large rooms booked
     */
    public int getLarge() {
        return large;
    }

}