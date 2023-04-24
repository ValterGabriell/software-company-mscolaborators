package io.github.valtergabriell.mscolaborators.application.domain.enumerations;

public enum TypePerson {
    PHYSIC_PERSON("CPF"), JURY_PERSON("CNPJ");

    private final String id;

    TypePerson(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
