package com.example.peter.controller;


import com.example.peter.constant.UserConstant;
import com.example.peter.entity.User;
import com.example.peter.entity.UserDocuments;
import com.example.peter.service.UserDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class DocumentsController {

    @Autowired
    private UserDocumentService documentService;


//    @PostMapping("/documents")
//    public ResponseEntity<String> addDocuments(@RequestParam("userId") Long userId, @RequestParam("documentId") long documentId, @RequestParam("nationalId") String nationalId, @RequestParam("nationaFile") MultipartFile nationaFile, @RequestParam("taxPinNumber") String taxPinNumber, @RequestParam("taxFile") MultipartFile taxFile, @RequestParam("policeGoodConductNumber") String policeGoodConductNumber, @RequestParam("policeFile") MultipartFile policeFile, @RequestParam("letter") String letter, @RequestParam("letterFile") MultipartFile letterFile) {
//        try {
//            byte[] fileData = nationaFile.getBytes();
//            byte[] fileData1 = taxFile.getBytes();
//            byte[] fileData2 = policeFile.getBytes();
//            byte[] fileData3 = letterFile.getBytes();
//
//            System.out.print("hy");
//            UserDocuments documents = new UserDocuments();
//            documents.setDocumentId(documentId);
//            documents.setNationalId(nationalId);
//            documents.setNationaFile(fileData);
//            documents.setTaxPinNumber(taxPinNumber);
//            documents.setTaxFile(fileData1);
//            documents.setPoliceGoodConductNumber(policeGoodConductNumber);
//            documents.setPolicFile(fileData2);
//            documents.setLetter(letter);
//            documents.setLetterFile(fileData3);
//
//            User user = new User();
//            user.setId(userId);
//            documents.setUser(user);
//            System.out.print("hy");
//            documentService.createDocument(documents);
//            System.out.print("hy");
//            return ResponseEntity.status(HttpStatus.OK).body("documents added");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }


    @PostMapping("/upload")
    public ResponseEntity<UserDocuments> uploadDocument(@RequestParam("documentId") long documentId,@RequestParam("nationalId") String nationalId, @RequestParam("file") MultipartFile file,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,@RequestParam("file3") MultipartFile file3,@RequestParam("user_id") long user_id,@RequestParam("taxPinNumber") String taxPinNumber,@RequestParam("policeGoodConductNumber") String policeGoodConductNumber,@RequestParam("letter") String letter) {
        byte[] fileData;
        byte[] fileData1;
        byte[] fileData2;
        byte[] fileData3;
        try {
            fileData = file.getBytes();
            fileData1 = file1.getBytes();
            fileData2 = file2.getBytes();
            fileData3 = file3.getBytes();
            UserDocuments documents = new UserDocuments();
            documents.setDocumentId(documentId);
            documents.setNationalId(nationalId);
            documents.setTaxPinNumber(taxPinNumber);
            documents.setPoliceGoodConductNumber(policeGoodConductNumber);
            documents.setLetter(letter);

            User user = new User();
            user.setId(user_id);
            documents.setUser(user);

            UserDocuments savedDocument = documentService.saveDocument(documents, fileData,fileData1,fileData2,fileData3);
            return ResponseEntity.ok(savedDocument);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }

    @DeleteMapping("/deleteDocuments")
    public ResponseEntity<String> deleteData(@PathVariable("id") long id){
        try{
            String str = this.documentService.deleteDocument(id);

                return ResponseEntity.status(HttpStatus.OK).body(str);
            }catch (Exception e){

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(UserConstant.MESSAGE_205);
        }

    }



//        @GetMapping("documents/{id}")
//        public UserDocuments get(@PathVariable("id") long id){
//            return this.documentService.getDocuments(id);
//        }
    }

