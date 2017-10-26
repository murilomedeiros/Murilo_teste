<%-- 
    Document   : client
    Created on : 25/10/2017, 00:27:08
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

    if (action.equals("addClient") && !(action.equals(""))) {
        String SQL = "", erro = "";
        String nameClient = request.getParameter("nameC");
        String dateClient = request.getParameter("dateC");
        String cpfClient = request.getParameter("cpfC");
        String genderClient = request.getParameter("genderC");
        if (nameClient != null && dateClient != null && cpfClient != null && genderClient != null) {
            SQL = "INSERT INTO desafio.tb_client(name, cpf, gender, date_birth)VALUES ( ?, ?, ?, ?)";
            try {
                int rt;
                rt = Application.Session.executeUpdate(null, SQL, new Object[]{nameClient, cpfClient, genderClient, dateClient});
                if (rt > 0) {
                    System.out.println("cliente cadastrado com sucesso");
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