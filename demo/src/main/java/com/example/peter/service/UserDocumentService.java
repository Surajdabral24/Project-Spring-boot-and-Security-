package com.example.peter.service;

import com.example.peter.entity.UserDocuments;
import com.example.peter.repository.UserDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDocumentService {

    @Autowired
    private UserDocumentRepository documentRepository;


    @Autowired
    private CloudService cloudService;


    public UserDocuments saveDocument(UserDocuments document, byte[] fileData,byte[] fileData1,byte[] fileData2,byte[] fileData3) {

        String filename = document.getNationaFile() + System.currentTimeMillis();
        String filename1 = document.getTaxFile() + System.currentTimeMillis();
        String filename2 = document.getPoliceFile() + System.currentTimeMillis();
        String filename3 = document.getLetterFile() + System.currentTimeMillis();

        String fileUrl = cloudService.uploadFile(fileData, filename);
        String fileUrl1 = cloudService.uploadFile(fileData1, filename1);
        String fileUrl2 = cloudService.uploadFile(fileData2, filename2);
        String fileUrl3 = cloudService.uploadFile(fileData3, filename3);

        document.setNationaFile(fileUrl);
        document.setTaxFile(fileUrl1);
        document.setPoliceFile(fileUrl2);
        document.setLetterFile(fileUrl3);

         documentRepository.save(document);
        return document;
    }


    public String deleteDocument(long id){

             UserDocuments documents = this.documentRepository.findByUserId(id);
             if(documents == null){
                 return "Documents not uploaded yet....";
             }else {
                 this.documentRepository.deleteById(documents.getDocumentId());
                 return "Documents deleted successfully";
             }

    }




    public String getDocumentFileUrl(UserDocuments document) {
        return cloudService.getFileUrl(document.getNationaFile());
    }



//    @Override
//    public String createDocument(UserDocuments userDocuments) {
//            documentRepository.save(userDocuments);
//        return "";
//        }

//    @Override
//    public UserDocuments getDocuments(long id) {
//        return this.documentRepository.findByUserId(id);
//    }


}

