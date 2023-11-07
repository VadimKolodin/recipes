<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Recipes</title>
</head>
<body>
<h1><%= "Methods: "%>
</h1>
<br/>
<a href="recipes">/recipes</a>
<p>
<ul>
    <li>GET</li>
    <li>POST<br>
        Body:
        <pre>
{
    "name": "string",
    "description":"string",
    "ingredients": [
        {
            "ingredientId":"5b6f3139-a4fb-455a-9ac3-226c78acf2d7",
            "quantity" : 1,
            "measure": "string"
        }
    ]
}
    </pre>
    </li>
    <li>DELETE /{recipeId}<br>

</ul>
</p>
<br>
<a href="ingredients">/ingredients</a>
<p>
<ul>
    <li>GET</li>
    <li>POST<br>
        Body:
        <pre>
{
    "name": "string",
    "category": "ENUM"
}
    </pre>
    </li>
    <li>DELETE /{ingredientId}<br>


</ul>
</p>
</body>
</html>