package com.Binusa.BawasluServer.DTO;

public class StrukturDTO {
    private String nama;
    private String jabatan;
    private String tugas;
    private Long id_jenis;

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

    public Long getId_jenis() {
        return id_jenis;
    }

    public void setId_jenis(Long id_jenis) {
        this.id_jenis = id_jenis;
    }
}
