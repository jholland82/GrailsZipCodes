
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
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><g:each in="${codes}" var="code"><g:link action='index' params="[state: code.key]">
                <div class="tag" style="font-size: ${(ranking.findIndexOf {it==code.key}+ 1) * 4}%;">${code.key}</div>
            </g:link></g:each></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
