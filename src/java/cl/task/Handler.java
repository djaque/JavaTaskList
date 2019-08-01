/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dany
 */
public class Handler extends HttpServlet {

    private List<Task> taskList;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * This method is used to complete one task
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (null == this.taskList) {
            this.taskList = new ArrayList<>();
        }
        
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        
        if (null != id && null != action) {
            if (action.equals("complete")) {
                int i = Integer.parseInt(id);
                this.taskList.get(i).setIsDone(Boolean.TRUE);
            }
        }
        
        request.setAttribute("tasklist", this.taskList);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * This method is used to create a new task
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (null == this.taskList) {
            this.taskList = new ArrayList<>();
        }
        String text = request.getParameter("text");
        if (null != text) {
            Task t = new Task(this.taskList.size(), text);
            this.taskList.add(t);
        }
        
        request.setAttribute("tasklist", this.taskList);
        request.getRequestDispatcher("index.jsp").forward(request, response);

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
