package de.fred4jupiter.jerseyspring.rest.beans;

import de.fred4jupiter.jerseyspring.rest.AlertResource;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

@XmlRootElement
public class Alert {

    @InjectLinks({
            @InjectLink(resource = AlertResource.class, rel = "self", method = "read"),
            @InjectLink(resource = AlertResource.class, rel = "edit", method = "update"),
            @InjectLink(resource = AlertResource.class, rel = "delete", method = "delete"),
            @InjectLink(resource = AlertResource.class, rel = "list", method = "list")
    })
    private List<Link> links;

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
