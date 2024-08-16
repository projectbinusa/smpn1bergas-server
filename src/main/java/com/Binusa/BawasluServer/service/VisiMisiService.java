package com.Binusa.BawasluServer.service;

import com.Binusa.BawasluServer.model.VisiMisi;
import com.Binusa.BawasluServer.repository.VisiMisiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisiMisiService {
    @Autowired
    private VisiMisiRepository visiMisiRepository;

    public VisiMisi add(VisiMisi visiMisi){
        return visiMisiRepository.save(visiMisi);
    }
    public VisiMisi getById(Long id){
        return visiMisiRepository.findById(id).orElse(null);
    }
    public List<VisiMisi> getAll(){
        return visiMisiRepository.findAll();
    }
    public VisiMisi edit(VisiMisi visiMisi ,Long id){
        VisiMisi update = visiMisiRepository.findById(id).orElse(null);
        update.setMisi(visiMisi.getMisi());
        update.setVisi(visiMisi.getVisi());
        return visiMisiRepository.save(update);
    }
    public Map<String, Boolean> delete(Long id) {
        try {
            visiMisiRepository.deleteById(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("Deleted", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            return Collections.singletonMap("Deleted", Boolean.FALSE);
        }
    }
}
