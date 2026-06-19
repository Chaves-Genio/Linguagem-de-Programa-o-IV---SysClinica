/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Consulta;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;

public class ConsultaDAO {

    public boolean salvar(Consulta c) {

        String sql =
        "INSERT INTO consultas(paciente_id, medico_id, data_consulta, hora_consulta, status) "
        + "VALUES(?,?,?,?,?)";

        try(Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, c.getPacienteId());
            pst.setInt(2, c.getMedicoId());
            pst.setDate(3, c.getDataConsulta());
            pst.setTime(4, c.getHoraConsulta());
            pst.setString(5, c.getStatus());

            return pst.executeUpdate() > 0;

        } catch(Exception e) {

            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Consulta> listar() {

        ArrayList<Consulta> lista = new ArrayList<>();

        String sql = "SELECT * FROM consultas";

        try(Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()) {

            while(rs.next()) {

                Consulta c = new Consulta();

                c.setId(rs.getInt("id"));
                c.setPacienteId(rs.getInt("paciente_id"));
                c.setMedicoId(rs.getInt("medico_id"));
                c.setDataConsulta(rs.getDate("data_consulta"));
                c.setHoraConsulta(rs.getTime("hora_consulta"));
                c.setStatus(rs.getString("status"));

                lista.add(c);
            }

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }

        return lista;
    }

    public boolean atualizar(Consulta c) {

        String sql =
        "UPDATE consultas SET "
        + "paciente_id=?, medico_id=?, data_consulta=?, hora_consulta=?, status=? "
        + "WHERE id=?";

        try(Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, c.getPacienteId());
            pst.setInt(2, c.getMedicoId());
            pst.setDate(3, c.getDataConsulta());
            pst.setTime(4, c.getHoraConsulta());
            pst.setString(5, c.getStatus());
            pst.setInt(6, c.getId());

            return pst.executeUpdate() > 0;

        } catch(Exception e) {

            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM consultas WHERE id=?";

        try(Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            return pst.executeUpdate() > 0;

        } catch(Exception e) {

            System.out.println(e.getMessage());
            return false;
        }
    }

    public Consulta buscarPorId(int id) {

        String sql = "SELECT * FROM consultas WHERE id=?";

        try(Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {

                Consulta c = new Consulta();

                c.setId(rs.getInt("id"));
                c.setPacienteId(rs.getInt("paciente_id"));
                c.setMedicoId(rs.getInt("medico_id"));
                c.setDataConsulta(rs.getDate("data_consulta"));
                c.setHoraConsulta(rs.getTime("hora_consulta"));
                c.setStatus(rs.getString("status"));

                return c;
            }

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }

        return null;
    }
    
    public ArrayList<Consulta> pesquisar(String texto) {

        ArrayList<Consulta> lista = new ArrayList<>();

        String sql =
            "SELECT c.* FROM consultas c " +
            "JOIN pacientes p ON c.paciente_id = p.id " +
            "JOIN medicos m ON c.medico_id = m.id " +
            "WHERE p.nome LIKE ? " +
            "OR m.nome LIKE ? " +
            "OR c.data_consulta LIKE ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            String valor = "%" + texto + "%";

            pst.setString(1, valor);
            pst.setString(2, valor);
            pst.setString(3, valor);

            ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Consulta c = new Consulta();

            c.setId(rs.getInt("id"));
            c.setPacienteId(rs.getInt("paciente_id"));
            c.setMedicoId(rs.getInt("medico_id"));
            c.setDataConsulta(rs.getDate("data_consulta"));
            c.setHoraConsulta(rs.getTime("hora_consulta"));
            c.setStatus(rs.getString("status"));

            lista.add(c);
        }

    } catch (Exception e) {
        System.out.println("Erro pesquisa: " + e.getMessage());
    }

    return lista;
}
}