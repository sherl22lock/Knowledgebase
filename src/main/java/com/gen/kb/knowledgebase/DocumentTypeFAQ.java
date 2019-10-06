package com.gen.kb.knowledgebase;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="FAQ_table")
@ApiModel(description = "All details about the FAQ document type")
public class DocumentTypeFAQ {

    @ApiModelProperty(notes = "The database generated FAQ ID")
    @GeneratedValue
    @Id
    private int docID;

    @ApiModelProperty(notes = "Question field for the FAQ document")
    @Column(name = "Question")
    private String Question;

    @ApiModelProperty(notes = "Answers field for the FAQ document")
    @Column(name = "Answers")
    private  String Answers;

    public int getDocID() {
        return docID;
    }

    public void setDocID(int docID) {
        this.docID = docID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswers() {
        return Answers;
    }

    public void setAnswers(String answers) {
        Answers = answers;
    }

    @Override
    public String toString() {
        return "DocumentTypeFAQ{" +
                "docID=" + docID +
                ", Question='" + Question + '\'' +
                ", Answers='" + Answers + '\'' +
                '}';
    }
}
