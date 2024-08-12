package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.DTO.PermohonanInformasiDTO;
import com.Binusa.BawasluServer.model.PermohonanInformasi;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.PermohonanInformasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bawaslu/api/permohonan-informasi")
@CrossOrigin(origins = "*")
public class PermohonanInformasiController {
    @Autowired
    private PermohonanInformasiService permohonanInformasiService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<CommonResponse<PermohonanInformasi>> createPermohonanInformasi(PermohonanInformasiDTO permohonanInformasiDTO, @RequestPart("fotoIdentitas") MultipartFile multipartFile) throws SQLException, ClassNotFoundException {
        CommonResponse<PermohonanInformasi> response = new CommonResponse<>();
        try {
            PermohonanInformasi permohonanInformasi = permohonanInformasiService.save(permohonanInformasiDTO, multipartFile);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(permohonanInformasi);
            response.setMessage("Permohonan informasi created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create permohonan informasi : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CommonResponse<List<PermohonanInformasi>>> listAllPermohonanInformasi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        Pageable pageable;
        if (sortOrder.equals("asc")) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        }        CommonResponse<List<PermohonanInformasi>> response = new CommonResponse<>();
        try {
            Page<PermohonanInformasi> informasiPage = permohonanInformasiService.findAll(pageable);

            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(informasiPage.getContent());
            response.setMessage("Permohonan informasi list retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to retrieve permohonan informasi list: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "multipart/form-data")
    public ResponseEntity<CommonResponse<PermohonanInformasi>> updatePermohonanInformasi(@PathVariable("id") Long id, PermohonanInformasiDTO permohonanInformasiDTO, @RequestPart("fotoIdentitas") MultipartFile multipartFile) throws SQLException, ClassNotFoundException {
        CommonResponse<PermohonanInformasi> response = new CommonResponse<>();
        try {
            Optional<PermohonanInformasi> currentPermohonanInformasi = permohonanInformasiService.findById(id);

            if (!currentPermohonanInformasi.isPresent()) {
                response.setStatus("error");
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setData(null);
                response.setMessage("Permohonan informasi with id " + id + " not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

//            // Update berita here...

            PermohonanInformasi permohonanInformasi = permohonanInformasiService.update(id, permohonanInformasiDTO, multipartFile);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(permohonanInformasi);
            response.setMessage("Permohonan informasi updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update permohonan informasi: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CommonResponse<String>> deletePermohonanInformasi(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<String> response = new CommonResponse<>();
        try {
            permohonanInformasiService.delete(id);
            response.setStatus("success");
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setData("Permohonan informasi deleted successfully.");
            response.setMessage("Permohonan informasi with id " + id + " deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to delete permohonan infomasi: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse<PermohonanInformasi>> get(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<PermohonanInformasi> response = new CommonResponse<>();
        try {
            PermohonanInformasi permohonanInformasi = permohonanInformasiService.getPermohonanKeberatanById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(permohonanInformasi);
            response.setMessage("Permohonan informasi get successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to get permohonan informasi : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
