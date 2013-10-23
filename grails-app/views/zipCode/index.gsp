
<%@ page import="com.grailszipcodes.ZipCode" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'zipCode.label', default: 'ZipCode')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-zipCode" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link class="reset_codes" action="resetCodes">Reset ZipCodes</g:link></li>
			</ul>
		</div>
		<div id="list-zipCode" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="state" title="${message(code: 'zipCode.state.label', default: 'State')}" />
					
						<g:sortableColumn property="stateCode" title="${message(code: 'zipCode.stateCode.label', default: 'State Code')}" />
					
						<g:sortableColumn property="postalCode" title="${message(code: 'zipCode.postalCode.label', default: 'Postal Code')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'zipCode.name.label', default: 'Name')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${zipCodeInstanceList}" status="i" var="zipCodeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${zipCodeInstance.id}">${fieldValue(bean: zipCodeInstance, field: "state")}</g:link></td>
					
						<td>${fieldValue(bean: zipCodeInstance, field: "stateCode")}</td>
					
						<td>${fieldValue(bean: zipCodeInstance, field: "postalCode")}</td>
					
						<td>${fieldValue(bean: zipCodeInstance, field: "name")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>
