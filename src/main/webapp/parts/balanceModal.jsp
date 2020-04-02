<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="balanceModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Balance settings</h4>
            </div>
            <div class="modal-body">
                <div>
                    <form:form method="POST" modelAttribute="BalanceForm" action="${contextPath}/balance"
                               class="form-signin">
                        <h2 class="form-signin-heading" id="updateBalanceForm">Alter Balance</h2>
                        <spring:bind path="balanceChange">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <h3>Add/Subtract to/from balance</h3>
                                <p id="textFieldAlert" style="color: red"></p>
                                <form:input type="number" path="balanceChange" class="form-control"
                                            placeholder="Value to add/subtract" id="input-number"
                                            autofocus="true"></form:input>
                                <form:errors path="balanceChange"></form:errors>
                            </div>
                        </spring:bind>

                        <spring:bind path="note">
                            <h3>Add note to changes in balance(optional)</h3>
                            <form:textarea path="note" maxlength="200" rows="10" cols="10"
                                           cssClass="form-control"></form:textarea>
                        </spring:bind>
                        <button class="btn btn-lg btn-primary btn-block" id="submitBtn" type="submit" disabled>Submit
                        </button>
                        <button class="btn btn-lg btn-primary btn-block btn-danger" data-dismiss="modal">Cancel</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    const inputField = document.getElementById("input-number");
    inputField.addEventListener("change", function (e) {
        if (e.target.value === '') {
            e.target.value = "0";
        }

        if (parseFloat(e.target.value) > 10000 || parseFloat(e.target.value) < -10000) {
            document.getElementById("submitBtn").disabled = true;
            document.getElementById("textFieldAlert").innerText = "You more than 10000 or less than -10000";
        } else if (parseFloat(e.target.value) === 0) {
            document.getElementById("submitBtn").disabled = true;
            document.getElementById("textFieldAlert").innerText = "You can't submit " + e.target.value;
        } else {
            document.getElementById("submitBtn").disabled = false;
            document.getElementById("textFieldAlert").innerText = "";
        }

    });
</script>