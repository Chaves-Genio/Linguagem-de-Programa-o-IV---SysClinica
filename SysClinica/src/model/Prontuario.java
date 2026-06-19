/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Prontuario {

    private int id;
    private int consultaId;
    private String diagnostico;
    private String medicamentos;
    private String observacoes;

    public Prontuario() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setConsultaId(int consultaId) {
        this.consultaId = consultaId;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public int getConsultaId() {
        return consultaId;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public String getObservacoes() {
        return observacoes;
    }
}
