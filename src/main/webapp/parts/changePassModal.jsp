<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="changePassModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Change Password</h4>
            </div>
            <div class="modal-body">
                <div>
                    <form:form method="POST" class="form-signin" action="${contextPath}/setUserPwd">
                        <div>
                            <input id="newPass" minlength="8" maxlength="32" type="text" required class="form-control"
                                   placeholder="New password" autofocus>
                        </div>
                        <div>
                            <input type="text" id="repeatNewPass" required name="pass"
                                   class="form-control" placeholder="Repeat new password">
                        </div>
                        <input type="hidden" value="${id}" name="id">
                        <%--                        <div class="form-group">--%>
                        <%--                            <form:input type="text" required="required" --%>
                        <%--                                        class="form-control" placeholder="Enter new password"--%>
                        <%--                                        autofocus="true"></form:input>--%>
                        <%--                        </div>--%>

                        <%--                        <div>--%>

                        <%--                        </div>--%>

                        <%--                        <div>--%>
                        <%--                            <form:input type="text" required="required" path="name"--%>
                        <%--                                        class="form-control" placeholder="Enter new password"--%>
                        <%--                                        autofocus="true"></form:input>--%>
                        <%--                        </div>--%>

                        <button class="btn btn-default btn-lg btn-block" type="submit" disabled id="submitBtn">Change
                            Password
                        </button>
                        <button type="button" class="btn btn-default btn-lg btn-block btn-danger" data-dismiss="modal">
                            Cancel
                        </button>
                        <script>
                            document.getElementById("repeatNewPass").addEventListener('change', function checkPasswords() {
                                if (document.getElementById("repeatNewPass").value === document.getElementById("newPass").value) {
                                    document.getElementById("submitBtn").disabled = false;
                                } else {
                                    document.getElementById("submitBtn").disabled = true;
                                }
                            });
                        </script>
                    </form:form>
                </div>
            </div>
        </div>

    </div>
</div>