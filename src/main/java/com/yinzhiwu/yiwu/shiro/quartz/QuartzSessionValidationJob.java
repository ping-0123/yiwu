package com.yinzhiwu.yiwu.shiro.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzSessionValidationJob implements Job {
	 /** 
     * Key used to store the session manager in the job data map for this job. 
     */  
    public static final String SESSION_MANAGER_KEY = "sessionManager";  

    /*-------------------------------------------- 
    |    I N S T A N C E   V A R I A B L E S    | 
    ============================================*/  
    private static final Log log = LogFactory.getLog(QuartzSessionValidationJob.class);  

    /*-------------------------------------------- 
    |         C O N S T R U C T O R S           | 
    ============================================*/  

    /*-------------------------------------------- 
    |  A C C E S S O R S / M O D I F I E R S    | 
    ============================================*/  

    /*-------------------------------------------- 
    |               M E T H O D S               | 
    ============================================*/  

    /** 
     * Called when the job is executed by quartz. This method delegates to the <tt>validateSessions()</tt> method on the 
     * associated session manager. 
     * 
     * @param context 
     *            the Quartz job execution context for this execution. 
     */  
    public void execute(JobExecutionContext context) throws JobExecutionException {  

        JobDataMap jobDataMap = context.getMergedJobDataMap();  
        ValidatingSessionManager sessionManager = (ValidatingSessionManager) jobDataMap.get(SESSION_MANAGER_KEY);  

        if (log.isDebugEnabled()) {  
            log.debug("Executing session validation Quartz job...");  
        }  

        sessionManager.validateSessions();  

        if (log.isDebugEnabled()) {  
            log.debug("Session validation Quartz job complete.");  
        }  
    }  

}  