package com.Binusa.BawasluServer.service;

import com.Binusa.BawasluServer.model.Kontak;
import com.Binusa.BawasluServer.repository.KontakRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KontakService {
    @Autowired
    private KontakRespository kontakRespository;

    public Kontak add(Kontak kontak){
        return kontakRespository.save(kontak);
    }
    public Kontak getById(Long id){
        return kontakRespository.findById(id).orElse(null);
    }
    public List<Kontak> getAll(){
        return kontakRespository.findAll();
    }
    public Kontak edit(Kontak kontak , Long id){
        Kontak update = kontakRespository.findById(id).orElse(null);
        update.setAddress(kontak.getAddress());
        update.setEmail(kontak.getEmail());
        update.setFax(kontak.getFax());
        update.setPhone(kontak.getPhone());
        return kontakRespository.save(update);
    }
    public Map<String, Boolean> delete(Long id) {
        try {
            kontakRespository.deleteById(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("Deleted", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            return Collections.singletonMap("Deleted", Boolean.FALSE);
        }
    }

}
