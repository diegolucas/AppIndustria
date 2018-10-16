package diegolucas.com.br.appindustria.model;

public class FlorestaModel {

    private Integer codigo;
    private Integer ano;
    private String estado;
    private Integer cortadas;
    private Integer volume;
    private Integer repostas;
    private Integer pago;

    public Integer getCodigo(){
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
