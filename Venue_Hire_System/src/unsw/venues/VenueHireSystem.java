/**
 *
 */
package unsw.venues;

import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author Robert Clifton-Everest
 *
 */
public class VenueHireSystem {

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */

    // lists for venue/room/customers booking
    private List<Venue> venue_list;
    private List<Room> room_list;
    private List<Booking> booking_list;

    /**
     * @param venue_list
     * @param room_list
     * @param booking_list
     */

    public VenueHireSystem() {
        this.venue_list = new ArrayList<Venue>();
        this.room_list = new ArrayList<Room>();
        this.booking_list = new ArrayList<Booking>();
    }

    /**
     *
     * @param json command: room/request/change/cancel
     */
    private void processCommand(JSONObject json) {
        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
            String id = json.getString("id");
            LocalDate start = LocalDate.parse(json.getString("start"));
            LocalDate end = LocalDate.parse(json.getString("end"));
            int small = json.getInt("small");
            int medium = json.getInt("medium");
            int large = json.getInt("large");

            JSONObject result = request(id, start, end, small, medium, large);
            System.out.println(result.toString(2));
            break;

        case "change":
            String newid = json.getString("id");
            LocalDate newStart = LocalDate.parse(json.getString("start"));
            LocalDate newEnd = LocalDate.parse(json.getString("end"));
            int newSmall = json.getInt("small");
            int newMedium = json.getInt("medium");
            int newLarge = json.getInt("large");

            JSONObject change_result = change(newid, newStart, newEnd, newSmall, newMedium, newLarge);

            System.out.println(change_result.toString(2));
            break;

        case "cancel":
            String newid2 = json.getString("id");
            // use the cancle function
            cancel(newid2);
            break;

        case "list":
            String venueL = json.getString("venue");
            JSONArray venueList = list(venueL);
            System.out.println(venueList.toString(2));
            break;
        }
    }


    /**
     *
     * @param venue name of the venue
     * @param room  name of the room in the venue
     * @param size  size of the room
     */
    private void addRoom(String venue, String room, String size) {
        // TODO Process the room command
        Venue v = get_Venue(venue);
        // if get_Venue return NULL, there's no 'venue' in the venue_list
        if (v == null) {
            v = new Venue(venue);
            this.venue_list.add(v);
        }
        // 'venue' is in the list, check if the room exist
        // if already exist, ignore, if not, add to the room_list
        if (!room_exist(v, room)) {
            Room r = new Room(room, size);
            v.add_venue_room(r);
            this.room_list.add(r);
        }
    }

    /**
     *
     * @param venue venue name of the venue need to get
     * @return Venue(Null if not exist)
     */
    // to get the venue in the Venue_list by its venue_name
    // if in the string, add room, if not in the string, add the venue in the list then add room
    private Venue get_Venue(String venue){
        for (Venue v: venue_list) {
            if (v.getVenue_name().equals(venue)) {
                return v;
            }
        }
        return null;
    }
    /**
     *
     * @param venue venue name
     * @param room_name room name
     * @return
     */
    // to check if a room in the list,
    // if in the list, ignore the 'room' command request, if not in the list, add it to the list
    private boolean room_exist(Venue venue,String room_name){
        for (Room r: venue.getVenue_room()) {
            if (r.getRoom_name().equals(room_name)) {
                return true;
            }
        }
        // if room not in the list, return false
        return false;
    }


    /**
     *
     * @param id booking request id
     * @param start booking start date
     * @param end booking end date
     * @param small booking request number of small room
     * @param medium booking request number of medium room
     * @param large booking request number of large room
     * @return success/rejected system response
     */
    public JSONObject request(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
        JSONObject result = new JSONObject();

        String status = "rejected";
        Venue finalVenue;
        for (Venue v: venue_list) {
            // check if each venue has enough available rooms to fit the requirements
            List<Room> smallRooms = v.availabRooms("small", start, end);
            List<Room> mediuRomms = v.availabRooms("medium", start, end);
            List<Room> larRooms = v.availabRooms("large", start, end);

            // check if one venue has enough room for the whole request
            // if yes, status change to success
            if (smallRooms.size() >= small &&  mediuRomms.size() >= medium && larRooms.size() >= large) {
                status = "success";
                JSONArray rooms = new JSONArray();
                Booking b = new Booking(id, start, end, small, medium, large);

                // create a room list for all the rooms gonna be booked
                List<Room> ava_Rooms = new ArrayList<Room>();
                if (small > 0) {
                    for (int i = 0; i < small; i++) {
                        ava_Rooms.add(smallRooms.get(i));
                    }
                }

                if (medium > 0) {
                    for (int i = 0; i < medium; i++) {
                        ava_Rooms.add(mediuRomms.get(i));
                    }
                }

                if (large > 0) {
                    for (int i = 0; i < large; i++) {
                        ava_Rooms.add(larRooms.get(i));
                    }
                }

                // for each room add the booking on their booking_list
                for (Room r: ava_Rooms) {
                    r.add_booking(b);
                    b.add_Room(r);
                    rooms.put(r.getRoom_name());
                }

                this.booking_list.add(b);
                result.put("status", status);
                result.put("venue", v.getVenue_name());
                result.put("rooms", rooms);
                return result;
            }
        }

        // if after the whole loop, status still keep "rejected", then no venue available
        result.put("status", status);
        return result;
    }

    /**
     *
     * @param id booking change id
     * @param start new booking start date
     * @param end new booking start date
     * @param small new request number of small room
     * @param medium new request number of medium room
     * @param large new request number of large room
     * @return success/rejected system response
     */
    public JSONObject change(String id, LocalDate start, LocalDate end,
    int small, int medium, int large) {
        // store the one u gonna change with the same request id
        Booking tmpBooking = get_Booking(id);
        String tmpid = tmpBooking.getId();
        LocalDate tmpS = tmpBooking.getStart();
        LocalDate tmpE = tmpBooking.getEnd();
        int tmpSmall = tmpBooking.getSmall();
        int tmpMed = tmpBooking.getMedium();
        int tmpLar = tmpBooking.getLarge();

        // cancel the request with the same request id
        cancel(id);

        // try to request the new one
        JSONObject result = request(id, start, end, small, medium, large);

        // if success then all good
        // if rejected, we need to keep the original request
        // in other word, request the original one again
        if (result.getString("status").equals("rejected")) {
            request(tmpid, tmpS, tmpE, tmpSmall, tmpMed, tmpLar);
        }

        return result;
    }

    /**
     *
     * @param id id of the request be canlled
     */

    // cancel the booking from each room's booking list
    // remove the booking from booking_list
    public void cancel(String id) {
        Booking b = get_Booking(id);
        b.reset();
        booking_list.remove(b);
    }

    /**
     *
     * @param id booking id
     * @return Booking
     */
    // to get the booking in the booking_list by its id
    // if in the string, cancel, if not in the string, ignore it
    private Booking get_Booking(String id){
        for (Booking b: booking_list) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    /**
     *
     * @param roomName room name
     * @return Room
     */
    // to get the room in the room_list by the name
    private Room get_Room(String roomName) {
        for (Room r: room_list) {
            if (r.getRoom_name().equals(roomName)) {
                return r;
            }
        }
        return null;
    }

    /**
     *
     * @param venue name to the venue
     * @return all the rooms and their reservation status in this venue
     */
    // Find out all rooms with status in a specific venue
    public JSONArray list(String venue) {
        JSONArray venue_rooms = new JSONArray();
        Venue v = get_Venue(venue);
        for (Room r: v.getVenue_room()) {
            JSONObject room_info = r.get_info();
            venue_rooms.put(room_info);
        }

        return venue_rooms;
    }


    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }

}
