package com.Binusa.BawasluServer.service;

import com.Binusa.BawasluServer.model.JenisStruktur;
import com.Binusa.BawasluServer.model.Struktur;
import com.Binusa.BawasluServer.repository.JenisStrukturRepository;
import com.Binusa.BawasluServer.repository.StrukturRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class JenisStrukturService {
    @Autowired
    private JenisStrukturRepository jenisStrukturRepository;

    @Autowired
    private StrukturRepository strukturRepository;

    public JenisStruktur save(JenisStruktur jenisStruktur) {
        return jenisStrukturRepository.save(jenisStruktur);
    }

    public JenisStruktur findById(Long id) {
        return jenisStrukturRepository.findById(id).orElse(null);
    }

    public Page<JenisStruktur> findAll(Pageable pageable) {
        return jenisStrukturRepository.findAll(pageable);
    }

    @Transactional
    public void delete(Long id) {
        JenisStruktur jenisStruktur = jenisStrukturRepository.findById(id).orElse(null);
        if (jenisStruktur != null) {
            List<Struktur> relatedBeritas = strukturRepository.getAllByJenis(jenisStruktur.getId());

            for (Struktur berita : relatedBeritas) {
                strukturRepository.delete(berita);
            }

            jenisStrukturRepository.delete(jenisStruktur);
        }
        jenisStrukturRepository.delete(jenisStruktur);
    }

    public JenisStruktur update(Long id, JenisStruktur jenisStruktur) {
        JenisStruktur jenisStruktur1 = jenisStrukturRepository.findById(id).orElse(null);
        jenisStruktur1.setNama(jenisStruktur.getNama());
        return jenisStrukturRepository.save(jenisStruktur1);
    }
}
