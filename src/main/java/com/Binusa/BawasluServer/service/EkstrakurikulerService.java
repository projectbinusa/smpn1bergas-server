package com.Binusa.BawasluServer.service;

import com.Binusa.BawasluServer.model.Ekstrakurikuler;
import com.Binusa.BawasluServer.repository.EkstrakurikulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EkstrakurikulerService {
    @Autowired
    private EkstrakurikulerRepository ekstrakurikulerRepository;

    public Ekstrakurikuler add(Ekstrakurikuler ekstrakurikuler){
        ekstrakurikuler.setName(ekstrakurikuler.getName());
        return ekstrakurikulerRepository.save(ekstrakurikuler);
    }
    public Ekstrakurikuler getById(Long id){
        return ekstrakurikulerRepository.findById(id).orElse(null);
    }
    public List<Ekstrakurikuler> getAll(){
        return ekstrakurikulerRepository.findAll();
    }
    public Ekstrakurikuler edit(Ekstrakurikuler ekstrakurikuler , Long id){
        Ekstrakurikuler update = ekstrakurikulerRepository.findById(id).orElse(null);
        update.setName(ekstrakurikuler.getName());
        return ekstrakurikulerRepository.save(update);
    }
    public Map<String, Boolean> delete(Long id) {
        try {
            ekstrakurikulerRepository.deleteById(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("Deleted", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            return Collections.singletonMap("Deleted", Boolean.FALSE);
        }
    }
}
