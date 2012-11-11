<%@ page import="grails.converters.JSON" %>
<r:script>
<g:if test="${properties}">_kmq.push(['record', '${event}', ${properties as JSON}]);</g:if>
<g:else>_kmq.push(['record', '${event}']);</g:else>
</r:script>