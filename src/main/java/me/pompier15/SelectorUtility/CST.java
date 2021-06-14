package me.pompier15.SelectorUtility;

public enum CST {

    METADATA_X_1 ("SeUX1"),
    METADATA_X_2 ("SeUX2"),
    METADATA_Y_1 ("SeUY1"),
    METADATA_Y_2 ("SeUY2"),
    METADATA_Z_1 ("SeUZ1"),
    METADATA_Z_2 ("SeUZ2"),
    METADATA_WORLD_1 ("SeUW1"),
    METADATA_WORLD_2 ("SeUW2");

    private final String _value;

    CST(String value)
    {
        _value = value;
    }

    public String Value()
    {
        return _value;
    }
}
