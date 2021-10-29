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


public class Venue {
    private String venue_name;
    private List<Room> venue_room;

    /**
     *
     * @param venue_name venue name
     */
    // venue constructor with the venue(String) and an empty room list
    public Venue(String venue_name) {
        this.venue_name = venue_name;
        this.venue_room = new ArrayList<Room>();
    }

    /**
     *
     * @param room add the room in the venue's room list
     */
    // add the room in the venue_room list of a venue
    public void add_venue_room(Room room) {
        venue_room.add(room);
    }

    /**
     *
     * @return venue name
     */
    public String getVenue_name() {
        return venue_name;
    }

    /**
     *
     * @return room list of the venue
     */
    public List<Room> getVenue_room() {
        return venue_room;
    }

    /**
     *
     * @param size size of the room request
     * @param start booking start date
     * @param end booking end date
     * @return list of avaiable rooms of the specific size
     */
    public List<Room> availabRooms(String size, LocalDate start, LocalDate end) {
        List<Room> ava_Rooms = new ArrayList<Room>();
        for (Room r: venue_room) {
            if (r.getSize().equals(size) && !r.is_reserved(start, end)) {
                ava_Rooms.add(r);
            }
        }
        return ava_Rooms;
    }

}