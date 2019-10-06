package com.gen.kb.knowledgebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FAQService {

    @Autowired
    FAQRepository faqRepository;

    public List<DocumentTypeFAQ> getAllDFAQ() {
        List<DocumentTypeFAQ> list = faqRepository.findAll();
        return list;
    }

    public DocumentTypeFAQ saveIntoDB(DocumentTypeFAQ documentTypeFAQ) {
        documentTypeFAQ = faqRepository.save(documentTypeFAQ);
        return documentTypeFAQ;
    }

    public DocumentTypeFAQ updateFAQ(DocumentTypeFAQ documentTypeFAQ) throws RecordNotFoundException {
        Optional<DocumentTypeFAQ> docTpe = faqRepository.findById(documentTypeFAQ.getDocID());

        if (docTpe.isPresent()) {
            DocumentTypeFAQ newDoc = new DocumentTypeFAQ();
            newDoc.setDocID(documentTypeFAQ.getDocID());
            newDoc.setAnswers(documentTypeFAQ.getAnswers());
            newDoc.setQuestion(documentTypeFAQ.getQuestion());
            newDoc=faqRepository.save(newDoc);
            return newDoc;
        } else {
            throw new RecordNotFoundException("No record found");
        }

    }

    public void deleteById(int id) throws RecordNotFoundException {
        Optional<DocumentTypeFAQ> docTpe = faqRepository.findById(id);
        if (docTpe.isPresent()) {
            faqRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No record found");
        }
    }

}