<div class="form-group">
    <label><g:message code="file.name"/> *</label>
    <g:textField name="name" class="form-control" value="${fileUpload?.name}" placeholder="Please Enter File Name"/>
    <UIHelper:renderErrorMessage fieldName="name" model="${fileUpload}" errorMessage="please.enter.name"/>
</div>

<div class="form-group">
    <label><g:message code="image"/> *</label>
    <g:field name="contactImage" class="form-control"  type="file" placeholder="Please Upload File"/>
    <g:if test="${fileUpload?.uploadFile}">
        <img src="${resource(dir: "contact-image", file: "/${fileUpload.id}-${fileUpload.uploadedFile}")}" class="img-thumbnail" style="margin-top: 10px; height: 100px; width: 100px;"/>
    </g:if>

</div>
