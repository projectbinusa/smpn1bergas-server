package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.model.TenagaKependidikan;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.TenagaKependidikanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/smpn1bergas/api/tenaga_kependidikan")
@CrossOrigin(origins = "*")
public class TenagaKependidikanController {
    @Autowired
    private TenagaKependidikanService tenagaKependidikanService;
    @PostMapping(path = "/add")
    public ResponseEntity<CommonResponse<TenagaKependidikan>> add(TenagaKependidikan tenaga) {
        CommonResponse<TenagaKependidikan> response = new CommonResponse<>();
        try {
            TenagaKependidikan tenaga1 = tenagaKependidikanService.add(tenaga);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(tenaga1);
            response.setMessage("Tenaga Kependidikan created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create tenaga kependidikan: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<Page<TenagaKependidikan>>> listAllTenagaKependidikan(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        CommonResponse<Page<TenagaKependidikan>> response = new CommonResponse<>();
        try {
            Page<TenagaKependidikan> beritaPage = tenagaKependidikanService.getAll(pageable);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(beritaPage);
            response.setMessage(" Tenaga Kependidikan list retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to retrieve tenaga kependidikan list: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<CommonResponse<TenagaKependidikan>> get(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<TenagaKependidikan> response = new CommonResponse<>();
        try {
            TenagaKependidikan tenaga = tenagaKependidikanService.getById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(tenaga);
            response.setMessage("Tenaga Kependidikan get successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to get tenaga kependidikan : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/put/{id}", produces = "application/json")
    public ResponseEntity<CommonResponse<TenagaKependidikan>> updateTenagaKependidikan(@PathVariable("id") Long id, @RequestBody TenagaKependidikan tenaga ) throws SQLException, ClassNotFoundException {
        CommonResponse<TenagaKependidikan> response = new CommonResponse<>();
        try {
            TenagaKependidikan tabelDip = tenagaKependidikanService.edit(tenaga, id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(tabelDip);
            response.setMessage("Tenaga Kependidikan updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update tenaga kependidikan : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tenagaKependidikanService.delete(id));
    }
}
