package com.hmtmcse.ocb

import grails.web.servlet.mvc.GrailsParameterMap

import javax.servlet.http.HttpServletRequest


class FileUploadService {

    AuthenticationService authenticationService

    def save(GrailsParameterMap params, HttpServletRequest request) {
        FileUpload fileUpload = new FileUpload(params)
        fileUpload.member = authenticationService.getMember()
        def response = AppUtil.saveResponse(false, fileUpload)
        if (fileUpload.validate()) {
            fileUpload.save(flush: true)
            if (!fileUpload.hasErrors()){
                response.isSuccess = true
                uploadImage(fileUpload, request)
            }
        }
        return response
    }

    def update(FileUpload fileUpload, GrailsParameterMap params, HttpServletRequest request) {
        fileUpload.properties = params
        def response = AppUtil.saveResponse(false, fileUpload)
        if (fileUpload.validate()) {
            fileUpload.save(flush: true)
            if (!fileUpload.hasErrors()){
                response.isSuccess = true
                uploadImage(fileUpload, request)
            }
        }
        return response
    }


    def get(Serializable id) {
        return FileUpload.get(id)
    }


    def list(GrailsParameterMap params) {
        params.max = params.max ?: GlobalConfig.itemsPerPage()
        List<FileUpload> contactList = FileUpload.createCriteria().list(params) {
            if (params?.colName && params?.colValue) {
                like(params.colName, "%" + params.colValue + "%")
            }
            if (!params.sort) {
                order("id", "desc")
            }
            eq("member", authenticationService.getMember())
        }
        return [list: contactList, count: contactList.totalCount]
    }


    def delete(FileUpload fileUpload) {
        try {
            fileUpload.delete(flush: true)
        } catch (Exception e) {
            println(e.getMessage())
            return false
        }
        return true
    }


    def uploadImage(FileUpload fileUpload, HttpServletRequest request){
        if (request.getFile("contactImage") && !request.getFile("contactImage").filename.equals("")){
            String uploadFile = FileUtil.uploadContactImage(fileUpload.id, request.getFile("contactImage"))
            if (!uploadFile.equals("")){
                fileUpload.uploadFile = uploadFile
                fileUpload.save(flush:true)
            }
        }
    }

}
