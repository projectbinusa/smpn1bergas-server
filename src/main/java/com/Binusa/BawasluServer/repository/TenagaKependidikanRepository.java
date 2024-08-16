package com.Binusa.BawasluServer.repository;

import com.Binusa.BawasluServer.model.Pengumuman;
import com.Binusa.BawasluServer.model.TenagaKependidikan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenagaKependidikanRepository extends JpaRepository<TenagaKependidikan , Long> {
    Page<TenagaKependidikan> findAll(Pageable pageable);
}
