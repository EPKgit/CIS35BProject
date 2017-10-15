<!DOCTYPE html>
<html>
<head><title>CIS 35B Web App</title></head>
<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
	  
<body bgcolor="#8894a8">
	<table class="title" align = "middle">
  		<tr><th><font size = "17">Car Chosen</font></th></tr>
	</table>
	</p>
	
	<%@ page import="javax.servlett.http.*" %>
	<%@ page import="java.util.Enumeration"%>
	<%@ page import="webApp.JSPConfigureCars"%>
	<%@ page import="java.lang.*"%>
	<%
		String choice = request.getParameter("CarChoice");
		int first = Integer.parseInt(request.getParameter("first"));
		if(choice == null && first == 0)
		{
			out.println("You have reached this page in error, please navigate back to /CIS35BProject/ to try again.");
		}
		else if(first != 0)
		{
			JSPConfigureCars temp = new JSPConfigureCars(choice);
			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements())
			{
				String curr = (String) e.nextElement();
				if(!(curr.compareTo("first") == 0) && !(curr.compareTo("CarChoice") == 0))
					temp.setOptionChoice(curr, request.getParameter(curr));
			}
			out.println("Here is what you selected:<br><table border=1>");
			out.println("<tr><td>" + choice+"</td><td>Base Price</td><td>"+temp.getBasePrice()+"</td>");
			String[] optionSetNames = temp.getOptionSetNames();
			for(int x = 0; x < optionSetNames.length; x++)
			{
				out.println("<tr><td>" + optionSetNames[x]+"</td><td>" + temp.getOptionChoice(optionSetNames[x]) + "</td><td>" + temp.getOptionChoicePrice(optionSetNames[x]) +"</td>");
			}
			out.println("<tr><td><b>Total Price</b></td><td></td><td>" + (temp.getTotalPrice()+temp.getBasePrice())+"</td></table>");
			
		}
		else
		{
	%>
	
    <p align="middle">
		You have chosen the <%=choice%> to configure.
    </p>
	
	<form action=# method="GET">
			
			<%out.println("<input type=\"hidden\" name=\"CarChoice\" value=\"" + choice+"\">");%>
			<%out.println("<input type=\"hidden\" name=\"first\" value=\"1\">");%>
			<tr>
				<td>Make/Model</td>
				<td><%=choice%></td>
			</tr>
			<%
				JSPConfigureCars temp = new JSPConfigureCars(choice);
				String[] optionSetNames = temp.getOptionSetNames();
				for(int x = 0; x < optionSetNames.length; x++)
				{
					String[] optionNames = temp.getOptionNames(optionSetNames[x]);
					out.println("<tr><td>" + optionSetNames[x]+"</td><td><select name=\""+optionSetNames[x]+"\">");
					for(int y = 0; y < optionNames.length; y++)
						out.println("<option value = \""+optionNames[y]+"\">" + optionNames[y] + "</option>>");
					out.println("</select></td></tr><br>");
				}
				out.println("<input type=\"submit\" value=\"Submit\"></submit>");
				}//extra curly bracket to finish the else statement from earlier
			%>
	</form>
	
</body>