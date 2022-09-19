package ma.emsi.patientsmvc.web;


import lombok.AllArgsConstructor;
import ma.emsi.patientsmvc.entities.Contact;
import ma.emsi.patientsmvc.entities.ContactExcelExporter;
import ma.emsi.patientsmvc.repositories.ContactRepository;
import ma.emsi.patientsmvc.sec.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class  ContactController {

    private ContactRepository contactRepository;

    @GetMapping(path = "/user/index")
    public String contacts(Model model,
                           @RequestParam(name="page",defaultValue = "0") int page,
                           @RequestParam(name="keyword",defaultValue = "")String keyword,
                           @RequestParam(name="s",defaultValue = "")String s,
                           @RequestParam(name="size",defaultValue = "10") int size
                          // @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d1

                    ){


        //Page<Contact> pageContacts=contactRepository.findByIntermediaireContains(keyword,PageRequest.of(page,size));
        Page<Contact> pageContacts=contactRepository.findByNomIntermContainsOrTypeContainsOrIntermediaireContains(keyword,keyword,keyword,PageRequest.of(page,size));
      // pageContacts=contactRepository.findByDateBetween(date,date,PageRequest.of(page,size));
        //pageContacts=contactRepository.findAllByDate(d1,d1,PageRequest.of(page,size));

        model.addAttribute("listContacts",pageContacts.getContent());
        model.addAttribute("pages", new int[pageContacts.getTotalPages()]);
        model.addAttribute("keyword",keyword);
       // model.addAttribute("date",d1);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",pageContacts.getTotalPages());
        return "patients";
    }

    @GetMapping("/admin/formPatients")
    public String formPatient(Model model  ){
        model.addAttribute("contact",new Contact());
        return "formPatients";
    }

    @PostMapping(path = "/admin/save")
    public String save(Model model, Contact contact, BindingResult bindingResult,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formPatients";
        contactRepository.save(contact);
        return "redirect:/user/index?page="+page+ "&keyword="+keyword;
    }

    @Autowired
    private UserServices services;

    @GetMapping("/users/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=contact_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Contact> listContacts = services.listAll();

        ContactExcelExporter excelExporter = new ContactExcelExporter(listContacts);

        excelExporter.export(response);
    }




}
