package it.uniroma3.siw.model;

public enum UserRole {
    USER("Utente"),
    ADMIN("Amministratore");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAuthority() {
        return "ROLE_" + this.name();
    }

    @Override
    public String toString() {
        return displayName;
    }
}