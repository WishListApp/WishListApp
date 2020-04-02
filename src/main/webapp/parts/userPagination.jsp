<center>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:choose>
                <c:when test="${currentPage != 1}">
                    <li>
                        <a href="/admin/users?page=${currentPage - 1}"aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="disabled">
                        <a href="/admin/users?page=${currentPage - 1}"aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:forEach begin="1" end="${pageCount}" var="i">
                <c:choose>
                    <c:when test="${currentPage != i}">
                        <li><a href="/admin/users?page=${i}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                         <li class="active"><a href="/admin/users?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${currentPage != pageCount}">
                    <li>
                        <a href="/admin/users?page=${currentPage + 1}"aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="disabled">
                        <a href="/admin/users?page=${currentPage + 1}"aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</center>