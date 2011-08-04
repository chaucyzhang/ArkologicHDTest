/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.DiskStatisticDAO;
import Models.Page;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/** 
 *
 * @author Administrator
 */
public class PagenateFormController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List list = new ArrayList();
        JSONArray ja = new JSONArray();
        try {
            Page page = new Page();
            page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
            DiskStatisticDAO dao = new DiskStatisticDAO();
            list = dao.queryNextRecord(page.getCurrentPage() + 1, 10);
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String date = map.get("createtime").toString();
                map.put("createtime", date);
                ja.add(map);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ModelAndView mv = new ModelAndView("ajax_result", "json", ja.toJSONString());
        return mv;
    }
}
