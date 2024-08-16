package com.Binusa.BawasluServer.repository;

import com.Binusa.BawasluServer.model.Guru;
import com.Binusa.BawasluServer.model.Pengumuman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuruRepository extends JpaRepository<Guru, Long> {
    Page<Guru> findAll(Pageable pageable);
}
