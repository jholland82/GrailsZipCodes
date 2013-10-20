<%@ page import="com.grailszipcodes.ZipCode" %>



<div class="fieldcontain ${hasErrors(bean: zipCodeInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="zipCode.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${zipCodeInstance?.state}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: zipCodeInstance, field: 'stateCode', 'error')} ">
	<label for="stateCode">
		<g:message code="zipCode.stateCode.label" default="State Code" />
		
	</label>
	<g:textField name="stateCode" value="${zipCodeInstance?.stateCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: zipCodeInstance, field: 'postalCode', 'error')} ">
	<label for="postalCode">
		<g:message code="zipCode.postalCode.label" default="Postal Code" />
		
	</label>
	<g:textField name="postalCode" value="${zipCodeInstance?.postalCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: zipCodeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="zipCode.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${zipCodeInstance?.name}"/>
</div>

