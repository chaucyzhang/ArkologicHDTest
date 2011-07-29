package util;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.springframework.web.servlet.view.AbstractView;


import flexjson.JSONSerializer;


/**

 * @author dhrubo

 *

 */

public class AjaxView extends AbstractView {

    private static final Log log = LogFactory.getLog(AjaxView.class);

    /* (non-Javadoc)

     * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)

     */

    @Override

    protected void renderMergedOutputModel(Map map, HttpServletRequest request,

        HttpServletResponse response) throws Exception {

        log.info("Resolving ajax request view -"+map);

       

        JSONSerializer serializer = new JSONSerializer();

        String jsonString = serializer.serialize(map );

       

        response.setContentType( "text/plain; charset=UTF-8" );

        response.getOutputStream().write( jsonString.getBytes() );


    }


}