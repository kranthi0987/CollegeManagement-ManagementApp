package app.managementapp.college.com.collegemanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuItem implements Serializable {
    private long id;
    private String name;
    private String snippet;
    private int photo;

    public MenuItem(long id, String date, String name, String snippet, int photo) {
        this.id = id;
        this.name = name;
        this.snippet = snippet;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSnippet() {
        return snippet;
    }

    public int getPhoto() {
        return photo;
    }

}


