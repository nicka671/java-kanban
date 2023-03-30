package objects;

import enums.Status;

public class Task
{
    protected String title;
    protected String description;
    protected int id;

    public void setCurrent_status(Status current_status) {
        this.current_status = current_status;
    }

    public Status getCurrent_status() {
        return current_status;
    }

    protected Status current_status;

    public void setId(int id) {
        this.id = id;
    }


}
