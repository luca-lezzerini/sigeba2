package it.iad.sigeba2.dto;

public class ProvaControllerDto {

    private String data;
    private Integer numero;

    public ProvaControllerDto() {
    }

    public ProvaControllerDto(String data, Integer numero) {
        this.data = data;
        this.numero = numero;

    }

    public ProvaControllerDto(String s, int n, int risultato) {
    }

    public String getData() {
        return data;
    }

    public void SetData(String data) {
        this.data = data;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
