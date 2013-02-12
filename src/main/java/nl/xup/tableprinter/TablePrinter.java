package nl.xup.tableprinter;

import java.util.ArrayList;
import java.util.List;

import nl.xup.tableprinter.ColumnHeader.Alignment;
import nl.xup.tableprinter.spi.AsciiPrinter;
import nl.xup.tableprinter.spi.Printer;


public class TablePrinter {

  // -------------------------------------------------------------------------
  // Class attributes
  // -------------------------------------------------------------------------

  private List<ColumnHeader> columnHeaders = new ArrayList<>();
  private List<Row> rows = new ArrayList<>();
  private Printer printer = new AsciiPrinter();

  // -------------------------------------------------------------------------
  // Constructors
  // -------------------------------------------------------------------------
  
  public TablePrinter() {
  }
  
  // -------------------------------------------------------------------------
  // Public Interface
  // -------------------------------------------------------------------------

  /**
   * Gives the list of column headers for the table.
   * @return
   */
  public List<ColumnHeader> getColumnHeaders() {
    return columnHeaders;
  }
  
  /**
   * Creates a new ColumnHeader and adds it to the table.
   * @param name the name to be printed in the column header.
   * @return
   */
  public ColumnHeader createColumnHeader( String name ) {
    ColumnHeader header = new ColumnHeader( name );
    columnHeaders.add( header );
    return header;
  }

  /**
   * Creates a new ColumnHeader and adds it to the table.
   * @param name the name to be printed in the column header.
   * @param align the alignment to be used for the column.
   * @return
   */
  public ColumnHeader createColumnHeader( String name, Alignment align ) {
    ColumnHeader header = new ColumnHeader( name, align );
    columnHeaders.add( header );
    return header;
  }

  /**
   * Gives the list of all rows (except for the header) making up the table.
   * @return
   */
  public List<Row> getRows() {
    return rows;
  }
  
  /**
   * Creates a new row and adds it to the table.
   * @return
   */
  public Row createRow() {
    Row row = new Row();
    rows.add( row );
    return row;
  }
  
  // -------------------------------------------------------------------------
  // Getters / Setters
  // -------------------------------------------------------------------------
  
  public Printer getPrinter() {
    return printer;
  }
  
  public void setPrinter( Printer printer ) {
    this.printer = printer;
  }
  
  // -------------------------------------------------------------------------
  // Object overrides
  // -------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return printer.print( this );
  }
}