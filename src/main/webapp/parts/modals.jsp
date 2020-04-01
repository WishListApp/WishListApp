<div id="addItemModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add item</h4>
      </div>
      <div class="modal-body">
        <div>
            <form:form method="POST" modelAttribute="Item" class="form-signin">
                <spring:bind path="name">
                    <div class="form-group">
                        <form:input type="text" required="required" path="name"
                                    class="form-control" placeholder="Item Name" autofocus="true"></form:input>
                    </div>
                </spring:bind>

                <spring:bind path="group">
                    <div class="form-group">
                        <select required path="group" name="group" class="bootstrap-select"
                                style="height: 40px; width: 100%; margin-top: 10px;">
                            <option value="" disabled selected>Choose Category</option>
                            <c:forEach var="emp" items="${CatEmp}" varStatus="status">
                                <option value=${emp.id}>${emp.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </spring:bind>

                <spring:bind path="priority">
                    <div class="form-group">
                        <select required path="priority" name="priority" class="bootstrap-select"
                                style="height: 40px; width: 100%; margin-top: 10px;">
                            <option value="" selected disabled>Choose Priority</option>
                            <c:forEach var="emp" items="${PriorEmp}" varStatus="status">
                                <option value=${emp.id}>${emp.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </spring:bind>

                <spring:bind path="price">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="number" path="price" class="form-control"
                                    placeholder="Item Price" autofocus="true" min="0.01" step="0.01"></form:input>
                        <form:errors path="price"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="url">
                    <div class="form-group">
                        <form:input type="text" path="url"
                                    class="form-control" placeholder="Item Url"></form:input>

                    </div>
                </spring:bind>
               <button class="btn btn-default btn-lg btn-block" type="submit">Add</button>
               <button type="button " class="btn btn-default btn-lg btn-block btn-danger" data-dismiss="modal">Cancel</button>
            </form:form>
        </div>
      </div>
    </div>

  </div>
</div>

<div id="editItemModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Edit item</h4>
      </div>
      <div class="modal-body">
        <div>
            <form:form method="POST" modelAttribute="Item" class="form-signin">
                <spring:bind path="name">
                    <div class="form-group">
                        <form:input type="text" required="required" path="name"
                                    class="form-control" placeholder="Item Name" autofocus="true"></form:input>
                    </div>
                </spring:bind>

                <spring:bind path="group">
                    <div class="form-group">
                        <select required path="group" name="group" class="bootstrap-select"
                                style="height: 40px; width: 100%; margin-top: 10px;">
                            <option value="" disabled selected>Choose Category</option>
                            <c:forEach var="emp" items="${CatEmp}" varStatus="status">
                                <option value=${emp.id}>${emp.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </spring:bind>

                <spring:bind path="priority">
                    <div class="form-group">
                        <select required path="priority" name="priority" class="bootstrap-select"
                                style="height: 40px; width: 100%; margin-top: 10px;">
                            <option value="" selected disabled>Choose Priority</option>
                            <c:forEach var="emp" items="${PriorEmp}" varStatus="status">
                                <option value=${emp.id}>${emp.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </spring:bind>

                <spring:bind path="price">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="number" path="price" class="form-control"
                                    placeholder="Item Price" autofocus="true" min="0.01" step="0.01"></form:input>
                        <form:errors path="price"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="url">
                    <div class="form-group">
                        <form:input type="text" path="url"
                                    class="form-control" placeholder="Item Url"></form:input>

                    </div>
                </spring:bind>
               <button class="btn btn-default btn-lg btn-block" type="submit">Edit</button>
               <button type="button " class="btn btn-default btn-lg btn-block btn-danger" data-dismiss="modal">Cancel</button>
            </form:form>
        </div>
      </div>
    </div>

  </div>
</div>