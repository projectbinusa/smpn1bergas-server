package com.Binusa.BawasluServer.service;

import com.Binusa.BawasluServer.DTO.KeuanganDTO;
import com.Binusa.BawasluServer.model.Berita;
import com.Binusa.BawasluServer.model.Keuangan;
import com.Binusa.BawasluServer.model.Tags;
import com.Binusa.BawasluServer.repository.CategoryKeuanganRepository;
import com.Binusa.BawasluServer.repository.KeuanganRepository;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

@Service
public class KeuanganService {
    @Autowired
    private KeuanganRepository keuanganRepository;

    @Autowired
    private CategoryKeuanganRepository categoryKeuanganRepository;

    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/bawaslu-a6bd2.appspot.com/o/%s?alt=media";

    public Keuangan add(KeuanganDTO keuangan, MultipartFile multipartFile) throws Exception {
        Keuangan newKeuangan = new Keuangan();
        String image = imageConverter(multipartFile);
        newKeuangan.setJudul(keuangan.getJudul());
        newKeuangan.setIsi(keuangan.getIsi());
        newKeuangan.setFotoJudul(image);
        newKeuangan.setCategoryKeuangan(categoryKeuanganRepository.findById(keuangan.getCategory_id()).orElse(null));

        return keuanganRepository.save(newKeuangan);
    }

    public Keuangan findById(Long id) {
        return keuanganRepository.findById(id).orElse(null);
    }

    public Page<Keuangan> getAll(Pageable pageable) {
        return keuanganRepository.findAll(pageable);
    }

    public Map<String, Boolean> delete(Long id) {
        try {
            keuanganRepository.deleteById(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("Deleted", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            return Collections.singletonMap("Deleted", Boolean.FALSE);
        }
    }

    public Keuangan edit(Long id, KeuanganDTO keuanganDTO, MultipartFile multipartFile) throws Exception {
        Keuangan keuangan = keuanganRepository.findById(id).orElse(null);
        String image = imageConverter(multipartFile);
        keuangan.setJudul(keuanganDTO.getJudul());
        keuangan.setIsi(keuanganDTO.getIsi());
        keuangan.setFotoJudul(image);
        keuangan.setCategoryKeuangan(categoryKeuanganRepository.findById(keuanganDTO.getCategory_id()).orElse(null));

        return keuanganRepository.save(keuangan);
    }

    public Page<Keuangan> getByCategory(Long categoryId, Pageable pageable) {
        return keuanganRepository.findByCategoryKeuangan_Id(categoryId, pageable);
    }

    private String imageConverter(MultipartFile multipartFile) throws Exception {
        try {
            String fileName = getExtension(multipartFile.getOriginalFilename());
            File file = convertFile(multipartFile, fileName);
            var RESPONSE_URL = uploadFile(file, fileName);
            file.delete();
            return RESPONSE_URL;
        } catch (Exception e) {
            e.getStackTrace();
            throw new Exception("Error upload file: " + e.getMessage());
        }
    }

    private String getExtension(String fileName) {
        return  fileName.split("\\.")[0];
    }

    private File convertFile(MultipartFile multipartFile, String fileName) throws IOException {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        System.out.println("File size: " + file.length());
        return file;
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("bawaslu-a6bd2.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("bawaslu-firebase.json");
        Credentials credentials = GoogleCredentials.fromStream(serviceAccount);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
}
