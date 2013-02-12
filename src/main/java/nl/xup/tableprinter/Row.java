package nl.xup.tableprinter;

import java.util.ArrayList;
import java.util.List;

/**
 * Table Printer row.
 * 
 * @author Minto van der Sluis
 */
public class Row {
  
  // -------------------------------------------------------------------------
  // Class Attributes
  // -------------------------------------------------------------------------

  private List<String> cells = new ArrayList<>();
  
  // -------------------------------------------------------------------------
  // Constructors
  // -------------------------------------------------------------------------

  Row() {
  }

  // -------------------------------------------------------------------------
  // Getters / Setters
  // -------------------------------------------------------------------------
  
  /**
   * Gives the list of cells in the row.
   * @return
   */
  public List<String> getCells() {
    return cells;
  }
  
  /**
   * Adds a new cell to the row.
   * @param value the value for the new cell.
   * @return
   */
  public String addCell( String value ) {
    cells.add( value );
    return value;
  }
}