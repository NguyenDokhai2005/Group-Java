package com.example.DICOM.Controller;

import com.example.DICOM.Entity.DicomImage;
import com.example.DICOM.Service.DicomImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
@CrossOrigin("*")
public class DicomImageController {

    @Autowired
    private DicomImageService dicomImageService;

    @PostMapping("/create")
    public DicomImage uploadDicomImage(@RequestBody DicomImage dicomImage) {
        return dicomImageService.createDicomImage(dicomImage);
    }
}
