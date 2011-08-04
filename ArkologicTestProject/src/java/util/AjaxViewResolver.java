package util;
import java.util.Locale;


import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.springframework.web.servlet.View;

import org.springframework.web.servlet.view.AbstractCachingViewResolver;


/**

 * @author dhrubo

 *

 */

public class AjaxViewResolver extends AbstractCachingViewResolver {

    private static final Log log = LogFactory.getLog(AjaxViewResolver.class);

    private String ajaxPrefix;

    private View ajaxView;

    /* (non-Javadoc)

     * @see org.springframework.web.servlet.view.AbstractCachingViewResolver#loadView(java.lang.String, java.util.Locale)

     */

    @Override

    protected View loadView(String viewName, Locale locale) throws Exception {

        log.info("viewName==>"+viewName);

        View view = null;

        if(viewName.startsWith(this.ajaxPrefix)){

            view = ajaxView;

        }

       

        return view;

    }

    public String getAjaxPrefix() {

        return ajaxPrefix;

    }

    public void setAjaxPrefix(String ajaxPrefix) {

        this.ajaxPrefix = ajaxPrefix;

    }

    public View getAjaxView() {

        return ajaxView;

    }

    public void setAjaxView(View ajaxView) {

        this.ajaxView = ajaxView;

    }

 }

