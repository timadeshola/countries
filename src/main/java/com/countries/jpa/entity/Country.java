package com.countries.jpa.entity;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "COUNTRIES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Audited
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "continent")
    @NonNull
    private String continent;

    @Column(name = "code")
    @NonNull
    private String code;

    @Column(name = "date_created")
    private Timestamp dateCreated;

    @Column(name = "date_updated")
    private Timestamp dateUpdated;

    @Column(name = "date_deleted")
    private Timestamp dateDeleted;

    @PrePersist
    protected void onCreate() {
        setDateCreated(new Timestamp(System.currentTimeMillis()));
    }

    @PreUpdate
    protected void onUpdate() {
        setDateUpdated(new Timestamp(System.currentTimeMillis()));
    }

    @PreRemove
    protected void onDelete() {
        setDateDeleted(new Timestamp(System.currentTimeMillis()));
    }

}
