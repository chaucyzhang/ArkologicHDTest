package Controllers;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ResultController implements Controller {

    protected final Log log = LogFactory.getLog(getClass());

    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        log.info("Returning ajax view");
        HttpSession session = request.getSession();
        List<Hashtable<String, String>> jList = new ArrayList<Hashtable<String, String>>();
        jList = (List) session.getAttribute("diskList");
        JSONArray ja = new JSONArray();
        for (int i = 0; i < jList.size(); i++) {
            Hashtable<String, String> ht = new Hashtable<String, String>();
            ht = jList.get(i);
            ja.add(ht);
        }
        ModelAndView mv = new ModelAndView("ajax_result", "json", ja.toJSONString());
        return mv;
    }
}
