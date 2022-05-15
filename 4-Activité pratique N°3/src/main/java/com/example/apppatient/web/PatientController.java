package com.example.apppatient.web;

import com.example.apppatient.entities.Patient;
import com.example.apppatient.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping("/user/index")
    public  String patients(Model model,
                            @RequestParam(name = "page" ,defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "5") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyWord){
        Page<Patient> pagePatients=patientRepository.findByNomContains(keyWord,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients.getContent());
        List<Integer> pages=new ArrayList<>();
        int totalPages=pagePatients.getTotalPages();
        if(totalPages<=7){
            for (int i=0;i<pagePatients.getTotalPages();i++)
                pages.add(i);
        }else if(page<totalPages){
            int pi=0;
            int pf=page;

            if(page-3<0){
                pi=0;
                pf=page+3+(3-page);
            }else if(page+3>totalPages-1){
                pf=totalPages-1;
                pi=page-6+(pf-page);
            }else {
                pi=page-3;
                pf=page+3;
            }
            for (int n=pi; n<=pf;n++){
                pages.add(n);
            }
        }

        model.addAttribute("pages",pages);
        model.addAttribute("keyWord",keyWord);
        model.addAttribute("currentPage",page);
        return  "patients";
    }



    @GetMapping("/admin/delete")
    public  String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public  String home(){
        return "home";
    }

    @GetMapping("/home")
    public  String index(){
        return "redirect:/";
    }


    @GetMapping("/user/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }


    @GetMapping("/admin/formPatients")
    public String formPatients(Model model,
                               @RequestParam(name = "page" ,defaultValue = "0") int page,
                               @RequestParam(name = "keyword",defaultValue = "") String keyword){
        model.addAttribute("patient",new Patient());

        model.addAttribute("keyword",keyword);
        model.addAttribute("currentPage",page);
        return "formPatients";
    }

    @PostMapping("/admin/save")
    public String save(Model model,@Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(name = "page" ,defaultValue = "0") int page,
                       @RequestParam(name = "keyword",defaultValue = "") String keyword){

        if(bindingResult.hasErrors()){

            return "formPatients";
        }

        patientRepository.save(patient);

        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/admin/editPatient")
    public  String editPatient(Model model,Long id, String keyword, int page){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient==null) throw  new RuntimeException("Patient introuvable");

        model.addAttribute("patient",patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("currentPage",page);
        return "editPatient";
    }



}
