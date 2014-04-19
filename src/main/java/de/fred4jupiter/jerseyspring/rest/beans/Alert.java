package de.fred4jupiter.jerseyspring.rest.beans;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement
public class Alert {
    private String id;

    private String title;

    private String description;

    public Alert() {

    }

    public Alert(String title) {
        this(title, null);
    }

    public Alert(String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
