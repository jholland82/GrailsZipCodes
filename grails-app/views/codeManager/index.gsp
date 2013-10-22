
<%@ page import="com.grailszipcodes.CodeManager" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'codeManager.label', default: 'CodeManager')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-codeManager" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-codeManager" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${codeManagerInstanceList}" status="i" var="codeManagerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
					</tr>
				</g:each>
				</tbody>
			</table>
            <table>
                <g:each in="${stateTags}" status="i" var="stateInstance">
                    <tr>
                        <td>${stateInstance}</td>
                    </tr>
                </g:each>
            </table>
			<div class="pagination">
				<g:paginate total="${codeManagerInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
