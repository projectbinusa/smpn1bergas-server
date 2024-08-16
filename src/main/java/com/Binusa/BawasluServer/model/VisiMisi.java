package com.Binusa.BawasluServer.model;

import javax.persistence.*;

@Entity
@Table(name = "visi_misi")
public class VisiMisi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visi")
    private String visi;

    @Column(name = "misi")
    private String misi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisi() {
        return visi;
    }

    public void setVisi(String visi) {
        this.visi = visi;
    }

    public String getMisi() {
        return misi;
    }

    public void setMisi(String misi) {
        this.misi = misi;
    }
}
