package DAO;

import Models.Diskstatistics;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;
import util.HibernateUtil;

public class DiskStatisticDAO {

    public void saveStatistics(String device, String rps, String wps, String krps, String kwps, String wait, String actv, String svct, String w, String b) {
        Session session = null;
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
        Diskstatistics ds = new Diskstatistics();
        ds.setDevice(device);
        ds.setRps(Float.parseFloat(rps));
        ds.setWps(Float.parseFloat(wps));
        ds.setKrps(Float.parseFloat(krps));
        ds.setKwps(Float.parseFloat(kwps));
        ds.setWait(Float.parseFloat(wait));
        ds.setActv(Float.parseFloat(actv));
        ds.setSvct(Float.parseFloat(svct));
        ds.setW(Integer.parseInt(w));
        ds.setB(Integer.parseInt(b));
        Date date = new java.util.Date();
        Timestamp tstamp = new java.sql.Timestamp(date.getTime());
        ds.setCreatetime(tstamp);
        session = sessionfactory.openSession();
        Transaction tr = session.beginTransaction();
        session.save(ds);
        tr.commit();
        session.close();
    }
    
    public List queryAllRecord(int page,int pageSize){
           Session session=HibernateUtil.getSessionFactory().getCurrentSession();
       session.beginTransaction();
       Query qr=session.createQuery("from Diskstatistics order by createtime desc");
       qr.setFirstResult((page-1)*pageSize);
       qr.setMaxResults(pageSize);
      // List result = session.createQuery("from Diskstatistics order by createtime desc").list();
       List result=qr.list();
   //    session.getTransaction().commit();
       session.close();
       return result;
    
    }
public List queryNextRecord(int page,int pageSize){
           Session session=HibernateUtil.getSessionFactory().getCurrentSession();
       session.beginTransaction();
       Query qr=session.createSQLQuery("select * from Diskstatistics order by createtime desc").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
       qr.setFirstResult((page-1)*pageSize);
       qr.setMaxResults(pageSize);
      // List result = session.createQuery("from Diskstatistics order by createtime desc").list();
       List result=qr.list();
   //    session.getTransaction().commit();

       session.close();
       return result;
    
    }

}
