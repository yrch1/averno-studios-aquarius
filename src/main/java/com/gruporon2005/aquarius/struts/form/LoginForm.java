/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruporon2005.aquarius.struts.form;


/**
 *
 * @author yrch
 */
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author eswar@vaannila.com
 */
public class LoginForm extends ActionForm {

    private String userName;

    private String password;


    /**
     *
     */
    public LoginForm() {
        super();
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (userName == null || userName.length() < 1) {
            errors.add("userName", new ActionMessage("error.userName.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        if (password == null || password.length() < 1) {
            errors.add("password", new ActionMessage("error.password.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
