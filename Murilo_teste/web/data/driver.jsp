<%-- 
    Document   : driver
    Created on : 25/10/2017, 00:26:51
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

    if (action.equals("addDriver") && !(action.equals(""))) {
        String SQL = "", SQL2 = "", erro = "";
        String nameDriver = request.getParameter("nameD");
        String dateDriver = request.getParameter("dateD");
        String cpfDriver = request.getParameter("cpfD");
        String carDriver = request.getParameter("carD");
        String genderDriver = request.getParameter("genderD");
        if (nameDriver != null && dateDriver != null && cpfDriver != null && carDriver != null && genderDriver != null) {
            SQL = "SELECT id FROM desafio.tb_driver WHERE cpf = ?";
            SQL2 = "INSERT INTO desafio.tb_driver( name, cpf, car_model, status, gender, date_birth)VALUES ( ?, ?, ?, ?, ?, ?)";
            RowSet rs = Application.Session.getRowSet(null, SQL, new Object[]{cpfDriver});
            if (rs.size() == 0) {
                try {
                    int rt;
                    rt = Application.Session.executeUpdate(null, SQL2, new Object[]{nameDriver, cpfDriver, carDriver, true, genderDriver, DateUtils.convertToTimestamp(dateDriver)});
                    if (rt > 0) {
                        System.out.println("motorista cadastrado com sucesso");
                    } else {
                        System.out.println("erro no cadastrado do motorista");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw ex;
                }
            } else {
                System.out.println("Motorista já cadastrado no sistema");
            }
        }

    }

%>


<%    if (action.equals("getDrivers") && !(action.equals(""))) {
        String erro = "";
        String SQL = "SELECT id, name, cpf, car_model, gender, status, status FROM desafio.tb_driver ORDER BY id";
        RowSet rs = Application.Session.getRowSet(null, SQL);
        Object[] record;
        int countdown;
        ArrayList<Object[]> dataDrivers = new ArrayList<>();
        if (rs.size() != 0) {
            while (rs.next()) {
                record = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    record[i] = rs.getString(i + 1).replace("\"", "\\\"").trim().replace("\n", " ");
                }
                dataDrivers.add(record);
            }
        } else {
            erro = "Algo deu errado.";
        }
        if (dataDrivers == null && dataDrivers.isEmpty()) {
            if (erro == null) {
%>
{"re":"Falha ao trazer os dados dos eventos."}
<%          } else {%>
{"re":"<%=erro%>"}
<%
    }
} else {
    countdown = dataDrivers.size();
%>
{"drivers":[
<%for (Object[] row : dataDrivers) {%>
{"id":<%= row[0]%>,
"name":"<%= row[1]%>",
"cpf":"<%= row[2]%>",
"car":"<%= row[3]%>",
"gender":"<%= row[4]%>",
"status":"<%= row[5]%>",
"status2":"<%= row[5]%>"
}
<%if (--countdown > 0) {%>
,
<%}%>
<%} %>
]}

<%        }
    }
%>

<%
    if (action.equals("changeStatus") && !(action.equals(""))) {
        boolean status = Boolean.parseBoolean(request.getParameter("status").toString());
        int idDriver = Integer.parseInt(request.getParameter("idDriver").toString());
        String SQL = "SELECT name FROM desafio.tb_driver WHERE id = ?";
        String SQL2 = "UPDATE desafio.tb_driver SET status = ? WHERE id = ?";
        RowSet rs = Application.Session.getRowSet(null, SQL, new Object[]{idDriver});
        if (rs.size() != 0) {
            int rtw = Application.Session.executeUpdate(null, SQL2, new Object[]{status, idDriver});
            if (rtw > 0) {
                System.out.println("status alterado com sucesso");
            } else {
               System.out.println("erro na alteração do status");
            }
        } else {
           System.out.println("motorista não cadastrado");
        }
    }
%>