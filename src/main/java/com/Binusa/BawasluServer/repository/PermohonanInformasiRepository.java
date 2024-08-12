package com.Binusa.BawasluServer.repository;

import com.Binusa.BawasluServer.model.PermohonanInformasi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermohonanInformasiRepository extends CrudRepository<PermohonanInformasi, Integer> {
    PermohonanInformasi findById(long id);
    Page<PermohonanInformasi> findAll(Pageable pageable);
    Page<PermohonanInformasi> findAllByOrderByUpdatedDateDesc(Pageable pageable);
}
