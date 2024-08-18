package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.model.VisiMisi;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.VisiMisiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/smpn1bergas/api/visiMisi")
@CrossOrigin(origins = "*")
public class VisiMisiController {
    @Autowired
    private VisiMisiService visiMisiService;

    @PostMapping(path = "/add")
    public ResponseEntity<CommonResponse<VisiMisi>> add(VisiMisi visiMisi) throws SQLException, ClassNotFoundException {
        CommonResponse<VisiMisi> response = new CommonResponse<>();
        try {
            VisiMisi visiMisi1 = visiMisiService.add(visiMisi);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(visiMisi1);
            response.setMessage("Visi Misi created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create Visi Misi: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<VisiMisi>> getAll(){
        return ResponseEntity.ok(visiMisiService.getAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<CommonResponse<VisiMisi>> get(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<VisiMisi> response = new CommonResponse<>();
        try {
            VisiMisi visiMisi = visiMisiService.getById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(visiMisi);
            response.setMessage("Visi Misi get successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to get Visi Misi : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/put/{id}", produces = "application/json")
    public ResponseEntity<CommonResponse<VisiMisi>> updateVisiMisi(@PathVariable("id") Long id, @RequestBody VisiMisi visiMisi) throws SQLException, ClassNotFoundException {
        CommonResponse<VisiMisi> response = new CommonResponse<>();
        try {
            VisiMisi tabelDip = visiMisiService.edit(visiMisi, id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(tabelDip);
            response.setMessage("Tabel Visi Misi updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update Visi Misi : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(visiMisiService.delete(id));
    }
}
