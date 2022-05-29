package com.example.application.data.entity;

import javax.persistence.*;
import java.io.ByteArrayInputStream;

@Entity
@Table
public class BugReport {
    @Id
    @SequenceGenerator(
            name = "bugreport_sequence",
            sequenceName = "bugreport_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bugreport_sequence"
    )
    public int bugID;
    public String topic;
    @Column(length = 1000)
    public String description;

    public BugReport() {
    }

    public BugReport(String topic, String description) {
        this.topic = topic;
        this.description = description;
    }

    public int getBugID() {
        return bugID;
    }

    public void setBugID(int bugID) {
        this.bugID = bugID;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
