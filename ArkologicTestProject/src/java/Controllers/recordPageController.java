package Controllers;

import DAO.DiskStatisticDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class recordPageController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
         ModelAndView mv=new ModelAndView("record");
         DiskStatisticDAO dao=new DiskStatisticDAO();
         List list=dao.queryAllRecord(1,10);
         mv.addObject("record",list);
         mv.addObject("page",1);
       return mv;
    }
}
