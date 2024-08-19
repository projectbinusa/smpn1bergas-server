package com.Binusa.BawasluServer.model;

import javax.persistence.*;

@Entity
@Table(name = "sarana")
public class Sarana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_sarana")
    private String nama_sarana;

    @Column(name = "deskripsi")
    private String deskripsi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama_sarana() {
        return nama_sarana;
    }

    public void setNama_sarana(String nama_sarana) {
        this.nama_sarana = nama_sarana;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
