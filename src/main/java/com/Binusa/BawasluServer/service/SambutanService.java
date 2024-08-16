package com.Binusa.BawasluServer.service;

import com.Binusa.BawasluServer.model.Sambutan;
import com.Binusa.BawasluServer.repository.SambutanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SambutanService {
    @Autowired
    private SambutanRepository sambutanRepository;

    public Sambutan add(Sambutan sambutan){
        return sambutanRepository.save(sambutan);
    }
    public Sambutan getById(Long id){
        return sambutanRepository.findById(id).orElse(null);
    }
    public List<Sambutan> getAll(){
        return sambutanRepository.findAll();
    }
    public Sambutan edit(Sambutan sambutan , Long id){
        Sambutan update = sambutanRepository.findById(id).orElse(null);
        update.setNama(sambutan.getNama());
        update.setIsi(sambutan.getIsi());
        update.setNip(sambutan.getNip());
        return sambutanRepository.save(update);
    }
    public Map<String, Boolean> delete(Long id) {
        try {
            sambutanRepository.deleteById(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("Deleted", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            return Collections.singletonMap("Deleted", Boolean.FALSE);
        }
    }
}
