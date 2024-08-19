package com.Binusa.BawasluServer.service;


import com.Binusa.BawasluServer.model.Berita;
import com.Binusa.BawasluServer.model.CategoryKeuangan;
import com.Binusa.BawasluServer.model.Keuangan;
import com.Binusa.BawasluServer.repository.CategoryKeuanganRepository;
import com.Binusa.BawasluServer.repository.KeuanganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryKeuanganService {
    @Autowired
    private CategoryKeuanganRepository categoryKeuanganRepository;
    @Autowired
    private KeuanganRepository keuanganRepository;

    public CategoryKeuangan save(CategoryKeuangan categoryKeuangan) {
        CategoryKeuangan categoryKeuangan1 = new CategoryKeuangan();
        categoryKeuangan1.setCategory(categoryKeuangan.getCategory());
        return categoryKeuanganRepository.save(categoryKeuangan1);
    }

    public CategoryKeuangan findById(Long id) {
        return categoryKeuanganRepository.findById(id).orElse(null);
    }

    public Page<CategoryKeuangan> findAll(Pageable pageable) {
        return categoryKeuanganRepository.findAll(pageable);
    }

    @Transactional
    public void delete(Long id) {
        CategoryKeuangan categoryKeuangan = categoryKeuanganRepository.findById(id).orElse(null);
        if (categoryKeuangan != null) {
            List<Keuangan> relatedBeritas = keuanganRepository.getAllByCategory(categoryKeuangan.getId());

            for (Keuangan berita : relatedBeritas) {
                keuanganRepository.delete(berita);
            }

            categoryKeuanganRepository.delete(categoryKeuangan);
        }
        categoryKeuanganRepository.delete(categoryKeuangan);
    }

    public CategoryKeuangan update(Long id, CategoryKeuangan categoryKeuangan) {
        CategoryKeuangan categoryKeuangan1 = categoryKeuanganRepository.findById(id).orElse(null);
        categoryKeuangan1.setCategory(categoryKeuangan.getCategory());
        return categoryKeuanganRepository.save(categoryKeuangan1);
    }


}
