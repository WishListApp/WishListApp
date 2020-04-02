<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="changeUserPassModal" class="modal fade" role="dialog">
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
                            <input id="newPass" minlength="8" maxlength="32" type="password" required
                                   class="form-control"
                                   placeholder="New password" autofocus>
                        </div>
                        <div>
                            <input type="password" id="repeatNewPass" required name="pass"
                                   class="form-control" placeholder="Repeat new password">
                        </div>
                        <input type="hidden" value="${id}" name="id">

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

<div id="changeAdminPassModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Change Password</h4>
            </div>
            <div class="modal-body">
                <div>
                    <form:form id="setPwd" method="POST" name="setPwd" action="${contextPath}/admin/setPwd"
                               items="${UlistEmp}">
                        <input type="password" required minlength="8" maxlength="32" autofocus
                               placeholder="Enter password" id="pass-field" class="form-control"
                               style="margin-bottom: 5px;">

                        <button class="btn btn-default btn-lg btn-block" type="button" onclick="changePassword()"
                                id="submitBtn">Change
                            Password
                        </button>

                        <button type="button" class="btn btn-default btn-lg btn-block btn-danger" data-dismiss="modal">
                            Cancel
                        </button>

                        <input type="hidden" name="password" id="password">
                        <input type="hidden" name="id" id="id-field"/>
                        <script>
                            function changePassword() {
                                document.getElementById("password").value = document.getElementById("pass-field").value;
                                document.getElementById("setPwd").submit();
                            }
                        </script>
                    </form:form>
                </div>
            </div>
        </div>

    </div>
</div>