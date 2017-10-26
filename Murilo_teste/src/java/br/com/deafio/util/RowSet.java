/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author murilo
 */
public class RowSet {
    /**
     * Classe que representa a estrutura do ResultSet lido e gravado no RowSet.
     *
     * Foi implementada para garantir compatibilidade com rotinas que urilizam o ResultSet
     */
    public class MetaData {

        private String[] columnLabels;

        private String[] columnTypeNames;

        public int getColumnCount() {
            return this.columnLabels.length;
        }

        public String getColumnName(int index) {
            return this.columnLabels[index - 1];
        }

        public String getColumnTypeName(int index) {
            return this.columnTypeNames[index - 1];
        }
    }
    private int index = -1;
    private MetaData metaData = new MetaData();

    /**
     * Método que retorna o metadados do RowSet, que espelha o metadados do ResultSet utilizado na criação do objeto
     * @return
     */
    public MetaData getMetaData() {
        return metaData;
    }
    private ArrayList<Object[]> lista = new ArrayList<Object[]>();

    /**
     * Implementa uma estrutura em cache do ResultSet, com todos os métodos e atributos necessários para sua utilização sem a necessidade de um Statment ou Connection aberto.
     * @param rs ResultSet que será lido e gravado em Cache.
     * @throws SQLException Exceções SQL serão repassadas ao método que criou a instância de RowSet.
     */
    public RowSet(ResultSet rs) throws SQLException {
        metaData.columnLabels = new String[rs.getMetaData().getColumnCount()];
        metaData.columnTypeNames = new String[rs.getMetaData().getColumnCount()];
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            metaData.columnLabels[i - 1] = rs.getMetaData().getColumnName(i);
            metaData.columnTypeNames[i - 1] = rs.getMetaData().getColumnTypeName(i);
        }
        index++;
        while (rs.next()) {
            Object[] values = new Object[rs.getMetaData().getColumnCount()];
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                if (rs.getMetaData().getColumnClassName(i).equals("java.sql.Timestamp")) {
                    values[i - 1] = rs.getTimestamp(i);
                } else {
                    values[i - 1] = rs.getObject(i);
                }
            }
            lista.add(values);
        }
        index = -1;
    }

    /**
     * Método que retorna a quantidade de registros em cache.
     * @return Quantidade de registros em cache no RowSet. Representa o tamanho do ArrayList de Object[].
     */
    public int size() {
        return lista.size();
    }

    /**
     * Método que retorna o índice de controle de varredura do RowSet.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @return Índice que representa a posição do registro durante navegação. Começa em 0.
     */
    public int getRow() {
        return index;
    }

    /**
     * Método que retorna os valores do registro em um arranjo unidimensional de Object.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param index Índice que representa a posição do registro. Começa em 0.
     * @return Arranjo unidimensional de objetos da classe genérica Object.
     */
    public Object[] getRowValues(int index) {
        return lista.get(index);
    }

    /**
     * Método que retorna o valor de uma coluna para um determinado registro.
     * @param rowIndex Índice que representa a posição do registro, começando em 0.
     * @param columnIndex Índice que representa a posição da coluna, começando em 1.
     * @return
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        return lista.get(rowIndex)[columnIndex - 1];
    }

    /**
     * Método que retorna o valor de uma coluna para um determinado registro.
     * @param rowIndex Índice que representa a posição do registro, começando em 0.
     * @param columnName Nome da coluna.
     * @return
     */
    public Object getValueAt(int rowIndex, String columnName) {
        return lista.get(rowIndex)[getColumnIndex(columnName) - 1];
    }

    /**
     * Método que permite definir um novo valor para uma coluna de um determinado registro.
     * @param rowIndex Índice que representa a posição do registro, começando em 0.
     * @param columnIndex Índice que representa a posição da coluna, começando em 1.
     * @param value Valor a ser armazenado no campo do registro.
     */
    public void setValueAt(int rowIndex, int columnIndex, Object value) {
        lista.get(rowIndex)[columnIndex - 1] = value;
    }

    /**
     * Método que permite definir um novo valor para uma coluna de um determinado registro.
     * @param rowIndex Índice que representa a posição do registro, começando em 0.
     * @param columnName Nome da coluna.
     * @param value Valor a ser armazenado no campo do registro.
     */
    public void setValueAt(int rowIndex, String columnName, Object value) {
        lista.get(rowIndex)[getColumnIndex(columnName) - 1] = value;
    }

    /**
     * Método que permite adicionar um registro inteiro no final da lista.
     * @param values Arranjo unidimensional de valores do registro. Os tipos e a ordem dos dados devem obedecer o metadados do RowSet.
     */
    public void addRow(Object[] values) {
        lista.add(values);
    }

    /**
     * Método que permite adicionar um registro inteiro no índice especificado.
     * @param index Índice que representa a posição do registro, começando em 0.
     * @param values Arranjo unidimensional de valores do registro. Os tipos e a ordem dos dados devem obedecer o metadados do RowSet.
     */
    public void addRow(int index, Object[] values) {
        lista.add(index, values);
    }

    /**
     * Método que permite definir a posição do registro na estrutura de controle de navegação.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param row Índice que representa a posição do registro, começando em 0.
     */
    public void absolute(int row) {
        this.index = row;
    }

    /**
     * Método que posiciona o cursor (índice de navegação dos registros) no primeiro registro.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @return True se há um registro nessa posição.
     */
    public boolean first() {
        index = 0;
        return size() > 0;
    }

    /**
     * Método que posiciona o cursor (índice de navegação dos registros) no registro anterior.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @return True se há um registro nessa posição.
     */
    public boolean previous() {
        if (index > 1) {
            index--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que posiciona o cursor (índice de navegação dos registros) no próximo registro.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @return True se há um registro nessa posição.
     */
    public boolean next() {
        if (index < size() - 1) {
            index++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que posiciona o cursor (índice de navegação dos registros) no último registro.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @return True se há um registro nessa posição.
     */
    public boolean last() {
        index = size();
        return size() > 0;
    }

    public boolean isFirst(){
        if (index != 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isLast(){
        if (index < size() - 1) {
            return false;
        } else {
            return true;
        }
    }

    public void beforeFirst(){
        index = -1;
    }
    
    /**
     * Método que retorna o ínice de uma coluna com nome especificado como parâmetro.
     * @param columnLabel Nome da coluna no RowSet.
     * @return Índice que representa a coluna. Começa em 0.
     */
    public int getColumnIndex(String columnLabel) {
        for (int i = 1; i <= this.getMetaData().getColumnCount(); i++) {
            if (columnLabel.toUpperCase().equals(this.getMetaData().getColumnName(i).toUpperCase())) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnIndex Índice que representa a posição da coluna, começando em 1.
     * @return Objeto no campo do registro. Pode ser null
     */
    public Object getObject(int columnIndex) {
        if (lista.get(index)[columnIndex - 1] == null) {
            return null;
        }
        return lista.get(index)[columnIndex - 1];
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnLabel Nome da coluna no metadados.
     * @return Objeto no campo do registro. Pode ser null
     */
    public Object getObject(String columnLabel) {
        if (lista.get(index)[getColumnIndex(columnLabel) - 1] == null) {
            return null;
        }
        return lista.get(index)[getColumnIndex(columnLabel) - 1];
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnIndex Índice que representa a posição da coluna, começando em 1.
     * @return Objeto no campo do registro. Pode ser null
     */
    public String getString(int columnIndex) {
        if (lista.get(index)[columnIndex - 1] == null) {
            return "";
        }
        return lista.get(index)[columnIndex - 1].toString().toString();
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnLabel Nome da coluna no metadados.
     * @return Objeto no campo do registro. Pode ser null
     */
    public String getString(String columnLabel) {
        if (lista.get(index)[getColumnIndex(columnLabel) - 1] == null) {
            return "";
        }
        if ((lista.get(index)[getColumnIndex(columnLabel) - 1]) instanceof java.sql.Clob) {
            try {
                int size = (int) ((java.sql.Clob) (lista.get(index)[getColumnIndex(columnLabel) - 1])).length();
                if (size > 0) {
                    return ((java.sql.Clob) (lista.get(index)[getColumnIndex(columnLabel) - 1])).getSubString(1, size);
                } else {
                    return "";
                }
            } catch (SQLException ex) {
                return ex.getMessage();
            }
        } else {
            return lista.get(index)[getColumnIndex(columnLabel) - 1].toString();
        }
    }
    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnIndex Índice que representa a posição da coluna, começando em 1.
     * @return Objeto no campo do registro. Pode ser null
     */
    public boolean getBoolean(int columnIndex) {
        Object o = lista.get(index)[columnIndex - 1];
        if (o == null) {
            return false;
        }else if(o instanceof Boolean){
            return ((Boolean)(o)).booleanValue();
        }else{
            return false;
        }
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnLabel Nome da coluna no metadados.
     * @return Objeto no campo do registro. Pode ser null
     */
    public boolean getBoolean(String columnLabel) {
        Object o = lista.get(index)[getColumnIndex(columnLabel) - 1];
        if (o == null) {
            return false;
        }else if(o instanceof Boolean){
            return ((Boolean)(o)).booleanValue();
        }else{
            return false;
        }
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnIndex Índice que representa a posição da coluna, começando em 1.
     * @return Objeto no campo do registro. Pode ser null
     */
    public int getInt(int columnIndex) {
        if (lista.get(index)[columnIndex - 1] == null) {
            return 0;
        }
        if ((lista.get(index)[columnIndex - 1]).getClass().equals(BigDecimal.class)) {
            return ((BigDecimal) (lista.get(index)[columnIndex - 1])).intValue();
        }
        return ((Integer) (lista.get(index)[columnIndex - 1])).intValue();
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnLabel Nome da coluna no metadados.
     * @return Objeto no campo do registro. Pode ser null
     */
    public int getInt(String columnLabel) {
        if (lista.get(index)[getColumnIndex(columnLabel) - 1] == null) {
            return 0;
        }
        if ((lista.get(index)[getColumnIndex(columnLabel) - 1]).getClass().equals(BigDecimal.class)) {
            return ((BigDecimal) (lista.get(index)[getColumnIndex(columnLabel) - 1])).intValue();
        }
        return ((Integer) (lista.get(index)[getColumnIndex(columnLabel) - 1])).intValue();
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnIndex Índice que representa a posição da coluna, começando em 1.
     * @return Objeto no campo do registro. Pode ser null
     */
    public long getLong(int columnIndex) {
        if (lista.get(index)[columnIndex - 1] == null) {
            return 0;
        }
        if ((lista.get(index)[columnIndex - 1]).getClass().equals(BigDecimal.class)) {
            return ((BigDecimal) (lista.get(index)[columnIndex - 1])).longValue();
        }
        return ((Long) (lista.get(index)[columnIndex - 1])).longValue();
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnLabel Nome da coluna no metadados.
     * @return Objeto no campo do registro. Pode ser null
     */
    public long getLong(String columnLabel) {
        if (lista.get(index)[getColumnIndex(columnLabel) - 1] == null) {
            return 0;
        }
        if ((lista.get(index)[getColumnIndex(columnLabel) - 1]).getClass().equals(BigDecimal.class)) {
            return ((BigDecimal) (lista.get(index)[getColumnIndex(columnLabel) - 1])).longValue();
        }
        return ((Long) (lista.get(index)[getColumnIndex(columnLabel) - 1])).longValue();
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnIndex Índice que representa a posição da coluna, começando em 1.
     * @return Objeto no campo do registro. Pode ser null
     */
    public double getDouble(int columnIndex) {
        if (lista.get(index)[columnIndex - 1] == null) {
            return 0.0;
        }
        return ((BigDecimal) (lista.get(index)[columnIndex - 1])).doubleValue();
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnLabel Nome da coluna no metadados.
     * @return Objeto no campo do registro. Pode ser null
     */
    public double getDouble(String columnLabel) {
        if (lista.get(index)[getColumnIndex(columnLabel) - 1] == null) {
            return 0.0;
        }
        return ((BigDecimal) (lista.get(index)[getColumnIndex(columnLabel) - 1])).doubleValue();
    }
//    /**
//     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
//     *
//     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
//     * @param columnIndex Índice que representa a posição da coluna, começando em 1.
//     * @return Objeto no campo do registro. Pode ser null
//     */
//    public Date getDate(int columnIndex){
//        if(lista.get(index)[columnIndex-1] == null) return null;
//        return ((Date)(lista.get(index)[columnIndex-1]));
//    }
//    /**
//     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
//     *
//     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
//     * @param columnLabel Nome da coluna no metadados.
//     * @return Objeto no campo do registro. Pode ser null
//     */
//    public Date getDate(String columnLabel){
//        if(lista.get(index)[getColumnIndex(columnLabel)-1] == null) return null;
//        return ((Date)(lista.get(index)[getColumnIndex(columnLabel)-1]));
//    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnIndex Índice que representa a posição da coluna, começando em 1.
     * @return Objeto no campo do registro. Pode ser null
     */
    public Timestamp getTimestamp(int columnIndex) {
        if (lista.get(index)[columnIndex - 1] == null) {
            return null;
        }
        return ((Timestamp) (lista.get(index)[columnIndex - 1]));
    }

    /**
     * Método que retorna o Objeto da coluna especificada como parâmetro referente ao registro referenciado pelo cursor (ínice) interno.
     *
     * Esse controle foi implementado para garantir compatibilidade com rotinas escritas utilizando o ResultSet.
     * @param columnLabel Nome da coluna no metadados.
     * @return Objeto no campo do registro. Pode ser null
     */
    public Timestamp getTimestamp(String columnLabel) {
        if (lista.get(index)[getColumnIndex(columnLabel) - 1] == null) {
            return null;
        }
        return ((Timestamp) (lista.get(index)[getColumnIndex(columnLabel) - 1]));
    }

    
}
