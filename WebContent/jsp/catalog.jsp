<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="ISO-8859-1">
        <title>Catalog</title>
        <link rel="stylesheet" href="../css/product_table.css">
        <link rel="stylesheet" href="../css/style.css">
        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
    </head>
    <body>
        <div class = "container">
            <div class="menu">
                <ul style="margin-bottom: -10px;">
                    <li class ="logo"> <img src="../images/book.png"> </li>
                    <li class ="title">Tome</li>
                    <li><a href="../index.html">Home</a></li>
                    <li class="active"> Catalog </li>
                    <li><a href="../html/aboutus.html">About Us</a> </li>
                </ul>
            </div>
        </div>

        <div class="sidebar">
            <div class="genre">
                <h2>Genre</h2>
                <ul id="genre-list">
                    <li class="active-genre">All</li>
                    <li>Fiction</li>
                    <li>Mystery</li>
                    <li>Horror</li>
                    <li>Suspense</li>
                    <li>Science Fiction</li>
                </ul>
            </div>
        </div>

        <table class="table-products">
            <tr>
                <td>
                    <div class="table-item-text">
                        <a href="../html/countdown.html"><img src="../images/1945.jpg" alt="1945"></img></a>
                        1945<br>by Chris Wallace<br>Hardcover<br><span style="color:black; background-color: inherit;">$21.49</span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="../html/gentleman.html"><img src="../images/a_gentleman_in_moscow.jpg" alt="A Gentleman in Moscow"></img></a>
                        A Gentleman in Moscow<br>by Amor Towles<br>Paperback<br><span style="color:black; background-color: inherit;">$14.99</span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="../html/malice.html"><img src="../images/a_stroke_of_malice.jpg" alt="A Stroke of Malice"></img></a>
                        A Stroke of Malice<br>by Anna Lee Huber<br>Paperback<br><span style="color:black; background-color: inherit;">$15.30</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="table-item-text">
                        <a href="../html/labyrinth.html"><img src="../images/labyrinth.jpg" alt="Labyrinth"></img></a>
                        Labyrinth<br>by Catherine Coulter<br>Paperback<br><span style="color:black; background-color: inherit;">$12.74</span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="../html/fires.html"><img src="../images/little_fires.jpg" alt="Little Fires Everywhere"></img></a>
                        Little Fires Everywhere<br>by Celeste Ng<br>Paperback<br><span style="color:black; background-color: inherit;">$14.99</span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="../html/meditations.html"><img src="../images/meditations.jpg" alt="Meditations"></img></a>
                        Meditations<br>by Marcus Aurelius<br>Hardcover<br><span style="color:black; background-color: inherit;">$23.48</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="table-item-text">
                        <a href="../html/holmes.html"><img src="../images/the_daughter_of_sherlock_holmes.jpg" alt="The Daughter of Sherlock Holmes"></img></a>
                        The Daughter of Sherlock Holmes<br>by Leonard Goldberg<br>Hardcover<br><span style="color:black; background-color: inherit;">$25.99</span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="../html/devils.html"><img src="../images/the_devil_in_the_white_city.jpg" alt="The Devil in the White City"></img></a>
                        The Devil in the White City<br>by Erik Larson<br>Paperback<br><span style="color:black; background-color: inherit;">$15.30</span>
                    </div>
                </td>
                <td>
                    <div class="table-item-text">
                        <a href="../html/hunting.html"><img src="../images/the_hunting_party.jpg" alt="The Hunting Party"></a>
                        The Hunting Party<br>by Lucy Foley<br>Hardcover<br><span style="color:black; background-color: inherit;">$24.29</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="table-item-text">
                        <a href="../html/monkey_god.html"><img src="../images/the_lost_city_of_the_monkey_god.jpg" alt="The Lost City of the Monkey God"></a>
                        The Lost City of the Monkey God<br>by Douglas Preston<br>Paperback<br><span style="color:black; background-color: inherit;">$16.19</span>
                    </div>
                </td>
            </tr>

        </table>
    </body>
</html>