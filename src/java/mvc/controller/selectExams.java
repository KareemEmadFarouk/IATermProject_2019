/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.model.ObjectsIO.HR_IO;

/**
 *
 * @author Kareem E.Farouk
 */
@WebServlet(name = "selectExams", urlPatterns = {"/selectExams"})
public class selectExams extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            HR_IO hrio = new HR_IO();
            String ordering = request.getParameter("OrdersStr"); //get all approved candidates Order_Exams
            String[] id_can = request.getParameterValues("Approved"); //get all approved candidates
            hrio.approveCandidate(id_can); // Store approval for all of them
            String[] temp = ordering.split("/");
            for (int i = 0; i < id_can.length; i++) {
                String[] temp2 = temp[i+1].split("-");
                //for (int j = 0; j < temp2.length; j++) {
                  //       out.print(id_can[i] + "--> " + temp2[j]);    
                //}
                //out.print("<br>");
                hrio.toHRExamTable(id_can[i], temp2);
            }
            response.sendRedirect("HR_Home.jsp");
        } 
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}