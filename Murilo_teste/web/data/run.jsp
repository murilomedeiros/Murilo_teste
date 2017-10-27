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
    //    <!-- Inserindo corrida no banco -->
    if (action.equals("addRun") && !(action.equals(""))) {
        String SQL = "", erro = "", SQL2 = "";
        String driverIdR = request.getParameter("driverIdR");
        String clientCpfR = request.getParameter("clientCpfR");
        String price = request.getParameter("priceR");
        String idC = ""; 
        if (driverIdR != null && clientCpfR != null && price != null) {
            SQL = "SELECT id FROM desafio.tb_client WHERE cpf = ?";
            SQL2 = "INSERT INTO desafio.tb_run(client_id, driver_id, price)VALUES ( ?, ?, ?)";
            RowSet rs = Application.Session.getRowSet(null, SQL, new Object[]{clientCpfR});
            if (rs.size() != 0) {
                while (rs.next()) {
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        idC = rs.getString(i + 1);
                    }
                }
                try {
                    int rt;
                    rt = Application.Session.executeUpdate(null, SQL2, new Object[]{Integer.parseInt(idC), Integer.parseInt(driverIdR), price});
                    if (rt > 0) {
                        System.out.println("corrida cadastrada com sucesso");
                    } else {
                        System.out.println("erro na inscrição");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw ex;
                }
            } else {
                System.out.println("cliente não cadastrado no sistema");
            }
        }
    }

%>


<% if (action.equals("selectDrivers")) {
        //    <!-- Lista dos motoristas para cadastro da corrida -->
        ArrayList<Object[]> driverArray = new ArrayList<>();

        try {
            String query = "SELECT id, name FROM desafio.tb_driver WHERE status = true;";
            RowSet rs = Application.Session.getRowSet(null, query);

            while (rs.next()) {
                Object[] record = new Object[(rs.getMetaData().getColumnCount())];
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    record[i] = rs.getString(i + 1);
                }
                driverArray.add(record);
            }
        } catch (Exception e) {
            e.getMessage();
        }

%>

<% if (!driverArray.isEmpty()) {%>
{
"driverArray":[
<% int countdown = driverArray.size(); %>
<%for (Object[] row : driverArray) {%>
{
"id":<%= row[0]%>,
"name":"<%= row[1]%>"
}
<%if (--countdown > 0) {%>
,
<%}%>
<%} %>
]
}
<%}%>
<%}%>