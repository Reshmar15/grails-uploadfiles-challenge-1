<meta name="layout" content="main"/>

<div class="card">
    <div class="card-header">
        <g:message code="contact" args="['Details']"/>
    </div>
    <div class="card-body">

        <div class="row">
            <div class="col-3">
                <div class="card" style="width: 18rem;">
                    <g:if test="${fileUpload.uploadFile}">
                        <img src="${resource(dir: "contact-image", file: "/${fileUpload.id}-${fileUpload.uploadFile}")}" class="card-img-top"/>
                    </g:if>
                    <g:else>
                        <g:img dir="images" file="grails.svg" class="card-img-top"/>
                    </g:else>

                </div>
            </div>

        </div>


        <div class="form-action-panel" style="margin-top: 8px;">
            <g:link controller="fileUpload" action="index" class="btn btn-primary"><g:message code="cancel"/></g:link>
        </div>
    </div>
</div>
