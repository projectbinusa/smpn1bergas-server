package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.model.JenisStruktur;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.JenisStrukturService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/smpn1bergas/api/jenis_struktur")
@CrossOrigin(origins = "*")
public class JenisStrukturcontroller {
    @Autowired
    private JenisStrukturService jenisStrukturService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CommonResponse<JenisStruktur>> createJenisStruktur(@RequestBody JenisStruktur jenisStruktur) throws SQLException, ClassNotFoundException {
        CommonResponse<JenisStruktur> response = new CommonResponse<>();
        try {
            JenisStruktur keuangan1 = jenisStrukturService.save(jenisStruktur);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(keuangan1);
            response.setMessage("Category keuangan created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create category keuangan: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CommonResponse<Page<JenisStruktur>>> listAlJenisStruktur(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdDate") String sort,
            @RequestParam(defaultValue = "asc") String direction) throws SQLException, ClassNotFoundException {
        CommonResponse<Page<JenisStruktur>> response = new CommonResponse<>();
        try {
            // Membuat objek Pageable untuk digunakan dalam repository.findAll
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));

            // Menggunakan service.findAll dengan pageable
            Page<JenisStruktur> jenisStrukturs = jenisStrukturService.findAll(pageable);

            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(jenisStrukturs);
            response.setMessage("Category keuangan list retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to retrieve category keuangan list: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/put/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<CommonResponse<JenisStruktur>> updateJenisStruktur(@PathVariable("id") Long id, @RequestBody JenisStruktur jenisStruktur) throws SQLException, ClassNotFoundException {
        CommonResponse<JenisStruktur> response = new CommonResponse<>();
        try {
            // Update keuangan here...

            JenisStruktur jenisStruktur1 = jenisStrukturService.update(id, jenisStruktur);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(jenisStruktur1);
            response.setMessage("Category keuangan updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update category keuangan: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CommonResponse<String>> deletecategorykeuangan(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<String> response = new CommonResponse<>();
        try {
            jenisStrukturService.delete(id);
            response.setStatus("success");
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setData("Category keuangan deleted successfully.");
            response.setMessage("Category keuangan with id " + id + " deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to delete category keuangan: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse<JenisStruktur>> get(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<JenisStruktur> response = new CommonResponse<>();
        try {
            JenisStruktur jenisStruktur = jenisStrukturService.findById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(jenisStruktur);
            response.setMessage("Category keuangan get successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to get category keuangan: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
