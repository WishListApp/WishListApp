<center>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:choose>
                <c:when test="${currentPage != 1}">
                <li>
                    <c:if test="${category != null}">
                        <a href="/itemList?category=${category}&page=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </c:if>
                    <c:if test="${category == null}">
                        <a href="/itemList?page=${currentPage - 1}"aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </c:if>
                </li>
                </c:when>
                <c:otherwise>
                <li class="disabled">
                    <c:if test="${category != null}">
                        <a href="/itemList?category=${category}&page=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </c:if>
                    <c:if test="${category == null}">
                        <a href="/itemList?page=${currentPage - 1}"aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </c:if>
                </li>
                </c:otherwise>
            </c:choose>

            <c:forEach begin="1" end="${pageCount}" var="i">
                <c:choose>
                    <c:when test="${currentPage != i}">
                        <c:if test="${category != null}">
                            <li><a href="/itemList?category=${category}&page=${i}">${i}</a></li>
                        </c:if>

                        <c:if test="${category == null}">
                            <li><a href="/itemList?page=${i}">${i}</a></li>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${category != null}">
                            <li class="active"><a href="/itemList?category=${category}&page=${i}">${i}</a></li>
                        </c:if>

                        <c:if test="${category == null}">
                            <li class="active"><a href="/itemList?page=${i}">${i}</a></li>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${currentPage != pageCount}">
                <li>
                    <c:if test="${category != null}">
                        <a href="/itemList?category=${category}&page=${currentPage - 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </c:if>
                    <c:if test="${category == null}">
                        <a href="/itemList?page=${currentPage - 1}"aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </c:if>
                </li>
                </c:when>
                <c:otherwise>
                <li class="disabled">
                    <c:if test="${category != null}">
                        <a href="/itemList?category=${category}&page=${currentPage - 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </c:if>
                    <c:if test="${category == null}">
                        <a href="/itemList?page=${currentPage - 1}"aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </c:if>
                </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</center>