package to.adapt.neba.impl.contact;

import io.neba.api.annotations.ResourceParam;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.JobManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Demonstrates how Spring MVC and Sling cooperate when using NEBA.
 *
 * @author Olaf Otto
 */
@Controller
public class ContactController {
    @Resource
    private JobManager jobManager;

    @RequestMapping(value = "/contact", method = POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Job add(Contact contact) {
        Map<String, Object> properties = contact.toMap();
        return jobManager.addJob("adaptto/contact/request", properties);
    }

    @ModelAttribute
    public Contact prepareFormModel(@ResourceParam Contact contact) {
        return contact;
    }
}
