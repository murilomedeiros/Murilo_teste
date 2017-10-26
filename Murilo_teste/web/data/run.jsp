<%-- 
    Document   : run
    Created on : 25/10/2017, 00:27:15
    Author     : murilo
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@page import="br.com.desafio.util.RowSet" %>
<%@page import="br.com.conexao.Application"%>

<%
    String action = request.getParameter("action").trim();

    if (action.equals("addRun") && !(action.equals(""))) {
        String SQL = "", erro = "";
        String nameDriver = request.getParameter("driverNameR");
        String nameClient = request.getParameter("clientNameR");
        String price = request.getParameter("priceR");
        if (nameDriver != null && nameClient != null && price != null) {
            SQL = "INSERT INTO desafio.tb_run(client_id, driver_id, price)VALUES ( ?, ?, ?)";
            try {
                int rt;
                rt = Application.Session.executeUpdate(null, SQL, new Object[]{nameDriver, nameClient, price});
                if (rt > 0) {
                    System.out.println("motorista cadastrado com sucesso");
                } else {
                    erro = "erro na inscrição";
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw ex;
            }
        }
    }

%>
