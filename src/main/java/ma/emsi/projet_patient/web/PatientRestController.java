package ma.emsi.projet_patient.web;

import ma.emsi.projet_patient.entities.Patient;
import ma.emsi.projet_patient.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PatientRestController {
    @Autowired
    private PatientRepository patientRepository;


    @GetMapping("/listPatients")
    public List<Patient> list(){
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")

    public Patient getOne(@PathVariable Long id){
        return patientRepository.findById(id).get();
    }

}
