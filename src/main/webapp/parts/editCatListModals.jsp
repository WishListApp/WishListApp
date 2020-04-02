<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="addCatModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Category</h4>
            </div>
            <div class="modal-body">
                <div>
                    <form:form id="addCat" method="POST" name="addCat" cssClass="form-signin"
                               action="${contextPath}/admin/addCat">
                        <input type="text" required class="form-control" name="name" id="name">

                        <button class="btn btn-default btn-lg btn-block" type="submit">Add</button>
                        <button type="button " class="btn btn-default btn-lg btn-block btn-danger" data-dismiss="modal">
                            Cancel
                        </button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="renameCatModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Rename Category</h4>
            </div>
            <div class="modal-body">
                <div>
                    <form:form method="POST"
                               action="${contextPath}/admin/renameCat">
                        <input type="text" required class="form-control" name="name" id="name-field"
                               style="margin-bottom: 5px;">

                        <input type="hidden" name="id" id="id-field"/>
                        <button class="btn btn-default btn-lg btn-block" type="submit">Rename</button>
                        <button type="button " class="btn btn-default btn-lg btn-block btn-danger" data-dismiss="modal">
                            Cancel
                        </button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>