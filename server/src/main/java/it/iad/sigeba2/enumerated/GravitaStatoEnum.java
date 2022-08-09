package it.iad.sigeba2.enumerated;

public enum GravitaStatoEnum {
    OK("OK"), INFO("INFO"), BASSA("BASSA"), MEDIA("MEDIA"), CRITICA("CRITICA");

    private final String valore;

    private GravitaStatoEnum(String valore) {
        this.valore = valore;
    }

    public String getValore() {
        return valore;
    }

}
