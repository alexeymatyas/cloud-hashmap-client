<#import "/spring.ftl" as spring/>
<html>
<head><title>Put sample object to cloud HashMap</title>
<body>
<div id="header">
<H2>
Put sample object to cloud HashMap
</H2>
</div>

<div id="content">
<form action="" method="POST">
  Key:
  <input type="text" name="key" /><br /><br /><br />
  String field:
  <@spring.bind "sampleObject.stringField"/>
  <input type="text"
      name="${spring.status.expression}"
      value="${spring.status.value?default("")}"/><br />
  Integer field:
  <@spring.bind "sampleObject.integerField"/>
  <input type="text"
      name="${spring.status.expression}"
      value="${spring.status.value?default("0")}"/><br />
  <input type="submit" value="put"/>
</form>
<#if message??>
    ${message}
</#if>
</div>  
</body>
</html>  