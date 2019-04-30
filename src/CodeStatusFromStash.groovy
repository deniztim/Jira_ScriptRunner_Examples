import groovy.json.JsonBuilder
import com.atlassian.jira.config.properties.APKeys;
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.core.Response
import org.apache.log4j.Level
import org.apache.log4j.Logger
import com.atlassian.jira.component.ComponentAccessor
import sun.misc.BASE64Encoder;
import com.atlassian.jira.util.json.JSONArray
import com.atlassian.jira.util.json.JSONObject
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

String className = "tr.com.obss.Scripts.codestatusfield"
Logger log = Logger.getLogger(className)
log.setLevel(Level.DEBUG)
log.debug ("Script " + className + " started")

def status = issue.getStatus().getName()
String response = getResponse()
log.warn("Response : "+ response )

if (status == "Ready for Dev"){
    String codestatus = "Ready for Development"
    return codestatus
}
else if (status == "Coding"){
    String codestatus = "Branch"
    return codestatus
}
else if (response.contains("into release")){
    String codestatus = "Release"
    return codestatus
}
else if (response.contains("into prod")){
    String codestatus = "In Prod"
    return codestatus
}
else if (response.contains("into preMaster")){
    String codestatus = "In Pre-Master"
    return codestatus
}
else {
    return null
}

public String getResponse(){

    String url = getBaseUrl() + "/rest/dev-status/latest/issue/detail?issueId="+issue.getId()+"&applicationType=stash&dataType=repository";
    log.warn("URL : " + url)

    URL urlObject = new URL(url)
    HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection()
    connection = (HttpURLConnection) urlObject.openConnection()
    String encoded = Base64.getEncoder().encodeToString(("username:pass").getBytes(StandardCharsets.UTF_8));  //Java 8
    connection.setRequestProperty("Authorization", "Basic "+encoded);
    connection.setRequestProperty("Content-Type", "application/json;charset="+"UTF-8")
    connection.setRequestMethod("GET")

    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))
    StringBuilder result = new StringBuilder()
    String line
    while((line = reader.readLine()) != null) {
        result.append(line)
    }
    reader.close()

    return result.toString()
}

public String getBaseUrl(){
    return  ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL);

}