/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package dao;

import model.Prontuario;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;

public class ProntuarioDAO {

    // SALVAR
    public boolean salvar(Prontuario p) {

        String sql =
        "INSERT INTO prontuarios(consulta_id, diagnostico, medicamentos, observacoes) " +
        "VALUES(?,?,?,?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, p.getConsultaId());
            pst.setString(2, p.getDiagnostico());
            pst.setString(3, p.getMedicamentos());
            pst.setString(4, p.getObservacoes());

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro salvar prontuario: " + e.getMessage());
            return false;
        }
    }

    // LISTAR
    public ArrayList<Prontuario> listar() {

        ArrayList<Prontuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM prontuarios";

        try (Connection con = Conexao.conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Prontuario p = new Prontuario();

                p.setId(rs.getInt("id"));
                p.setConsultaId(rs.getInt("consulta_id"));
                p.setDiagnostico(rs.getString("diagnostico"));
                p.setMedicamentos(rs.getString("medicamentos"));
                p.setObservacoes(rs.getString("observacoes"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro listar prontuarios: " + e.getMessage());
        }

        return lista;
    }

    // ATUALIZAR
    public boolean atualizar(Prontuario p) {

        String sql =
        "UPDATE prontuarios SET consulta_id=?, diagnostico=?, medicamentos=?, observacoes=? " +
        "WHERE id=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, p.getConsultaId());
            pst.setString(2, p.getDiagnostico());
            pst.setString(3, p.getMedicamentos());
            pst.setString(4, p.getObservacoes());
            pst.setInt(5, p.getId());

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro atualizar prontuario: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int id) {

        String sql = "DELETE FROM prontuarios WHERE id=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro eliminar prontuario: " + e.getMessage());
            return false;
        }
    }

    // BUSCAR POR ID
    public Prontuario buscarPorId(int id) {

        String sql = "SELECT * FROM prontuarios WHERE id=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Prontuario p = new Prontuario();

                p.setId(rs.getInt("id"));
                p.setConsultaId(rs.getInt("consulta_id"));
                p.setDiagnostico(rs.getString("diagnostico"));
                p.setMedicamentos(rs.getString("medicamentos"));
                p.setObservacoes(rs.getString("observacoes"));

                return p;
            }

        } catch (Exception e) {
            System.out.println("Erro buscar prontuario: " + e.getMessage());
        }

        return null;
    }
}