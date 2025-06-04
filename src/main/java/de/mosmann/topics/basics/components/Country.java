package de.mosmann.topics.basics.components;

public enum Country {
    GERMANY("Germany"),
    AUSTRIA("Austria"),
    SWITZERLAND("Switzerland"),
    UNITED_STATES("United States"),
    UNITED_KINGDOM("United Kingdom"),
    FRANCE("France"),
    ITALY("Italy"),
    SPAIN("Spain"),
    NETHERLANDS("Netherlands"),
    BELGIUM("Belgium"),
    SWEDEN("Sweden"),
    NORWAY("Norway"),
    DENMARK("Denmark"),
    FINLAND("Finland"),
    POLAND("Poland"),
    CZECH_REPUBLIC("Czech Republic"),
    HUNGARY("Hungary"),
    PORTUGAL("Portugal"),
    GREECE("Greece"),
    TURKEY("Turkey");

    private final String displayName;

    Country(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
