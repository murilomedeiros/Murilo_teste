<%-- 
    Document   : client
    Created on : 25/10/2017, 00:27:08
    Author     : murilo
--%>


<%@page import="br.com.deafio.util.DateUtils"%>
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
                rt = Application.Session.executeUpdate(null, SQL, new Object[]{nameClient, cpfClient, genderClient, DateUtils.convertToTimestamp(dateClient)});
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


<%    if (action.equals("getClients") && !(action.equals(""))) {
        String erro = "";
        String SQL = "SELECT id, name, cpf, gender FROM desafio.tb_client ORDER BY id";
        RowSet rs = Application.Session.getRowSet(null, SQL);
        Object[] record;
        int countdown;
        ArrayList<Object[]> dataClients = new ArrayList<>();
        if (rs.size() != 0) {
            while (rs.next()) {
                record = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    record[i] = rs.getString(i + 1).replace("\"", "\\\"").trim().replace("\n", " ");
                }
                dataClients.add(record);
            }
        } else {
            erro = "Algo deu errado.";
        }
        if (dataClients == null && dataClients.isEmpty()) {
            if (erro == null) {
%>
{"re":"Falha ao trazer os dados dos eventos."}
<%          } else {%>
{"re":"<%=erro%>"}
<%
    }
} else {
    countdown = dataClients.size();
%>
{"clients":[
<%for (Object[] row : dataClients) {%>
{"id":<%= row[0]%>,
"name":"<%= row[1]%>",
"cpf":"<%= row[2]%>",
"gender":"<%= row[3]%>"
}
<%if (--countdown > 0) {%>
,
<%}%>
<%} %>
]}

<%        }
    }
%>