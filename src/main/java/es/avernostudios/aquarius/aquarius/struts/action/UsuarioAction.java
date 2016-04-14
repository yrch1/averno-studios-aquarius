/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.avernostudios.aquarius.aquarius.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author yrch
 */
public class UsuarioAction extends GenericAction{


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
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception
    {
        ActionForward result = mapping.findForward("success");
        return result;
    }

}
