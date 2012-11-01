/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gruporon2005.aquarius.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author yrch
 */
public abstract class GenericAction extends Action
{

    /**
     * @param mapping mapping
     * @param form form
     * @param request request
     * @param response response
     * @throws Exception Exception
     * @return ActionForward
     *
     */
    @Override
    public abstract ActionForward execute(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception;


}
