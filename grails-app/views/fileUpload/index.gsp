<meta name="layout" content="main"/>

<div class="card">
    <div class="card-header">
        <g:message code="file.list" args="['List']"/>
        <span class="float-right">
          <div class="btn-group">
                <g:link controller="fileUpload" action="create" class="btn btn-success"><g:message code="upload"/></g:link>
                <g:link controller="fileUpload" action="index" class="btn btn-primary"><g:message code="reload"/></g:link>
            </div>
        </span>
    </div>
    <div class="card-body">
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th><g:message code="id"/></th>
                <th><g:message code="uploaded.name"/></th>
                <g:sortableColumn property="name" title="${g.message(code: "name")}" style="width: 200px"/>
                <th class="action-row" style="width: 50px"><g:message code="delete"/></th>
            </tr>
            </thead>
            <tbody>
                <g:each in="${fileUpload}" var="info">
                    <tr>
                      <td>
                        <p>${info.id}</p>
                      </td>

                        <td>
                            <g:if test="${info.uploadFile}">
                                <p>${info.uploadFile}</p>

                            </g:if>
                            <g:else>
                                <g:img dir="images" file="grails.svg" class="img-thumbnail" style="height: 50px; width: 50px;"/>
                            </g:else>
                        </td>
                        <td>${info?.name}</td>
                        <td>
                            <div class="btn-group">
                                <g:link controller="fileUpload" action="delete" id="${info.id}" class="btn btn-secondary delete-confirmation"><i class="fas fa-trash"></i></g:link>
                            </div>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
        <div class="paginate">
            <g:paginate total="${total ?: 0}" />
        </div>
    </div>
</div>
