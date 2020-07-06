package com.hmtmcse.ocb

class FileUpload {

    Integer id
    String name
    String uploadFile
    Member member

    Date dateCreated
    Date lastUpdated

    static constraints = {
        uploadFile(nullable: true, blank: true)
    }

    static mapping = {
        version(false)

    }
}
