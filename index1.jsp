<!DOCTYPE html>
<html>
<head><title>CIS 35B Web App</title></head>
<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
<body bgcolor="#8894a8">
	<table class="title">
  		<tr><th><font size = "17">Car Configuration Client</font></th></tr>
	</table>
	</p>
    <p align="middle">
       Thank you for trying my car configuration client, please choose a car to configure          and press the submit button
    </p>
    
	<%@ page import="webApp.JSPGetCars" %>
    <%@ page import="java.util.ArrayList" %>
	<%ArrayList<String> temp = new JSPGetCars().getCarList();%>
    <form align="middle" action=ConfigClient/index.jsp method="GET">
        <select name="CarChoice">
            <%for(int x = 0; x < temp.size(); x++)
                out.println("<option value = \""+temp.get(x)+"\">" + temp.get(x) + "                                      </option>>");
            %>
        </select>
	<%out.println("<input type=\"hidden\" name=\"first\" value=\"0\">");%>
        <submit><input type="submit" value="Submit"></submit>
    </form>
		
</body>