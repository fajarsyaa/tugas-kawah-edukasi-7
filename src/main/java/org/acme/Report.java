package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report extends PanacheEntityBase {

    @Id
    @Column(unique = true,nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator")
    public String id;
    public String komoditas;
    @Column(name = "tanggal_panen")
    public LocalDateTime tanggalPanen;
    public Integer harga;
    public Integer total;
    @CreationTimestamp
    public LocalDateTime created_at;
    @UpdateTimestamp
    public LocalDateTime updated_at;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }


    public void setCreated_at() {
        this.created_at = LocalDateTime.now();
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at() {
        this.updated_at = LocalDateTime.now();
    }
}
