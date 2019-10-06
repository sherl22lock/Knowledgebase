package com.gen.kb.knowledgebase;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faq")
@Api(value="Spring boot crud for knowledge base")
public class FAQController {

    @Autowired
    FAQService faqService;

    /* to get all the faq R of CRUD */
    @ApiOperation(value = "To get the list of available faq", response = DocumentTypeFAQ.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("getAllFAQ")
    public ResponseEntity<List<DocumentTypeFAQ>> getAllFAQ(){
        List<DocumentTypeFAQ> allFAQ= faqService.getAllDFAQ();
        return new ResponseEntity<List<DocumentTypeFAQ>>(allFAQ ,new HttpHeaders(), HttpStatus.OK);
    }

    /* to insert a faq into the h2 database  C of CRUD*/
    @ApiOperation(value="To insert a faq document into the database")
    @PostMapping("/saveFAQ")
    public ResponseEntity<DocumentTypeFAQ> insertFaqIntoDB(@RequestBody DocumentTypeFAQ documentTypeFAQ){
        DocumentTypeFAQ doc = faqService.saveIntoDB(documentTypeFAQ);

        return new ResponseEntity<DocumentTypeFAQ>(doc,new HttpHeaders(), HttpStatus.OK);
    }

    /*update a faq U of CRUD*/
    @ApiOperation(value =" To update the faq docuement in the database" )
    @PostMapping("/updateFAQ")
    public ResponseEntity<DocumentTypeFAQ> updateFAQ(@RequestBody DocumentTypeFAQ documentTypeFAQ) throws RecordNotFoundException {
        DocumentTypeFAQ doc = faqService.updateFAQ(documentTypeFAQ);
        return new ResponseEntity<DocumentTypeFAQ>(doc,new HttpHeaders(), HttpStatus.OK);
    }

    /* delete a faq based on id */
    @ApiOperation(value = "Delete an FAQ from the database using faq id ")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteFAQ(@PathVariable("id") int id) throws RecordNotFoundException {
        faqService.deleteById(id);
        return HttpStatus.FORBIDDEN;    }

}
