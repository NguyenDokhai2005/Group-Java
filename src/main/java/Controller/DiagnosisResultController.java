package Controller;

import Entity.DiagnosisResult;
import Service.DiagnosisResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosis-results")
public class DiagnosisResultController {

    private final DiagnosisResultService diagnosisResultService;

    public DiagnosisResultController(DiagnosisResultService diagnosisResultService) {
        this.diagnosisResultService = diagnosisResultService;
    }

    @GetMapping
    public List<DiagnosisResult> getAll() {
        return diagnosisResultService.getAllResults();
    }

    @GetMapping("/{id}")
    public DiagnosisResult getById(@PathVariable Long id) {
        return diagnosisResultService.getResultById(id).orElse(null);
    }

    @GetMapping("/patient/{patientId}")
    public List<DiagnosisResult> getByPatientId(@PathVariable Long patientId) {
        return diagnosisResultService.getResultsByPatientId(patientId);
    }

    @GetMapping("/image/{imgId}")
    public List<DiagnosisResult> getByImageId(@PathVariable Long imgId) {
        return diagnosisResultService.getResultsByImageId(imgId);
    }

    @PostMapping
    public DiagnosisResult create(@RequestBody DiagnosisResult result) {
        return diagnosisResultService.saveResult(result);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        diagnosisResultService.deleteResult(id);
    }
}
