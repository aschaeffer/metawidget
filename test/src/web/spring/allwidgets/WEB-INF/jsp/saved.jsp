<%@ page language="java" %>
<%@ taglib uri="http://metawidget.org/spring" prefix="m" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
	<body>

		<form:form commandName="allWidgetsCommand">

			<m:metawidget path="allWidgetsCommand" createHiddenFields="true" readOnly="true" inspectorConfig="config/inspector-config.xml">				
				<m:stub path="mystery"/>
			</m:metawidget>

		</form:form>
		
	</body>	    
</html>