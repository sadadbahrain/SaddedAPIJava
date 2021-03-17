<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sadded</title>
</head>
<body>
	<h1>Welcome to Sadded Demo</h1>
	 <form action="PaymentGateway" method="post" >
         <table>
         	<tr>
         		<td>Full Name </td>
         		<td><input name="fullName" type="text" /></td>
         	</tr>
         	<tr>
         		<td>Email </td>
         		<td><input name="email" type="text" /></td>
         	</tr>
         	<tr>
         		<td>Phone Number </td>
         		<td><input name="msisdn" type="text" /></td>
         	</tr>
         	<tr>
         		<td>Description </td>
         		<td><input name="description" type="text" /></td>
         	</tr>
         	<tr>
         		<td>Amount </td>
         		<td><input name="amount" type="number" /></td>
         	</tr>
         </table>
         <input type="submit" />
      </form>
</body>
</html>