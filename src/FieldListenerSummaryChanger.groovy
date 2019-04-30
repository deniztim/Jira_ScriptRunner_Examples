import com.atlassian.jira.user.util.UserManager
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.ModifiedValue
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.event.type.EventDispatchOption
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.issue.UpdateIssueRequest
import com.atlassian.jira.event.issue.IssueEvent

def issueManager = ComponentAccessor.getIssueManager()
UserManager userManager = ComponentAccessor.getUserManager()

def issue = event.issue as Issue
def issueKey = issue.getKey()
issue = issueManager.getIssueObject(issueKey);

def exsummary = issue.getSummary()
def codeisin = ComponentAccessor.getCustomFieldManager().getCustomFieldObject('customfield_12609')
String codeisinvalue = issue.getCustomFieldValue(codeisin)

if (exsummary.contains("RELEASE")==false && codeisinvalue=="Release"){
String newsummary = "RELEASE - " + exsummary;
issue.setSummary(newsummary);
issueManager.updateIssue(event.getUser(),issue,EventDispatchOption.DO_NOT_DISPATCH,false)
}
else {
    return null
}

//end