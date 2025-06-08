package com.example.DICOM.Controller;

import com.example.DICOM.DTO.DicomImageDTO;
import com.example.DICOM.Service.DicomImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class DicomImageController {

    private final DicomImageService dicomImageService;

    public DicomImageController(DicomImageService dicomImageService) {
        this.dicomImageService = dicomImageService;
    }

    @PostMapping
    public boolean uploadImage(@RequestBody DicomImageDTO dto) {
        return dicomImageService.saveDicomImage(dto);
    }

    @GetMapping
    public List<DicomImageDTO> getAllImages() {
        return dicomImageService.getAllImages();
    }
}

