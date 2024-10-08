package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.DTO.KeuanganDTO;
import com.Binusa.BawasluServer.model.Keuangan;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.KeuanganService;
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
import java.util.Optional;

@RestController
@RequestMapping("/smpn1bergas/api/keuangan")
@CrossOrigin(origins = "*")
public class KeuanganController {
    @Autowired
    private KeuanganService keuanganService;


    @PostMapping(path = "/add", consumes = "multipart/form-data")
    public ResponseEntity<CommonResponse<Keuangan>> add(KeuanganDTO keuangan, @RequestPart("file") MultipartFile multipartFile) throws SQLException, ClassNotFoundException {
        CommonResponse<Keuangan> response = new CommonResponse<>();
        try {
            Keuangan keuangan1 = keuanganService.add(keuangan, multipartFile);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(keuangan1);
            response.setMessage("Keuangan created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create keuangan: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<Page<Keuangan>>> listAllKeuangan(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        CommonResponse<Page<Keuangan>> response = new CommonResponse<>();
        try {
            Page<Keuangan> beritaPage = keuanganService.getAll(pageable);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(beritaPage);
            response.setMessage(" Keuangan list retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to retrieve keuangan list: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/category")
    public ResponseEntity<CommonResponse<Page<Keuangan>>> getBycategory(
            @RequestParam(name = "id_category") Long idKategory,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        CommonResponse<Page<Keuangan>> response = new CommonResponse<>();
        try {
            Page<Keuangan> beritaPage = keuanganService.getByCategory(idKategory, pageable);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(beritaPage);
            response.setMessage(" Keuangan list retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to retrieve keuangan list: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("{/id}")
    public ResponseEntity<CommonResponse<Keuangan>> get(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<Keuangan> response = new CommonResponse<>();
        try {
            Keuangan keuangan = keuanganService.findById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(keuangan);
            response.setMessage("Keuangan get successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to get keuangan : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/put/{id}", consumes = "multipart/form-data")
    public ResponseEntity<CommonResponse<Keuangan>> updateKeuangan(@PathVariable("id") Long id, KeuanganDTO keuangan,  @RequestPart("file") MultipartFile multipartFile ) throws SQLException, ClassNotFoundException {
        CommonResponse<Keuangan> response = new CommonResponse<>();
        try {
            Keuangan tabelDip = keuanganService.edit(id, keuangan, multipartFile);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(tabelDip);
            response.setMessage("Keuangan updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update keuangan : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(keuanganService.delete(id));
    }
}
