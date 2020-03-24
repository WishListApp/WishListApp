<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Item</title>
    <link rel="stylesheet" href="./resources/css/normalize.css">
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/addItem.css">
</head>
<body>
    <h2>Add Item</h2>
    <div class="menu_bar">
        <ul>
            <li><a href="./mainPage">Home</a></li>
            <li><a href="./balance">Balance</a></li>
            <li><a href="./addItem"  class="active">Add Item</a></li>
            <li><a href="">Delete Item</a></li>
        </ul>
    </div>

    <div>
        <form action="" method="post" class="form-signin">
            <label> Name:
                <input type="text" name="name" class="form-control">
            </label>
            <br>
            <label> Category:
                <select name="category" id="category">
                    <option value="jewelry">Jewelry</option>
                    <option value="clothes">Clothes</option>
                    <option value="tech">Tech</option>
                </select>
            </label>
            <br>
            <label> Priority:
                <select name="priority" id="priority">
                    <option value="0">Top</option>
                    <option value="1">Middle</option>
                    <option value="2">Low</option>
                </select>
            </label>
            <br>
            <label>Price:
                <input type="number" name="price" id="price" class="form-control" min="0">
            </label>
            <label> Store Page:
                <input type="text" name="store_page" class="form-control">
            </label>
            <br>
            <input type="submit" value="Add Item" class="btn-success">
        </form>
    </div>
</body>
</html>