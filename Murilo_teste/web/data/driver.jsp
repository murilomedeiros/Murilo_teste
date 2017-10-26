<%-- 
    Document   : driver
    Created on : 25/10/2017, 00:26:51
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

    if (action.equals("addDriver") && !(action.equals(""))) {
        String SQL = "", erro = "";
        String nameDriver = request.getParameter("nameD");
        String dateDriver = request.getParameter("dateD");
        String cpfDriver = request.getParameter("cpfD");
        String carDriver = request.getParameter("carD");
        String genderDriver = request.getParameter("genderD");
        String statusDriver = request.getParameter("statusD");
        if (nameDriver != null && dateDriver != null && cpfDriver != null && carDriver != null && genderDriver != null && statusDriver != null) {
            SQL = "INSERT INTO desafio.tb_driver( name, cpf, car_model, status, gender, date_birth)VALUES ( ?, ?, ?, ?, ?, ?)";
            try {
                int rt;
                rt = Application.Session.executeUpdate(null, SQL, new Object[]{nameDriver, cpfDriver, carDriver, true, genderDriver, dateDriver});
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