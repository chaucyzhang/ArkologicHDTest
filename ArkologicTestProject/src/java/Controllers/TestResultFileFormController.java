package Controllers;

import DAO.DiskStatisticDAO;
import Models.TestResultFileBean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import util.IostatParser;

/** 
 *
 * @author Administrator
 */
public class TestResultFileFormController extends SimpleFormController {

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        HttpSession ss = request.getSession();
        TestResultFileBean testfile = (TestResultFileBean) command;
        MultipartFile file = testfile.getFile();
        if (file == null) {
            throw new Exception("Upload Failed,file content is null");
        }
        if (file.getSize() > 100000) {
            throw new Exception("Uploada Failed, file size is to large");
        }
        String filename = file.getOriginalFilename();
        Session session = null;
        if (file.getSize() > 0) {
            try {
                String path = "../webapps/ArkologicTestPorject/TestFiles/";
                SaveFileFromInputStream(file.getInputStream(), path, filename);
                IostatParser ip = new IostatParser(path + filename);
                List<Hashtable<String, String>> jsonFormatList = new ArrayList<Hashtable<String, String>>();
                jsonFormatList = ip.getFileJsonFormat();
                ss.setAttribute("diskList", jsonFormatList);
                for (int i = 0; i < jsonFormatList.size(); i++) {
                    Hashtable<String, String> ht = new Hashtable<String, String>();
                    ht = jsonFormatList.get(i);
                    DiskStatisticDAO dao = new DiskStatisticDAO();
                    try {
                        dao.saveStatistics(ht.get("device"), ht.get("rps"), ht.get("wps"), ht.get("krps"), ht.get("kwps"), ht.get("wait"), ht.get("actv"), ht.get("svc_t"), ht.get("w"), ht.get("b"));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            throw new Exception("File can't be null");
        }

        return new ModelAndView("result");

    }

    public void SaveFileFromInputStream(InputStream stream, String path, String filename) throws IOException, FileNotFoundException {
        if (!new File(path).isDirectory()) {
            new File(path).mkdirs();
        }
        FileOutputStream fs = new FileOutputStream(path + filename);
        byte[] buffer = new byte[1024 * 1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = stream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }
}
