<#import "/spring.ftl" as spring/>
<html>
<head><title>Get sample object from cloud HashMap</title>
<body>
<div id="header">
<H2>
	Get sample object from cloud HashMap
</H2>
</div>

<div id="content">
  <form action="" method="POST">
      Key:
      <input type="text" name="key" /><br />
      <input type="submit" value="get"/>
  </form>
  <#if sampleObject??>
      String field: ${sampleObject.stringField}<br />
      Integer field: ${sampleObject.integerField}<br />
  <#else>
      No value for given key
  </#if>
</div>  
</body>
</html>  