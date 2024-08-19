package com.Binusa.BawasluServer.model;

import javax.persistence.*;

@Entity
@Table(name = "struktur")
public class Struktur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_anggota")
    private String nama;

    @Column(name = "jabatan")
    private String jabatan;

    @Column(name = "tugas_anggota")
    private String tugas;

    @Lob
    @Column(name = "foto_anggota")
    private String foto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jenis_id")
    private JenisStruktur jenisStruktur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getTugas() {
        return tugas;
    }

    public void setTugas(String tugas) {
        this.tugas = tugas;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public JenisStruktur getJenisStruktur() {
        return jenisStruktur;
    }

    public void setJenisStruktur(JenisStruktur jenisStruktur) {
        this.jenisStruktur = jenisStruktur;
    }
}
