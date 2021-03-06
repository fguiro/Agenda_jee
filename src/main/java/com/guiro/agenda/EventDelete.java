/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.guiro.agenda;

import com.guiro.agenda.doa.EventDOA;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guiro
 */
@WebServlet(name = "EventDelete", urlPatterns = {"/eventdelete"})
public class EventDelete extends HttpServlet {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = getServletContext().getNamedDispatcher("EventList");
        dispatcher.forward(request, response) ;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession().getAttribute("user_id") != null){
            EventDOA eventManager;
            try {
		eventManager = new EventDOA();
		int id = Integer.parseInt(request.getParameter("id")) ;
	        
                if(eventManager.delete_event(id)){
                    request.setAttribute("message", "Delete successed...");
                }
                else{
                    request.setAttribute("errors", "Falied to delete");
                }
            } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
            }
                
            processRequest(request, response);
        }else{
            request.setAttribute("error", "Identifiez vous d'abord pour acceder aux pages.");
            getServletContext().getNamedDispatcher("Login").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession().getAttribute("user_id") != null){
            processRequest(request, response);
        }else{
            request.setAttribute("error", "Identifiez vous d'abord pour acceder aux pages.");
            getServletContext().getNamedDispatcher("Login").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
