<!doctype html>
<html>
    <head>
    </head>
    <body>

    <p>Please, add to the basket all the things you wish. After finishing, please add you name and phone number,
    and press "complete". You can check the contents of your basket at any time while picking items.

    <p>  <#assign firstName = "">
    <#assign lastName = "">
    <#assign phoneNumber = "">
    <form action="/complete" method = "post">
                       First name:<input type="text" required name="firstName" value="${firstName}"><br>
                       Last name:<input type="text" required name="lastName" value="${lastName}"><br>
                       Phone numer:<input type="text" required name="phoneNumber" value="${phoneNumber}"><br>
                       <input type="submit" value="Complete">
                       </form></p>