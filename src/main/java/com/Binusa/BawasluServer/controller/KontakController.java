package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.model.Kontak;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.KontakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/smpn1bergas/api/kontak")
@CrossOrigin(origins = "*")
public class KontakController {
    @Autowired
    private KontakService kontakService;

    @PostMapping(path = "/add")
    public ResponseEntity<CommonResponse<Kontak>> add(Kontak kontak) throws SQLException, ClassNotFoundException {
        CommonResponse<Kontak> response = new CommonResponse<>();
        try {
            Kontak kontak1 = kontakService.add(kontak);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(kontak1);
            response.setMessage("Kontak created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create kontak: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Kontak>> getAll(){
        return ResponseEntity.ok(kontakService.getAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<CommonResponse<Kontak>> get(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<Kontak> response = new CommonResponse<>();
        try {
            Kontak kontak = kontakService.getById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(kontak);
            response.setMessage("Kontak get successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to get kontak : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/put/{id}", produces = "application/json")
    public ResponseEntity<CommonResponse<Kontak>> updateKontak(@PathVariable("id") Long id, @RequestBody Kontak kontak) throws SQLException, ClassNotFoundException {
        CommonResponse<Kontak> response = new CommonResponse<>();
        try {
            Kontak tabelDip = kontakService.edit(kontak, id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(tabelDip);
            response.setMessage("Kontak updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update kontak : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(kontakService.delete(id));
    }
}
