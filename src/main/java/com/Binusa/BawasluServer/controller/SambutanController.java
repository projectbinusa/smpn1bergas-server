package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.model.Sambutan;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.SambutanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/smpn1bergas/api/sambutan")
@CrossOrigin(origins = "*")
public class SambutanController {
    @Autowired
    private SambutanService sambutanService;

    @PostMapping(path = "/add")
    public ResponseEntity<CommonResponse<Sambutan>> add(Sambutan sambutan) throws SQLException, ClassNotFoundException {
        CommonResponse<Sambutan> response = new CommonResponse<>();
        try {
            Sambutan sambutan1 = sambutanService.add(sambutan);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(sambutan1);
            response.setMessage("Sambutan created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create sambutan: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Sambutan>> getAll(){
        return ResponseEntity.ok(sambutanService.getAll());
    }
    @GetMapping("{/id}")
    public ResponseEntity<CommonResponse<Sambutan>> get(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<Sambutan> response = new CommonResponse<>();
        try {
            Sambutan sambutan = sambutanService.getById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(sambutan);
            response.setMessage("Sambutan get successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to get sambutan : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/put/{id}", produces = "application/json")
    public ResponseEntity<CommonResponse<Sambutan>> updateSambutan(@PathVariable("id") Long id, @RequestBody Sambutan sambutan) throws SQLException, ClassNotFoundException {
        CommonResponse<Sambutan> response = new CommonResponse<>();
        try {
            Sambutan tabelDip = sambutanService.edit(sambutan, id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(tabelDip);
            response.setMessage("Sambutan updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update sambutan : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sambutanService.delete(id));
    }
}
