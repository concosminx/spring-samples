<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Zero design skills</title>
  </head>
  <body>
    Hello and Welcome to our page
    <br />
    <br />
    <c:url value="/user/home" var="userHome" />
    <fieldset style="width: fit-content;">
      <legend>User Setup</legend>
      <h3>
        <a href="${userHome}">Go to User Setup</a>
      </h3>
    </fieldset>

    <br />
    <br />
    <c:url value="/car/home" var="carHome" />
    <fieldset style="width: fit-content;">
      <legend>Car Setup</legend>
      <h3>
        <a href="${carHome}">Go to Car Setup</a>
      </h3>
    </fieldset>
  </body>
</html>