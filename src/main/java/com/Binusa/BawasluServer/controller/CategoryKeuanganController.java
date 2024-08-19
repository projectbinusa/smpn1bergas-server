package com.Binusa.BawasluServer.controller;


import com.Binusa.BawasluServer.model.CategoryKeuangan;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.CategoryKeuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smpn1bergas/api/category_keuangan")
@CrossOrigin(origins = "*")
public class CategoryKeuanganController {
    @Autowired
    private CategoryKeuanganService categoryKeuanganService;
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CommonResponse<CategoryKeuangan>> createCategoryKeuangan(@RequestBody CategoryKeuangan categoryBerita) throws SQLException, ClassNotFoundException {
        CommonResponse<CategoryKeuangan> response = new CommonResponse<>();
        try {
            CategoryKeuangan keuangan1 = categoryKeuanganService.save(categoryBerita);
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
    public ResponseEntity<CommonResponse<Page<CategoryKeuangan>>> listAlCategoryKeuangan(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdDate") String sort,
            @RequestParam(defaultValue = "asc") String direction) throws SQLException, ClassNotFoundException {
        CommonResponse<Page<CategoryKeuangan>> response = new CommonResponse<>();
        try {
            // Membuat objek Pageable untuk digunakan dalam repository.findAll
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));

            // Menggunakan service.findAll dengan pageable
            Page<CategoryKeuangan> categoryBeritas = categoryKeuanganService.findAll(pageable);

            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(categoryBeritas);
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
    public ResponseEntity<CommonResponse<CategoryKeuangan>> updateCategoryKeuangan(@PathVariable("id") Long id, @RequestBody CategoryKeuangan categoryBerita) throws SQLException, ClassNotFoundException {
        CommonResponse<CategoryKeuangan> response = new CommonResponse<>();
        try {
            // Update keuangan here...

            CategoryKeuangan categoryBerita1 = categoryKeuanganService.update(id, categoryBerita);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(categoryBerita1);
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
            categoryKeuanganService.delete(id);
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
    public ResponseEntity<CommonResponse<CategoryKeuangan>> get(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<CategoryKeuangan> response = new CommonResponse<>();
        try {
            CategoryKeuangan categoryBerita = categoryKeuanganService.findById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(categoryBerita);
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
