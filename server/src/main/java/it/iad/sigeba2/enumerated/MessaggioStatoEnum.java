package it.iad.sigeba2.enumerated;

public enum MessaggioStatoEnum {
    
    OK("Operazione eseguita con successo","",GravitaStatoEnum.OK),
    UTENTE_NON_AUTORIZZATO("L'utente non è autorizzato a compiere l'operazione","Accedere con un utente diverso o richiedere all'amministratore i privilegi necessari",GravitaStatoEnum.CRITICA), 
    CLIENTE_NON_TROVATO("Il cliente non è presente del database", "verificare l'esatteza dei criteri di ricerca", GravitaStatoEnum.CRITICA), 
    CONTO_CORRENTE_NON_TROVATO("Il conto corrente non è presente del database", "verificare l'esatteza dei criteri di ricerca", GravitaStatoEnum.CRITICA);
    
    private final String messaggio;
    private final String azione;
    private final GravitaStatoEnum gravita; 

    private MessaggioStatoEnum(String messaggio, String azione, GravitaStatoEnum gravita) {
        this.messaggio = messaggio;
        this.azione = azione;
        this.gravita = gravita;
    }
    
}
