package com.hmtmcse.ocb

import grails.util.Holders
import org.springframework.web.multipart.MultipartFile

class FileUtil {

    public static String getRootPath(){
        return Holders.servletContext?.getRealPath("")
    }


    public static File makeDirectory(String path){
        File file = new File(path)
        if (!file.exists()){
            file.mkdirs()
        }
        return file
    }


    public static String uploadContactImage(Integer contactId, MultipartFile multipartFile){
      String success = ""

        if (contactId && multipartFile){
            String filePath = "${getRootPath()}contact-image/"
            makeDirectory(filePath)

            multipartFile.transferTo(new File(filePath, contactId + "-" + multipartFile.originalFilename))
            File file = new File(filePath, contactId + "-" + multipartFile.originalFilename);
            String error = parseFile(file)
            int noOfLines = 0
            int wordCount = 0
            long userId = 0l
            long coins = 0l
            String userName = ""
            //No errror then save to db
            if ("".equals(error)){

            file.eachLine  { String line ->
            noOfLines++
            wordCount=0
            line.tokenize().eachWithIndex { String word , int index ->
             wordCount++

              if (wordCount==1){
               Long temp= Long.parseLong(word);
               userId = temp
               }
               if (wordCount==2){
                Long temp= Long.parseLong(word);
                coins = temp
                }
               if (wordCount==3){
               userName = word
               }
             }
             //save to database
            UserDetails user = new UserDetails(userId:userId , coins:coins ,
                                    userName:userName).save()
            success= "saved Sucessfully"
            println success
            println user
            }

            } else {
              success = error
            }

            return "File Name:" +multipartFile.originalFilename + " - Status :" +success
        }
         return success
      }

      public static String parseFile(File file ){
      int noOfLines = 0
      int wordCount = 0
      String error = ""
      file.eachLine  { String line ->
      noOfLines++
      wordCount=0
      line.tokenize().each { String word ->
      wordCount++
      try{

      if (wordCount == 1  || wordCount == 2 ){

        if(wordCount == 1  && !(word.isInteger() || word.isLong() || word.isBigInteger()) ){
          error = "Invalid data at line no "+noOfLines + " , element :"+ word
          return error
        }
        if( wordCount ==2   && !(word.isInteger() || word.isLong() || word.isBigInteger())){
          error = "Invalid data at line no "+noOfLines + " , element is "+ word + ". Expecting natural number"
          return error
         }
         long temp= Long.parseLong(word)

         if((wordCount ==2 ) ){
         if (temp <= 0){

          error = "Invalid data at line no "+noOfLines + " , element  is "+ word + ". Expecting natural number"
          return error
          }
         }
       }
        }catch(NumberFormatException ne){

          error = "NumberFormatException occured. Invalid data at line no "+noOfLines + " , element is "+ word
          return error
        }catch(Exception e){
          error =  "Exception occured. Invalid data at line no "+noOfLines + " , element :"+ word
          return error
        }


       }
       }


         return error
      }
}
