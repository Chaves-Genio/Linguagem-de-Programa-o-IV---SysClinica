/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import util.Conexao;

public class testeConexao {

    public static void main(String[] args) {

        Connection con =
                Conexao.conectar();

        if(con != null){

            System.out.println(
                    "Ligação realizada com sucesso!"
            );

        }else{

            System.out.println(
                    "Falha na ligação!"
            );
        }
    }
}