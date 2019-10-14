package com.gen.kb.knowledgebase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.Doc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KnowledgebaseApplication.class)
public class KnowledgebaseApplicationTests {

@Autowired
FAQService faqService;

/*inserting 3 documents and updating the second and deleting the third */


@Test
public void saveIntoDBtest(){
    DocumentTypeFAQ documentTypeFAQ = new DocumentTypeFAQ();
    documentTypeFAQ.setQuestion("what is this");
    documentTypeFAQ.setAnswers("this is a test case");
    assertEquals(documentTypeFAQ,faqService.saveIntoDB(documentTypeFAQ));

    DocumentTypeFAQ documentTypeFAQ1 = new DocumentTypeFAQ();
    documentTypeFAQ1.setQuestion("what is this");
    documentTypeFAQ1.setAnswers("this is a test case 2");
    assertEquals(documentTypeFAQ1,faqService.saveIntoDB(documentTypeFAQ1));

    DocumentTypeFAQ documentTypeFAQ2 = new DocumentTypeFAQ();
    documentTypeFAQ2.setQuestion("what is this");
    documentTypeFAQ2.setAnswers("this is a test case 2");
    assertEquals(documentTypeFAQ2,faqService.saveIntoDB(documentTypeFAQ2));
}

/* If id is not specified then, it will throw record not found exception */

@Test
public void updateIntoDBtest() throws RecordNotFoundException {
    DocumentTypeFAQ documentTypeFAQ = new DocumentTypeFAQ();
    documentTypeFAQ.setDocID(2);
    documentTypeFAQ.setQuestion("what is this test case ?");
    documentTypeFAQ.setAnswers("this is a update test case");
    assertNotEquals(documentTypeFAQ,faqService.updateFAQ(documentTypeFAQ));
}

/* deleting the third document */
@Test
public void deleteFAQTest() throws RecordNotFoundException {
    faqService.deleteById(3);
}
/*inserted 3 documents, deleted one document, now retreiving the list and getting the size */
@Test
    public void getAllFAQTest(){
    assertEquals(0,faqService.getAllDFAQ().size());
}


}
