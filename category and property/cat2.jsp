<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Categories</title>
   <style>
        /* Add your CSS styles for cards here 
        .card {
            width: 20%;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin: 40px;
            padding: 10px;
            float: left;
            text-align: center;
            display: inline-block;
            margin-right: 10px;
            }*/
         /* Add your CSS styles for cards here */
.card {
    width: calc(30.33% - 0px); /* Width of each card, minus margins */
    /* margin: 20px 10px; Margin around each card 
    float: left;*/
    text-align: center;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 5px;
    overflow: hidden; /* Ensure images don't overflow */
}
/*
.card img {
    width: 100%;
    height: auto;  Maintain aspect ratio 
}*/
   
    </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="category.css">
</head>
<body>
    <header>PROPERTY CATEGORIES</header>
    <div class="container">
        <div class="navbar">
            <nav>
                <a href="/home.html">Home</a>
                <a class="active" href="cat2.jsp">Properties</a>
                <a href="user/careers.html">Careers</a>
                <a href="user/testimonial.html">Testimonials</a>
                <!--<a href="/AboutUs/about us.html">About us</a>-->
            </nav>
        </div>
        <div class="col">     
    <div id="categoryContainer" >
        <!-- Categories will be dynamically loaded here -->
    </div>  
       </div>
    <script>
        // JavaScript to fetch and display categories
        window.onload = function() {
            fetch('CategoryServlet')
                .then(response => response.text())
                .then(data => {
                    document.getElementById('categoryContainer').innerHTML = data;
                });
        };
    </script>
    <footer>
            <a href="#" class="fa fa-facebook"></a>
            <a href="#" class="fa fa-twitter"></a>
            <a href="#" class="fa fa-instagram"></a>
            <p>&#169; copyright 2024 NestQuest.</p>
    </footer>
</body>
</html>
