package ma.emsi.projet_patient;

import ma.emsi.projet_patient.entities.Patient;
import ma.emsi.projet_patient.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ProjetPatientApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(ProjetPatientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Insertions
        patientRepository.save(new Patient(null,"Asmaa",new Date(),100,false));
        patientRepository.save(new Patient(null,"Meryem",new Date(),50,true));
        patientRepository.save(new Patient(null,"Loubna",new Date(),87,false));
        patientRepository.save(new Patient(null,"Chahida",new Date(),99,false));
        patientRepository.save(new Patient(null,"test",new Date(),87,true));
        patientRepository.save(new Patient(null,"Marwa",new Date(),11,true));
        patientRepository.save(new Patient(null,"Abderrahman",new Date(),98,false));
        patientRepository.save(new Patient(null,"Bennacer",new Date(),97,false));
        patientRepository.save(new Patient(null,"Aya",new Date(),21,true));
        patientRepository.save(new Patient(null,"Salma",new Date(),34,true));
        patientRepository.save(new Patient(null,"Hanane",new Date(),66,false));
        patientRepository.save(new Patient(null,"Abdelkrim",new Date(),23,false));
        patientRepository.save(new Patient(null,"Amina",new Date(),81,false));
        // Afficher Tous
        System.out.println("****************Tous les patients");
        patientRepository.findAll().forEach(p->{
            System.out.println(p.toString());
        });

        //Afficher le 3eme element
        System.out.println("****************Le 3eme element");
        Patient patient=patientRepository.findById(3L).get();
        System.out.println(patient.getNom());

        //Afficher les patients qui contient A dans leurs noms avec pagination
        System.out.println("****************les patients qui contient A dans leurs noms avec pagination ");
        Page<Patient> patients=patientRepository.findByNomContains("a", PageRequest.of(0,2));
        patients.getContent().forEach(p ->{
            System.out.println(p.toString());
        });

        //Afficher les patients qui sont malades
        System.out.println("****************les patients qui sont malade");
        List<Patient> patients2=patientRepository.findByMalade(true);
        patients2.forEach(p ->{
            System.out.println(p.toString());
        } );

        //Afficher les patients qui contient s dans leurs noms et ne sont pas malade
        System.out.println("****************les patients qui contient s dans leurs noms et ne sont pas malade");
        List<Patient> patients3=patientRepository.findByNomContainsAndMalade("s",false);
        patients3.forEach(p ->{
            System.out.println(p.toString());
        } );

        //Supprimer le patient dont l id 5
       // patientRepository.deleteById(5L);
        //System.out.println("****************Tous les patients apres suppression de test");
        //patientRepository.findAll().forEach(p->{
          //  System.out.println(p.toString());
        //});

        //Pagination de la page 0 on affichant que 2 patients
        System.out.println("****************pagination");
        Page<Patient> patientPage=patientRepository.findAll(PageRequest.of(0,2));
        System.out.println("Nombre de pages: "+patientPage.getTotalPages());
        List<Patient> patients4=patientPage.getContent();
        patients4.forEach(p ->{
            System.out.println(p.toString());
        } );
        // patientPage.getContent().forEach(p ->{
        //   System.out.println(p.toString());
        // } );

    }
}
