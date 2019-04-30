import com.atlassian.jira.issue.Issue
import com.atlassian.jira.component.ComponentAccessor
import org.apache.log4j.Level
import org.apache.log4j.Logger

String className = "tr.com.obss.Scripts.totalmobiledev"
Logger log = Logger.getLogger(className)
log.setLevel(Level.DEBUG)
log.debug ("Script " + className + " started")

def mobiledev = ComponentAccessor.getCustomFieldManager().getCustomFieldObject('customfield_12616')
String mobilenum = issue.getCustomFieldValue(mobiledev)
def backenddev = ComponentAccessor.getCustomFieldManager().getCustomFieldObject('customfield_12618')
String backendnum = issue.getCustomFieldValue(backenddev)
def frontenddev = ComponentAccessor.getCustomFieldManager().getCustomFieldObject('customfield_12619')
String frontendnum = issue.getCustomFieldValue(frontenddev)
def Calculated

log.warn("Response 1 : "+ mobilenum )
log.warn("Response 2 : "+ backendnum )
log.warn("Response 3 : "+ frontendnum )

if (issue.getCustomFieldValue(mobiledev) != null && issue.getCustomFieldValue(backenddev) != null && issue.getCustomFieldValue(frontenddev) != null ) {
    Calculated = mobilenum.toDouble() +  backendnum.toDouble() + frontendnum.toDouble()
}
else if (issue.getCustomFieldValue(mobiledev) != null && issue.getCustomFieldValue(backenddev) != null) {
    Calculated = mobilenum.toDouble() +  backendnum.toDouble()
}
else if (issue.getCustomFieldValue(backenddev) != null && issue.getCustomFieldValue(frontenddev) != null){
    Calculated = backendnum.toDouble() + frontendnum.toDouble()
}
else if (issue.getCustomFieldValue(mobiledev) != null && issue.getCustomFieldValue(frontenddev) != null){
    Calculated = mobilenum.toDouble() +  frontendnum.toDouble()
}
else if (issue.getCustomFieldValue(mobiledev) != null){
    Calculated = mobilenum.toDouble()
}
else if (issue.getCustomFieldValue(frontenddev) != null){
    Calculated = frontendnum.toDouble()
}
else if (issue.getCustomFieldValue(backenddev) != null){
    Calculated = backendnum.toDouble()
}
else {
    return
}

return Calculated