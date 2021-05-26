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

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    //Methode qui affiche la page index
    @GetMapping(path = "/index")
    public String index() {
        return "index";
    }

    // Methode qui liste les patients avec la barre de recherche
    @GetMapping(path = "/patients")
    public String list(Model model,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "5") int size,
                       @RequestParam(name = "motcle", defaultValue = "") String mc) {
        // Page<Patient> pagepatients=patientRepository.findAll(PageRequest.of(page, size));
        Page<Patient> pagepatients=patientRepository.findByNomContains(mc,PageRequest.of(page, size));
        model.addAttribute("listPatients",pagepatients.getContent());
        model.addAttribute("pages", new int[pagepatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("motcle",mc);
        model.addAttribute("size",size);
        return "patients";
    }

    // Methode de suppression
    @GetMapping(path = "/deletePatient")
    public String delete(Long id, String motcle, int page, int size) {
        patientRepository.deleteById(id);
        // apres la suppression on va lui rediriger vers /patients encore une fois pr les listes apres la suppression
        return "redirect:/patients?page=" + page + "&size=" + size + "&motcle=" + motcle;
    }


    //pour acceder a cette methode
    //@GetMapping("/patients")
    //public List<Patient> patients(){
    //  return patientRepository.findAll();
    //}

    //@GetMapping("/patients/{id}")
    //public Patient patients(@PathVariable Long id){
    //  return patientRepository.findById(id).get();
    //}

    //@RequestMapping(value = "/patients", method = RequestMethod.GET)
    //public List<Patient> listPatients2(){
    //  List<Patient> patients = patientRepository.findAll();
    //return patients;
    //}
    @GetMapping("/formPatient")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }

    @PostMapping("/validerPatient")
    public String validerPatient(Model model ,@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        model.addAttribute("patient",patient);
        return "confirmation";
    }
   // modifier un patient
    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id) {
        Patient p=patientRepository.findById(id).get();
        model.addAttribute("patient", p);
        return "editPatient";
    }


}
