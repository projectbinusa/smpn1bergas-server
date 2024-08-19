package com.Binusa.BawasluServer.repository;

import com.Binusa.BawasluServer.model.Berita;
import com.Binusa.BawasluServer.model.Keuangan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeuanganRepository extends JpaRepository<Keuangan , Long > {
    Page<Keuangan> findByCategoryKeuangan_Id(Long categoryId, Pageable pageable);
    @Query(value = "SELECT * FROM keuangan  WHERE category_id = :categoryId", nativeQuery = true)
    List<Keuangan> getAllByCategory(Long categoryId);
}
