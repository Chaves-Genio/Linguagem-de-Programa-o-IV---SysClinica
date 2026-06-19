/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Paciente;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // =========================
    // INSERIR PACIENTE
    // =========================
    public boolean salvar(Paciente paciente) {

        String sql = "INSERT INTO pacientes "
                + "(nome, bi, nascimento, telefone, endereco, historico, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, TRUE)";

        try (
                Connection con = Conexao.conectar();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, paciente.getNome());
            pst.setString(2, paciente.getBi());

            pst.setDate(
                    3,
                    new java.sql.Date(
                            paciente.getNascimento().getTime()
                    )
            );

            pst.setString(4, paciente.getTelefone());
            pst.setString(5, paciente.getEndereco());
            pst.setString(6, paciente.getHistorico());

            return pst.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println("Erro ao salvar paciente: "
                    + e.getMessage());

            return false;
        }
    }

    // =========================
    // ATUALIZAR PACIENTE
    // =========================
    public boolean atualizar(Paciente paciente) {

        String sql = "UPDATE pacientes SET "
                + "nome=?, "
                + "bi=?, "
                + "nascimento=?, "
                + "telefone=?, "
                + "endereco=?, "
                + "historico=? "
                + "WHERE id=?";

        try (
                Connection con = Conexao.conectar();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, paciente.getNome());
            pst.setString(2, paciente.getBi());

            pst.setDate(
                    3,
                    new java.sql.Date(
                            paciente.getNascimento().getTime()
                    )
            );

            pst.setString(4, paciente.getTelefone());
            pst.setString(5, paciente.getEndereco());
            pst.setString(6, paciente.getHistorico());

            pst.setInt(7, paciente.getId());

            return pst.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println("Erro ao atualizar paciente: "
                    + e.getMessage());

            return false;
        }
    }

    // =========================
    // ELIMINAR PACIENTE
    // (LÓGICO)
    // =========================
    public boolean eliminar(int id) {

        String sql =
                "UPDATE pacientes "
                + "SET estado = FALSE "
                + "WHERE id = ?";

        try (
                Connection con = Conexao.conectar();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setInt(1, id);

            return pst.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println("Erro ao eliminar paciente: "
                    + e.getMessage());

            return false;
        }
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    public Paciente buscarPorId(int id) {

        String sql =
                "SELECT * FROM pacientes "
                + "WHERE id = ?";

        Paciente paciente = null;

        try (
                Connection con = Conexao.conectar();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                paciente = new Paciente();

                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setBi(rs.getString("bi"));
                paciente.setNascimento(rs.getDate("nascimento"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setHistorico(rs.getString("historico"));
            }

        } catch (Exception e) {

            System.out.println("Erro ao buscar paciente: "
                    + e.getMessage());
        }

        return paciente;
    }

    // =========================
    // LISTAR TODOS
    // =========================
    public List<Paciente> listar() {

        List<Paciente> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM pacientes "
                + "WHERE estado = TRUE "
                + "ORDER BY nome";

        try (
                Connection con = Conexao.conectar();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()
        ) {

            while (rs.next()) {

                Paciente paciente =
                        new Paciente();

                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setBi(rs.getString("bi"));
                paciente.setNascimento(rs.getDate("nascimento"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setHistorico(rs.getString("historico"));

                lista.add(paciente);
            }

        } catch (Exception e) {

            System.out.println("Erro ao listar pacientes: "
                    + e.getMessage());
        }

        return lista;
    }

    // =========================
    // PESQUISAR POR NOME
    // =========================
    public List<Paciente> pesquisar(String nome) {

        List<Paciente> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM pacientes "
                + "WHERE nome LIKE ? "
                + "AND estado = TRUE "
                + "ORDER BY nome";

        try (
                Connection con = Conexao.conectar();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    "%" + nome + "%"
            );

            ResultSet rs =
                    pst.executeQuery();

            while (rs.next()) {

                Paciente paciente =
                        new Paciente();

                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setBi(rs.getString("cpf"));
                paciente.setNascimento(rs.getDate("nascimento"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setHistorico(rs.getString("historico"));

                lista.add(paciente);
            }

        } catch (Exception e) {

            System.out.println("Erro ao pesquisar paciente: "
                    + e.getMessage());
        }

        return lista;
    }

    // =========================
    // VERIFICAR CPF
    // =========================
    public boolean biExiste(String bi) {

        String sql =
                "SELECT id FROM pacientes "
                + "WHERE cpf = ?";

        try (
                Connection con = Conexao.conectar();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, bi);

            ResultSet rs =
                    pst.executeQuery();

            return rs.next();

        } catch (Exception e) {

            System.out.println("Erro ao verificar CPF: "
                    + e.getMessage());

            return false;
        }
    }
//
    public Paciente buscarPorNome(String nome) {

    String sql = "SELECT * FROM pacientes WHERE nome = ?";

    try (Connection con = Conexao.conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, nome);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            Paciente p = new Paciente();

            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setBi(rs.getString("bi"));
            p.setNascimento(rs.getDate("nascimento"));
            p.setTelefone(rs.getString("telefone"));
            p.setEndereco(rs.getString("endereco"));
            p.setHistorico(rs.getString("historico"));
           // p.setEstado(rs.getBoolean("estado"));

            return p;
        }

    } catch (Exception e) {
        System.out.println("Erro buscarPaciente: " + e.getMessage());
    }

    return null;
}

}