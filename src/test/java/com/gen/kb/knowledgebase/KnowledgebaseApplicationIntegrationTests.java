package com.gen.kb.knowledgebase;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.mockito.Mockito.verify;




@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class KnowledgebaseApplicationIntegrationTests {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate tTemplate;

    @MockBean
    private FAQRepository faqRepository;

    @Before
    public void init() {
       DocumentTypeFAQ documentTypeFAQ = new DocumentTypeFAQ();
       documentTypeFAQ.setDocID(1);
       documentTypeFAQ.setQuestion("what is this ?");
       documentTypeFAQ.setAnswers("this is a test case for integration tests");
        when(faqRepository.findById(1)).thenReturn(Optional.of(documentTypeFAQ));
    }

    @Test
    public void findAllFAQ() throws Exception {
        List<DocumentTypeFAQ> documentTypeFAQList = new ArrayList<DocumentTypeFAQ>();

        /* creating 2 docusmnets type faq and add it into the list */

        DocumentTypeFAQ documentTypeFAQ1 = new DocumentTypeFAQ();
        documentTypeFAQ1.setDocID(1);
        documentTypeFAQ1.setQuestion("what is this ?");
        documentTypeFAQ1.setAnswers("this is a test case for integration tests");

        DocumentTypeFAQ documentTypeFAQ2 = new DocumentTypeFAQ();
        documentTypeFAQ2.setDocID(2);
        documentTypeFAQ2.setQuestion("what is this ?");
        documentTypeFAQ2.setAnswers("this is a test case for integration tests");

        documentTypeFAQList.add(documentTypeFAQ1);

        documentTypeFAQList.add(documentTypeFAQ2);

        when(faqRepository.findAll()).thenReturn(documentTypeFAQList);

        String expected = om.writeValueAsString(documentTypeFAQList);

        ResponseEntity<String> response = tTemplate.getForEntity("/faq/getAllFAQ", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);

        verify(faqRepository, times(1)).findAll();

    }


    @Test
    public void save_faq_OK() throws Exception {

        DocumentTypeFAQ documentTypeFAQ1 = new DocumentTypeFAQ();
        documentTypeFAQ1.setDocID(1);
        documentTypeFAQ1.setQuestion("what is this ?");
        documentTypeFAQ1.setAnswers("this is a test case for integration tests");
        when(faqRepository.save(any(DocumentTypeFAQ.class))).thenReturn(documentTypeFAQ1);

        String expected = om.writeValueAsString(documentTypeFAQ1);

        ResponseEntity<String> response = tTemplate.postForEntity("/faq/saveFAQ", documentTypeFAQ1, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);

        verify(faqRepository, times(1)).save(any(DocumentTypeFAQ.class));

    }

    @Test
    public void delete_faq_OK() {

        doNothing().when(faqRepository).deleteById(1);

        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = tTemplate.exchange("/faq/delete/1", HttpMethod.DELETE, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(faqRepository, times(1)).deleteById(1);
    }
}
