package it.iad.sigeba2.dto;
@Deprecated
public class DataNumeroDto {

    private String data;
    private Integer numero;

    public DataNumeroDto() {
    }

    public DataNumeroDto(String data, Integer numero) {
        this.data = data;
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

}
