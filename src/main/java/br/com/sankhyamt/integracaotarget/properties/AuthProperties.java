package br.com.sankhyamt.integracaotarget.properties;

import br.com.sankhyamt.integracaotarget.model.database.ConnectionSQLServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe que armazena as propriepades da integração.
 * @since v1.0
 * @version 1.1
 */
public class AuthProperties {

    static private String usuario = "";
    static private String senha = "";

    public void getAuthProperties() throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        Connection connection = ConnectionSQLServer.connection();

        String sql = "SELECT USUARIOTARGET, SENHATARGET FROM TMSCONF";

        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()){

                setUsuario(rs.getString("USUARIOTARGET"));
                setSenha(rs.getString("SENHATARGET"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
