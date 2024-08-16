package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.model.Ekstrakurikuler;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.EkstrakurikulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/smpn1bergas/api/ekstrakulikuler")
@CrossOrigin(origins = "*")
public class EkstrakulikulerController {
    @Autowired
    private EkstrakurikulerService ekstrakurikulerService;

    @PostMapping(path = "/add")
    public ResponseEntity<CommonResponse<Ekstrakurikuler>> add(Ekstrakurikuler ekstrakurikuler) throws SQLException, ClassNotFoundException {
        CommonResponse<Ekstrakurikuler> response = new CommonResponse<>();
        try {
            Ekstrakurikuler ekstrakurikuler1 = ekstrakurikulerService.add(ekstrakurikuler);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(ekstrakurikuler1);
            response.setMessage("Ekstrakurikuler created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create ekstrakurikuler: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Ekstrakurikuler>> getAll(){
        return ResponseEntity.ok(ekstrakurikulerService.getAll());
    }
    @GetMapping("{/id}")
    public ResponseEntity<CommonResponse<Ekstrakurikuler>> get(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<Ekstrakurikuler> response = new CommonResponse<>();
        try {
            Ekstrakurikuler ekstrakurikuler = ekstrakurikulerService.getById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(ekstrakurikuler);
            response.setMessage("Ekstrakurikuler get successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to get ekstrakurikuler : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/put/{id}", produces = "application/json")
    public ResponseEntity<CommonResponse<Ekstrakurikuler>> updateEkstrakurikuler(@PathVariable("id") Long id, @RequestBody Ekstrakurikuler ekstrakurikuler) throws SQLException, ClassNotFoundException {
        CommonResponse<Ekstrakurikuler> response = new CommonResponse<>();
        try {
            Ekstrakurikuler tabelDip = ekstrakurikulerService.edit(ekstrakurikuler, id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(tabelDip);
            response.setMessage("Ekstrakurikuler updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update ekstrakurikuler : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ekstrakurikulerService.delete(id));
    }
}
