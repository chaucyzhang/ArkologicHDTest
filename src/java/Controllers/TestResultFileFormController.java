/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Diskstatistics;
import Models.TestResultFileBean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
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
        HttpSession ss=request.getSession();
        TestResultFileBean testfile = (TestResultFileBean) command;
         MultipartFile file=testfile.getFile();
         if (file == null) {   
                throw new Exception("Upload Failed,file content is null");     
            }   
            if(file.getSize()>100000)        
            {   
                throw new Exception("Uploada Failed, file size is to large");               
            }   
            String filename=file.getOriginalFilename();
            Session session=null;
         if(file.getSize()>0){     
            try {   
                String path ="../webapps/ArkologicTestPorject/TestFiles/";
                    SaveFileFromInputStream(file.getInputStream(),path,filename);   
                    IostatParser ip=new IostatParser(path+filename);
                    List<Hashtable<String,String>> jsonFormatList=new ArrayList<Hashtable<String,String>>();
                    jsonFormatList=ip.getFileJsonFormat();
                    ss.setAttribute("diskList",jsonFormatList);
                    for(int i=0;i<jsonFormatList.size();i++){
                    Hashtable<String,String> ht=new Hashtable<String,String>();
                        ht=jsonFormatList.get(i);
                        SessionFactory sessionfactory=new Configuration().configure().buildSessionFactory();
                        Diskstatistics ds=new Diskstatistics();
                       ds.setDevice(ht.get("device"));
                        ds.setRps(Float.parseFloat(ht.get("rps")));
                        ds.setWps(Float.parseFloat(ht.get("wps")));
                        ds.setKrps(Float.parseFloat(ht.get("krps")));
                        ds.setKwps(Float.parseFloat(ht.get("kwps")));
                        ds.setWait(Float.parseFloat(ht.get("wait")));
                        ds.setActv(Float.parseFloat(ht.get("actv")));
                        ds.setSvct(Float.parseFloat(ht.get("svc_t")));
                        ds.setW(Integer.parseInt(ht.get("w")));
                        ds.setB(Integer.parseInt(ht.get("b")));
                        Date date = new java.util.Date();
                        Timestamp tstamp = new java.sql.Timestamp( date.getTime() );
                        ds.setCreatetime(tstamp);
                        session=sessionfactory.openSession();
                        Transaction tr=session.beginTransaction();
                        session.save(ds);
                        tr.commit();
                        session.close();
                    }
                } catch (IOException e) {   
                    System.out.println(e.getMessage());   
                    return null;   
                }
         }
         else{
        throw new Exception("File can't be null");
         }
             
        return new ModelAndView("result");

    }
     public void SaveFileFromInputStream(InputStream stream,String path, String filename) throws IOException,FileNotFoundException   
    {         
        if(!new File(path).isDirectory())
        {
            new File(path).mkdirs();
        }
        FileOutputStream fs=new FileOutputStream(path+filename);   
        byte[] buffer =new byte[1024*1024];   
        int bytesum = 0;   
        int byteread = 0;    
        while ((byteread=stream.read(buffer))!=-1)   
        {   
           bytesum+=byteread;   
           fs.write(buffer,0,byteread);   
           fs.flush();   
        }    
        fs.close();   
        stream.close();         
    }         


}
