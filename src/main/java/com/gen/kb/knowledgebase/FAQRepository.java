package com.gen.kb.knowledgebase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FAQRepository extends JpaRepository<DocumentTypeFAQ,Integer> {
}
