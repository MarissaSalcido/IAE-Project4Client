<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="ISO-8859-1">
        <title>Catalog</title>
        <link rel="stylesheet" href="css/product_table.css">
        <link rel="stylesheet" href="css/style.css">
        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
    </head>
    <body>
        <div class = "container">
            <div class="menu">
                <ul style="margin-bottom: -10px;">
                    <li class ="logo"> <img src="images/book.png"> </li>
                    <li class ="title">Tome</li>
                    <li><a href="../index.html">Home</a></li>
                    <li><a href="html/aboutus.html">About Us</a> </li>
                    <li class="active"> Catalog </li>
                    <li><a href="cart">Cart</a> </li>
                </ul>
            </div>
        </div>
		
		<% java.util.HashMap<String, models.Book> bookMap = (HashMap<String, models.Book>)request.getAttribute("bookMap"); %>
        <table class="table-products">
            <tr>
                <td>
                    <div class="table-item-text">
                        <a href="ProductDetail?id=INF5678"><img src=<% out.print(bookMap.get("INF5678").getImage()); %> alt=<% out.print(bookMap.get("INF5678").getName()); %>></img></a>
                        <% out.print(bookMap.get("INF5678").getName()); %>
                        <br>by <% out.print(bookMap.get("INF5678").getAuthor()); %><br><% out.print(bookMap.get("INF5678").getType()); %><br><span style="color:black; background-color: inherit;">$<% out.print(bookMap.get("INF5678").getPrice()); %></span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="ProductDetail/id=INF1928"><img src=<% out.print(bookMap.get("INF1928").getImage()); %> alt=<% out.print(bookMap.get("INF1928").getName()); %>></img></a>
                        <% out.print(bookMap.get("INF1928").getName()); %>
                        <br>by <% out.print(bookMap.get("INF1928").getAuthor()); %><br><% out.print(bookMap.get("INF1928").getType()); %><br><span style="color:black; background-color: inherit;">$<% out.print(bookMap.get("INF1928").getPrice()); %></span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="ProductDetail/id=INF1234"><img src=<% out.print(bookMap.get("INF1234").getImage()); %> alt=<% out.print(bookMap.get("INF1234").getName()); %>></img></a>
                        <% out.print(bookMap.get("INF1234").getName()); %>
                        <br>by <% out.print(bookMap.get("INF1234").getAuthor()); %><br><% out.print(bookMap.get("INF1234").getType()); %><br><span style="color:black; background-color: inherit;">$<% out.print(bookMap.get("INF1234").getPrice()); %></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="table-item-text">
                        <a href="ProductDetail/id=INF2837"><img src=<% out.print(bookMap.get("INF2837").getImage()); %> alt=<% out.print(bookMap.get("INF2837").getName()); %>></img></a>
                        <% out.print(bookMap.get("INF2837").getName()); %>
                        <br>by <% out.print(bookMap.get("INF2837").getAuthor()); %><br><% out.print(bookMap.get("INF2837").getType()); %><br><span style="color:black; background-color: inherit;">$<% out.print(bookMap.get("INF2837").getPrice()); %></span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="ProductDetail/id=INF1357"><img src=<% out.print(bookMap.get("INF1357").getImage()); %> alt=<% out.print(bookMap.get("INF1357").getName()); %>></img></a>
                        <% out.print(bookMap.get("INF1357").getName()); %>
                        <br>by <% out.print(bookMap.get("INF1357").getAuthor()); %><br><% out.print(bookMap.get("INF1357").getType()); %><br><span style="color:black; background-color: inherit;">$<% out.print(bookMap.get("INF1357").getPrice()); %></span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="ProductDetail/id=INF5498"><img src=<% out.print(bookMap.get("INF5498").getImage()); %> alt=<% out.print(bookMap.get("INF5498").getName()); %>></img></a>
                        <% out.print(bookMap.get("INF5498").getName()); %>
                        <br>by <% out.print(bookMap.get("INF5498").getAuthor()); %><br><% out.print(bookMap.get("INF5498").getType()); %><br><span style="color:black; background-color: inherit;">$<% out.print(bookMap.get("INF5498").getPrice()); %></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="table-item-text">
                        <a href="ProductDetail/id=INF3746"><img src=<% out.print(bookMap.get("INF3746").getImage()); %> alt=<% out.print(bookMap.get("INF3746").getName()); %>></img></a>
                        <% out.print(bookMap.get("INF3746").getName()); %>
                        <br>by <% out.print(bookMap.get("INF3746").getAuthor()); %><br><% out.print(bookMap.get("INF3746").getType()); %><br><span style="color:black; background-color: inherit;">$<% out.print(bookMap.get("INF3746").getPrice()); %></span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="ProductDetail/id=INF2468"><img src=<% out.print(bookMap.get("INF2468").getImage()); %> alt=<% out.print(bookMap.get("INF2468").getName()); %>></img></a>
                        <% out.print(bookMap.get("INF2468").getName()); %>
                        <br>by <% out.print(bookMap.get("INF2468").getAuthor()); %><br><% out.print(bookMap.get("INF2468").getType()); %><br><span style="color:black; background-color: inherit;">$<% out.print(bookMap.get("INF2468").getPrice()); %></span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="ProductDetail/id=INF1245"><img src=<% out.print(bookMap.get("INF1245").getImage()); %> alt=<% out.print(bookMap.get("INF1245").getName()); %>></img></a>
                        <% out.print(bookMap.get("INF1245").getName()); %>
                        <br>by <% out.print(bookMap.get("INF1245").getAuthor()); %><br><% out.print(bookMap.get("INF1245").getType()); %><br><span style="color:black; background-color: inherit;">$<% out.print(bookMap.get("INF1245").getPrice()); %></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="table-item-text">
                        <a href="ProductDetail/id=INF9782"><img src=<% out.print(bookMap.get("INF9782").getImage()); %> alt=<% out.print(bookMap.get("INF9782").getName()); %>></img></a>
                        <% out.print(bookMap.get("INF9782").getName()); %>
                        <br>by <% out.print(bookMap.get("INF9782").getAuthor()); %><br><% out.print(bookMap.get("INF9782").getType()); %><br><span style="color:black; background-color: inherit;">$<% out.print(bookMap.get("INF9782").getPrice()); %></span>
                    </div>
                </td>
            </tr>

        </table>
