package com.Binusa.BawasluServer.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "keuangan")
public class Keuangan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "judul_keuangan")
    private String judul;

    @Lob
    @Column(name = "foto_judul")
    private String fotoJudul;

    @Lob
    @Column(name = "isi")
    private String isi;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryKeuangan categoryKeuangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getFotoJudul() {
        return fotoJudul;
    }

    public void setFotoJudul(String fotoJudul) {
        this.fotoJudul = fotoJudul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }


    public CategoryKeuangan getCategoryKeuangan() {
        return categoryKeuangan;
    }

    public void setCategoryKeuangan(CategoryKeuangan categoryKeuangan) {
        this.categoryKeuangan = categoryKeuangan;
    }
}
