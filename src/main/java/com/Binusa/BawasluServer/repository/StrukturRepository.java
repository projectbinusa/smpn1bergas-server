package com.Binusa.BawasluServer.repository;

import com.Binusa.BawasluServer.model.Keuangan;
import com.Binusa.BawasluServer.model.Struktur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StrukturRepository extends JpaRepository<Struktur , Long> {
    @Query(value = "SELECT * FROM struktur WHERE jenis_id = :id " , nativeQuery = true)
    Page<Struktur> findByJenisId(Long id , Pageable pageable);

    @Query(value = "SELECT * FROM struktur  WHERE jenis_id = :id ", nativeQuery = true)
    List<Struktur> getAllByJenis(Long id);
}
