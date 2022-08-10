package core.threading.transactions.entities;

/**
 * @author Aleksey Morozov
 * @since 05.08.2022
 */
public class BaseEntity {
    private int id;

    public BaseEntity() {
    }

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
