package model;

/**
 * Represents the type of field in the labyrinth.
 */
public enum FieldType {

    /**
     * Starting field of a labyrinth.
     */
    START(),

    /**
     * Destination field of a labyrinth.
     */
    DESTINATION(),

    /**
     * A wall field.
     */
    WALL(),

    /**
     * A "normal" walkable field.
     */
    WALKABLE();

}
