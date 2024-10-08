package com.Binusa.BawasluServer.controller;


import com.Binusa.BawasluServer.model.Sambutan;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.SambutanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<Page<Sambutan>>> listAllSambutan(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        CommonResponse<Page<Sambutan>> response = new CommonResponse<>();
        try {
            Page<Sambutan> beritaPage = sambutanService.getAll(pageable);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(beritaPage);
            response.setMessage(" Sambutan list retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to retrieve guru list: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("{id}")
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
