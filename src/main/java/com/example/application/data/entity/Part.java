package com.example.application.data.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Part {
    @Id
    @SequenceGenerator(
            name = "part_sequence",
            sequenceName = "part_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "part_sequence"
    )
    private int partID;
    private String partName;

    @OneToMany(mappedBy = "part")
    private Set<MoviePersonPartLink> moviePersonPartLink;

    public Part() {
    }

    public Part(int partID, String partName) {
        this.partID = partID;
        this.partName = partName;
    }
    public Part(String partName) {
        this.partName = partName;
    }

    public int getPartID() {
        return partID;
    }


    public void setPartID(int partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
