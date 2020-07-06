package com.hmtmcse.ocb

class FileUploadController {

    FileUploadService fileUploadService

    def index() {
        def response = fileUploadService.list(params)
        [fileUpload: response.list, total:response.count]
    }

    def details(Integer id) {
        def response = fileUploadService.get(id)
        if (!response){
            redirect(controller: "fileUpload", action: "index")
        }else{
            [fileUpload: response]
        }
    }

    def create() {
        [fileUpload: flash.redirectParams]
    }

    def save() {
        def response = fileUploadService.save(params, request)
        if (response.isSuccess) {
            flash.message = AppUtil.infoMessage(g.message(code: "saved"))
            redirect(controller: "fileUpload", action: "index")
        } else {
            flash.redirectParams = response.model
            flash.message = AppUtil.infoMessage(g.message(code: "unable.to.save"), false)
            redirect(controller: "fileUpload", action: "create")
        }
    }

    def edit(Integer id) {
        if (flash.redirectParams) {
            [fileUpload: flash.redirectParams]
        } else {
            def response = fileUploadService.get(id)
            if (!response) {
                flash.message = AppUtil.infoMessage(g.message(code: "invalid.entity"), false)
                redirect(controller: "fileUpload", action: "index")
            } else {
                [fileUpload: response]
            }
        }
    }

    def update() {
        def response = fileUploadService.get(params.id)
        if (!response){
            flash.message = AppUtil.infoMessage(g.message(code: "invalid.entity"), false)
            redirect(controller: "fileUpload", action: "index")
        }else{
            response = fileUploadService.update(response, params, request)
            if (!response.isSuccess){
                flash.redirectParams = response.model
                flash.message = AppUtil.infoMessage(g.message(code: "unable.to.update"), false)
                redirect(controller: "fileUpload", action: "edit")
            }else{
                flash.message = AppUtil.infoMessage(g.message(code: "updated"))
                redirect(controller: "fileUpload", action: "index")
            }
        }
    }

    def delete(Integer id) {
        def response = fileUploadService.get(id)
        if (!response){
            flash.message = AppUtil.infoMessage(g.message(code: "invalid.entity"), false)
            redirect(controller: "fileUpload", action: "index")
        }else{
            response = fileUploadService.delete(response)
            if (!response){
                flash.message = AppUtil.infoMessage(g.message(code: "unable.to.delete"), false)
            }else{
                flash.message = AppUtil.infoMessage(g.message(code: "deleted"))
            }
            redirect(controller: "fileUpload", action: "index")
        }
    }
}
