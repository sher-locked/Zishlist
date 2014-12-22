package neo.com.zishlist.database;

/**
 * The data model object.
 * It will be saved in the database in this form.
 */
public class RestaurantDO {

    // table's columns
    private long id;
    private String name;
    private String place;


    // getters & setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }


    @Override
    public String toString() {
        return name;
    }
}
