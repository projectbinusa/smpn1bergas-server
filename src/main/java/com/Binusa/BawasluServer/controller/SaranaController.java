package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.model.Sarana;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.SaranaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/smpn1bergas/api/sarana")
@CrossOrigin(origins = "*")
public class SaranaController {
    @Autowired
    private SaranaService saranaService;

    @PostMapping(path = "/add")
    public ResponseEntity<CommonResponse<Sarana>> add(Sarana sarana) throws SQLException, ClassNotFoundException {
        CommonResponse<Sarana> response = new CommonResponse<>();
        try {
            Sarana sarana1 = saranaService.add(sarana);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(sarana1);
            response.setMessage("Sarana created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create sarana: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<Page<Sarana>>> listAllSarana(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        CommonResponse<Page<Sarana>> response = new CommonResponse<>();
        try {
            Page<Sarana> beritaPage = saranaService.getAll(pageable);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(beritaPage);
            response.setMessage(" Sarana list retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to retrieve sarana list: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("{/id}")
    public ResponseEntity<CommonResponse<Sarana>> get(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<Sarana> response = new CommonResponse<>();
        try {
            Sarana sarana = saranaService.getById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(sarana);
            response.setMessage("Sarana get successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to get sarana : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/put/{id}", produces = "application/json")
    public ResponseEntity<CommonResponse<Sarana>> updateSarana(@PathVariable("id") Long id, @RequestBody Sarana sarana) throws SQLException, ClassNotFoundException {
        CommonResponse<Sarana> response = new CommonResponse<>();
        try {
            Sarana tabelDip = saranaService.edit(sarana, id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(tabelDip);
            response.setMessage(" Sarana updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update sarana : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(saranaService.delete(id));
    }
}
