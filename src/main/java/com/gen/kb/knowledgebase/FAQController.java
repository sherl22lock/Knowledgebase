package com.gen.kb.knowledgebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faq")
public class FAQController {

    @Autowired
    FAQService faqService;

    /* to get all the faq R of CRUD */
    @GetMapping("getAllFAQ")
    public ResponseEntity<List<DocumentTypeFAQ>> getAllFAQ(){
        List<DocumentTypeFAQ> allFAQ= faqService.getAllDFAQ();
        return new ResponseEntity<List<DocumentTypeFAQ>>(allFAQ ,new HttpHeaders(), HttpStatus.OK);
    }

    /* to insert a faq into the h2 database  C of CRUD*/
    @PostMapping("/saveFAQ")
    public ResponseEntity<DocumentTypeFAQ> insertFaqIntoDB(@RequestBody DocumentTypeFAQ documentTypeFAQ){
        DocumentTypeFAQ doc = faqService.saveIntoDB(documentTypeFAQ);

        return new ResponseEntity<DocumentTypeFAQ>(doc,new HttpHeaders(), HttpStatus.OK);
    }

    /*update a faq U of CRUD*/
    @PostMapping("/updateFAQ")
    public ResponseEntity<DocumentTypeFAQ> updateFAQ(@RequestBody DocumentTypeFAQ documentTypeFAQ) throws RecordNotFoundException {
        DocumentTypeFAQ doc = faqService.updateFAQ(documentTypeFAQ);
        return new ResponseEntity<DocumentTypeFAQ>(doc,new HttpHeaders(), HttpStatus.OK);
    }

    /* delete a faq based on id */
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteFAQ(@PathVariable("id") int id) throws RecordNotFoundException {
        faqService.deleteById(id);
        return HttpStatus.FORBIDDEN;    }

}
