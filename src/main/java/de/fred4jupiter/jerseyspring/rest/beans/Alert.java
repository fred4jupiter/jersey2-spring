package de.fred4jupiter.jerseyspring.rest.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Alert {

    private String id;

    private String title;

    private String description;

    private String owner;

    public Alert() {
        // needed for mashalling/unmarshalling
    }

    public Alert(String title) {
        this(title, null);
    }

    public Alert(String title, String owner) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.owner = owner;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
