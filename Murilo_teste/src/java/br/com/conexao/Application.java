/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.conexao;

import br.com.desafio.util.RowSet;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author murilo
 */
public class Application {

    public static class Session {

        private static String DB_DRIVER = "org.postgresql.Driver";
        private static String DB_CONNECTION = "jdbc:postgresql://localhost:5432/postgres";
        private static String DB_USER = "postgres";
        private static String DB_PASSWORD = "pwd";

        public static Connection getConnection(HttpServletRequest request) throws SQLException, NamingException, Exception {
            java.sql.Connection dbConnection = null;

            try {

                Class.forName(DB_DRIVER);

            } catch (ClassNotFoundException e) {

                System.out.println(e.getMessage());

            }

            try {

                dbConnection = DriverManager.getConnection(
                        DB_CONNECTION, DB_USER, DB_PASSWORD);
                return dbConnection;

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }

            return dbConnection;
        }

        public static Object[] executeScalarQuery(HttpServletRequest request, String SQL) throws SQLException, NamingException, Exception {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                conn = getConnection(request);
                stmt = conn.createStatement();
                rs = stmt.executeQuery(SQL);
                Object values[] = null;
                if (rs.next()) {
                    values = new Object[rs.getMetaData().getColumnCount()];
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        values[i] = rs.getObject(i + 1);
                    }
                }
                rs.close();
                stmt.close();
                conn.close();
                return values;
            } catch (SQLException ex) {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } finally {
                    rs = null;
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } finally {
                    stmt = null;
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } finally {
                    conn = null;
                }
                throw new SQLException(ex);
            }
        }

        /**
         *
         * @param request
         * @param SQL
         * @param email
         * @param password
         * @return
         * @throws SQLException
         * @throws NamingException
         * @throws Exception
         */
        public static Object[] executeScalarQuery(HttpServletRequest request, String SQL, Object[] parameters) throws SQLException, NamingException, Exception {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                conn = getConnection(request);
                stmt = conn.prepareStatement(SQL);

                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i].getClass().equals(BigDecimal.class)) {
                        stmt.setBigDecimal(i + 1, (BigDecimal) parameters[i]);
                    } else if (parameters[i].getClass().equals(Integer.class)) {
                        stmt.setInt(i + 1, ((Integer) (parameters[i])).intValue());
                    } else if (parameters[i].getClass().equals(Long.class)) {
                        stmt.setLong(i + 1, ((Long) (parameters[i])).longValue());
                    } else if (parameters[i].getClass().equals(String.class)) {
                        if ("NULL".equalsIgnoreCase((String) parameters[i])) {
                            stmt.setString(i + 1, null);
                        } else {
                            stmt.setString(i + 1, (String) parameters[i]);
                        }
                    } else if (parameters[i].getClass().equals(byte[].class)) {
                        stmt.setBytes(i + 1, (byte[]) parameters[i]);
                    } else if (parameters[i] instanceof InputStream) {
                        stmt.setBinaryStream(i + 1, (InputStream) parameters[i]);
                    } else {
                        stmt.setObject(i + 1, parameters[i]);
                    }
                }
                rs = stmt.executeQuery();
                Object values[] = null;
                if (rs.next()) {
                    values = new Object[rs.getMetaData().getColumnCount()];
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        values[i] = rs.getObject(i + 1);
                    }
                }
                rs.close();
                stmt.close();
                conn.close();
                return values;
            } catch (SQLException ex) {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } finally {
                    rs = null;
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } finally {
                    stmt = null;
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } finally {
                    conn = null;
                }
                throw new SQLException(ex);
            }
        }

        public static int executeUpdate(HttpServletRequest request, String SQL) throws SQLException, NamingException, Exception {
            Connection conn = null;
            Statement stmt = null;
            try {
                conn = getConnection(request);
                stmt = conn.createStatement();
                int r = stmt.executeUpdate(SQL);
                stmt.close();
                conn.close();
                return r;
            } catch (SQLException ex) {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } finally {
                    stmt = null;
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } finally {
                    conn = null;
                }
                throw new SQLException(ex);
            }
        }

        public static int executeUpdate(HttpServletRequest request, String SQL, Object[] parameters) throws SQLException, NamingException, Exception {
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = getConnection(request);
                stmt = conn.prepareStatement(SQL);

                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i].getClass().equals(BigDecimal.class)) {
                        stmt.setBigDecimal(i + 1, (BigDecimal) parameters[i]);
                    } else if (parameters[i].getClass().equals(Integer.class)) {
                        stmt.setInt(i + 1, ((Integer) (parameters[i])).intValue());
                    } else if (parameters[i].getClass().equals(Long.class)) {
                        stmt.setLong(i + 1, ((Long) (parameters[i])).longValue());
                    } else if (parameters[i].getClass().equals(String.class)) {
                        if ("NULL".equalsIgnoreCase((String) parameters[i])) {
                            stmt.setString(i + 1, null);
                        } else {
                            stmt.setString(i + 1, (String) parameters[i]);
                        }
                    } else if (parameters[i].getClass().equals(byte[].class)) {
                        stmt.setBytes(i + 1, (byte[]) parameters[i]);
                    } else if (parameters[i] instanceof InputStream) {
                        stmt.setBinaryStream(i + 1, (InputStream) parameters[i]);
                    } else {
                        stmt.setObject(i + 1, parameters[i]);
                    }
                }

                int r = stmt.executeUpdate();
                stmt.close();
                conn.close();
                return r;
            } catch (SQLException ex) {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } finally {
                    stmt = null;
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } finally {
                    conn = null;
                }
                throw new SQLException(ex);
            }
        }

        public static int[] executeBatchUpdate(HttpServletRequest request, String[] queries) throws SQLException, NamingException, Exception {
            if (queries == null) {
                throw new SQLException("A lista de queries está nula");
            }
            for (String query : queries) {
                if (query == null) {
                    throw new SQLException("Query nula no array de queries");
                }
            }
            int[] results = new int[queries.length];
            Connection conn = null;
            Statement stmt = null;
            try {
                conn = getConnection(request);
                conn.setAutoCommit(false);
                for (int i = 0; i < queries.length; i++) {
                    String SQL = queries[i];
                    stmt = conn.createStatement();
                    results[i] = stmt.executeUpdate(SQL);
                    stmt.close();
                }
                conn.commit();
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } finally {
                    stmt = null;
                }
                try {
                    if (conn != null) {
                        conn.rollback();
                        conn.setAutoCommit(true);
                    }
                } finally {
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } finally {
                    conn = null;
                }
                throw new SQLException(ex);
            }
            return results;
        }

        public static int[] executeBatchUpdate(HttpServletRequest request, String[] queries, List<Object[]> parametersList) throws SQLException, NamingException, Exception {
            if (queries == null) {
                throw new SQLException("A lista de queries está nula");
            }
            for (String query : queries) {
                if (query == null) {
                    throw new SQLException("Query nula no array de queries");
                }
            }
            if (parametersList == null) {
                throw new SQLException("A lista de parâmetros está nula");
            }
            for (Object[] parameters : parametersList) {
                if (parameters == null) {
                    throw new SQLException("Array de parâmetros nulo na lista de parâmetros");
                }
                for (Object parameter : parameters) {
                    if (parameter == null) {
                        throw new SQLException("Parâmetro nulo no array de parâmetros da lista de parâmetros");
                    }
                }
            }
            int[] results = new int[queries.length];
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = getConnection(request);
                conn.setAutoCommit(false);
                for (int i = 0; i < queries.length; i++) {
                    String SQL = queries[i];
                    Object[] parameters = parametersList.get(i);
                    stmt = conn.prepareStatement(SQL);
                    for (int j = 0; j < parameters.length; j++) {
                        if (parameters[j].getClass().equals(BigDecimal.class)) {
                            stmt.setBigDecimal(j + 1, (BigDecimal) parameters[j]);
                        } else if (parameters[j].getClass().equals(Integer.class)) {
                            stmt.setInt(j + 1, ((Integer) (parameters[j])).intValue());
                        } else if (parameters[j].getClass().equals(Long.class)) {
                            stmt.setLong(j + 1, ((Long) (parameters[j])).longValue());
                        } else if (parameters[j].getClass().equals(String.class)) {
                            if ("NULL".equalsIgnoreCase((String) parameters[j])) {
                                stmt.setString(j + 1, null);
                            } else {
                                stmt.setString(j + 1, (String) parameters[j]);
                            }
                        } else if (parameters[j].getClass().equals(byte[].class)) {
                            stmt.setBytes(j + 1, (byte[]) parameters[j]);
                        } else if (parameters[j] instanceof InputStream) {
                            stmt.setBinaryStream(j + 1, (InputStream) parameters[j]);
                        } else {
                            stmt.setObject(j + 1, parameters[j]);
                        }
                    }
                    results[i] = stmt.executeUpdate(SQL);
                    stmt.close();
                }
                conn.commit();
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } finally {
                    stmt = null;
                }
                try {
                    if (conn != null) {
                        conn.rollback();
                        conn.setAutoCommit(true);
                    }
                } finally {
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } finally {
                    conn = null;
                }
                throw new SQLException(ex);
            }
            return results;
        }

        public static RowSet getRowSet(HttpServletRequest request, String SQL) throws SQLException, NamingException, Exception {
            Connection conn = getConnection(request);
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(SQL);
                RowSet rowSet = new RowSet(rs);
                rs.close();
                stmt.close();
                conn.close();
                return rowSet;
            } catch (SQLException ex) {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } finally {
                    rs = null;
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } finally {
                    stmt = null;
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } finally {
                    conn = null;
                }
                throw new SQLException(ex);
            }
        }

        public static RowSet getRowSet(HttpServletRequest request, String SQL, Object[] parameters) throws SQLException, NamingException, Exception {
            Connection conn = getConnection(request);
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                stmt = conn.prepareStatement(SQL);
                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i].getClass().equals(BigDecimal.class)) {
                        stmt.setBigDecimal(i + 1, (BigDecimal) parameters[i]);
                    } else if (parameters[i].getClass().equals(Integer.class)) {
                        stmt.setInt(i + 1, ((Integer) (parameters[i])).intValue());
                    } else if (parameters[i].getClass().equals(String.class)) {
                        stmt.setString(i + 1, (String) parameters[i]);
                    } else if (parameters[i].getClass().equals(byte[].class)) {
                        stmt.setBytes(i + 1, (byte[]) parameters[i]);
                    } else if (parameters[i] instanceof InputStream) {
                        stmt.setBinaryStream(i + 1, (InputStream) parameters[i]);
                    } else {
                        stmt.setObject(i + 1, parameters[i]);
                    }
                }
                rs = stmt.executeQuery();
                RowSet rowSet = new RowSet(rs);
                rs.close();
                stmt.close();
                conn.close();
                return rowSet;
            } catch (SQLException ex) {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } finally {
                    rs = null;
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } finally {
                    stmt = null;
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } finally {
                    conn = null;
                }
                throw new SQLException(ex);
            }
        }

        public static boolean amISuperUser(HttpServletRequest request) throws SQLException, NamingException, Exception {
            RowSet rs = getRowSet(request, "SELECT rolsuper FROM pg_roles WHERE rolname::name = session_user");
            if (rs.next()) {
                return rs.getBoolean("rolsuper");
            } else {
                return false;
            }
        }

        public static String getMyUserName(HttpServletRequest request) {
            //if(getConnection(request) == null) return null;
            HttpSession session = request.getSession(true);
            return (String) session.getAttribute("Application.Session.userName");
        }

        public static String[] getMyRoles(HttpServletRequest request) throws SQLException, NamingException, Exception {
            ArrayList<String> roles = new ArrayList<String>();
            RowSet rs = getRowSet(request, ""
                    + "SELECT rolname as role "
                    + "FROM pg_roles "
                    + "WHERE rolname::name = session_user "
                    + ""
                    + "UNION "
                    + ""
                    + "SELECT r.rolname "
                    + "FROM pg_auth_members m "
                    + "JOIN pg_roles r ON(r.oid = m.roleid) "
                    + "JOIN pg_roles r2 ON(r2.oid = m.member) "
                    + "WHERE r2.rolname::name = session_user");
            while (rs.next()) {
                roles.add(rs.getString("role"));
            }
            String rolesArray[] = new String[roles.size()];
            rolesArray = roles.toArray(rolesArray);
            return rolesArray;
        }

        public static Date getDatabaseServerDate(HttpServletRequest request) throws SQLException, NamingException, Exception {
            RowSet rs = getRowSet(request, "SELECT now();");
            Date dt = rs.getTimestamp(1);
            return dt;
        }
    }

    public static Date getServerDate() {
        return new Date();
    }

    public static String getParameter(HttpServletRequest request, String parameterName, String defaultValue) {
        String parameter = defaultValue;
        if (request.getParameter(parameterName) != null) {
            return request.getParameter(parameterName);
        }
        return parameter;
    }

    public static String getSessionAttribute(HttpServletRequest request, String parameterName, String defaultValue, boolean reset) {
        //Captura a sessão do usuário
        HttpSession session = request.getSession(true);
        //Se não há um hashMap para o servlet do request, cria-o na sessão do usuário
        if (session.getAttribute(request.getServletPath() + "{ParametersMap}") == null) {
            session.setAttribute(request.getServletPath() + "{ParametersMap}", new HashMap<String, String>());
        }
        HashMap<String, String> parametersMap = (HashMap<String, String>) session.getAttribute(request.getServletPath() + "{ParametersMap}");

        if (reset) {
            parametersMap.put(parameterName, defaultValue);
        }

        if (parametersMap.containsKey(parameterName)) {
            //Se o hashMap contem o dado, retorna o dado persistido atualizando-o se caso o request tenha um novo valor
            if (request.getParameter(parameterName) != null) {
                parametersMap.put(parameterName, request.getParameter(parameterName));
            }
        } else {
            //Caso contrário armazena o valor padrão no hashMap e retorna-o
            if (request.getParameter(parameterName) != null) {
                parametersMap.put(parameterName, request.getParameter(parameterName));
            } else {
                parametersMap.put(parameterName, defaultValue);
            }
        }
        return parametersMap.get(parameterName);
    }

    public static String getServletContextAttribute(HttpServlet servlet, HttpServletRequest request, String parameterName, String defaultValue, boolean reset) {
        ServletContext context = servlet.getServletContext();
        //Se não há um hashMap para o servlet do request, cria-o na sessão do usuário
        if (context.getAttribute(request.getServletPath() + "{ParametersMap}") == null) {
            context.setAttribute(request.getServletPath() + "{ParametersMap}", new HashMap<String, String>());
        }
        HashMap<String, String> parametersMap = (HashMap<String, String>) context.getAttribute(request.getServletPath() + "{ParametersMap}");

        if (reset) {
            parametersMap.put(parameterName, defaultValue);
        }

        if (parametersMap.containsKey(parameterName)) {
            //Se o hashMap contem o dado, retorna o dado persistido atualizando-o se caso o request tenha um novo valor
            if (request.getParameter(parameterName) != null) {
                parametersMap.put(parameterName, request.getParameter(parameterName));
            }
        } else {
            //Caso contrário armazena o valor padrão no hashMap e retorna-o
            if (request.getParameter(parameterName) != null) {
                parametersMap.put(parameterName, request.getParameter(parameterName));
            } else {
                parametersMap.put(parameterName, defaultValue);
            }
        }
        return parametersMap.get(parameterName);
    }

    public static byte[] getByteArray(HttpServletRequest request, String SQL, int parameter) throws SQLException, NamingException, Exception {
        Connection conn = Session.getConnection(request);
        PreparedStatement stmt = null;
        ResultSet rs = null;
        byte[] byteArray = null;
        try {
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, parameter);
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    byteArray = rs.getBytes(1);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
            return byteArray;
        } catch (SQLException e) {
            try {
                if (rs != null) {
                    rs.close();
                }
            } finally {
                rs = null;
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } finally {
                stmt = null;
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } finally {
                conn = null;
            }
            throw new SQLException(e);
        }
    }

}
