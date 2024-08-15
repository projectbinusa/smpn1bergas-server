package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.DTO.TabelDipDTO;
import com.Binusa.BawasluServer.model.Prestasi;
import com.Binusa.BawasluServer.model.TabelDip;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.EkstrakurikulerService;
import com.Binusa.BawasluServer.service.PrestasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smpn1bergas/api/prestasi")
@CrossOrigin(origins = "*")
public class PrestasiController {
    @Autowired
    private PrestasiService prestasiService;

    @PostMapping(path = "/add", consumes = "multipart/form-data")
    public ResponseEntity<CommonResponse<Prestasi>> add(Prestasi prestasi, @RequestPart("file") MultipartFile multipartFile) throws SQLException, ClassNotFoundException {
        CommonResponse<Prestasi> response = new CommonResponse<>();
        try {
            Prestasi prestasi1 = prestasiService.add(prestasi, multipartFile);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(prestasi);
            response.setMessage("Prestasi created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create prestasi: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Prestasi>> getAll(){
        return ResponseEntity.ok(prestasiService.getAll());
    }
    @GetMapping("{/id}")
    public ResponseEntity<CommonResponse<Prestasi>> get(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<Prestasi> response = new CommonResponse<>();
        try {
            Prestasi prestasi = prestasiService.getByid(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(prestasi);
            response.setMessage("Prestasi get successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to get prestasi : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/put/{id}", produces = "application/json")
    public ResponseEntity<CommonResponse<Prestasi>> updatePrestasi(@PathVariable("id") Long id, @RequestBody Prestasi prestasi,  @RequestPart("file") MultipartFile multipartFile ) throws SQLException, ClassNotFoundException {
        CommonResponse<Prestasi> response = new CommonResponse<>();
        try {
            Prestasi tabelDip = prestasiService.edit(prestasi, multipartFile, id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(tabelDip);
            response.setMessage("Tabel dip updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update dip : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
