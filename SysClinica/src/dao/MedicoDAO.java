/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Medico;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;

public class MedicoDAO {

    public boolean salvar(Medico m){

        String sql =
        "INSERT INTO medicos(nome,crm,especialidade,telefone,email) "
        + "VALUES(?,?,?,?,?)";

        try(Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(sql)){

            pst.setString(1,m.getNome());
            pst.setString(2,m.getCrm());
            pst.setString(3,m.getEspecialidade());
            pst.setString(4,m.getTelefone());
            pst.setString(5,m.getEmail());

            return pst.executeUpdate() > 0;

        }catch(Exception e){

            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Medico> listar(){

        ArrayList<Medico> lista = new ArrayList<>();

        String sql = "SELECT * FROM medicos";

        try(Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()){

            while(rs.next()){

                Medico m = new Medico();

                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setCrm(rs.getString("crm"));
                m.setEspecialidade(rs.getString("especialidade"));
                m.setTelefone(rs.getString("telefone"));
                m.setEmail(rs.getString("email"));

                lista.add(m);
            }

        }catch(Exception e){

            System.out.println(e.getMessage());
        }

        return lista;
    }

    public boolean atualizar(Medico m){

        String sql =
        "UPDATE medicos SET nome=?,crm=?,especialidade=?,telefone=?,email=? WHERE id=?";

        try(Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(sql)){

            pst.setString(1,m.getNome());
            pst.setString(2,m.getCrm());
            pst.setString(3,m.getEspecialidade());
            pst.setString(4,m.getTelefone());
            pst.setString(5,m.getEmail());
            pst.setInt(6,m.getId());

            return pst.executeUpdate() > 0;

        }catch(Exception e){

            return false;
        }
    }

    public boolean eliminar(int id){

        String sql = "DELETE FROM medicos WHERE id=?";

        try(Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(sql)){

            pst.setInt(1,id);

            return pst.executeUpdate() > 0;

        }catch(Exception e){

            return false;
        }
    }

    //
    public Medico buscarPorNome(String nome) {

        String sql = "SELECT * FROM medicos WHERE nome = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nome);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Medico m = new Medico();

                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setCrm(rs.getString("crm"));
                m.setEspecialidade(rs.getString("especialidade"));
                m.setTelefone(rs.getString("telefone"));
                m.setEmail(rs.getString("email"));
                //m.setEstado(rs.getBoolean("estado"));

                return m;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Medico buscarPorId(int id) {

    String sql = "SELECT * FROM medicos WHERE id=?";

    try (Connection con = Conexao.conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            Medico m = new Medico();

            m.setId(rs.getInt("id"));
            m.setNome(rs.getString("nome"));
            m.setCrm(rs.getString("crm"));
            m.setEspecialidade(rs.getString("especialidade"));
            m.setTelefone(rs.getString("telefone"));
            m.setEmail(rs.getString("email"));
            //m.setEstado(rs.getBoolean("estado"));

            return m;
        }

    } catch (Exception e) {
        System.out.println("Erro buscar médico: " + e.getMessage());
    }

    return null;
}
}
