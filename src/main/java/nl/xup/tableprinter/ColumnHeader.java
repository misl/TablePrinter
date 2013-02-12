package nl.xup.tableprinter;

/**
 * Table Printer column header configuration.
 * 
 * @author Minto van der Sluis
 */
public class ColumnHeader {

  // -------------------------------------------------------------------------
  // Enumerations
  // -------------------------------------------------------------------------
  
  public enum Alignment {
    LEFT, CENTER, RIGHT
  }
  
  // -------------------------------------------------------------------------
  // Class attributes
  // -------------------------------------------------------------------------
  
  private String name;
  private Alignment align;
  private int width;
  
  // -------------------------------------------------------------------------
  // Constructors
  // -------------------------------------------------------------------------

  ColumnHeader( String name ) {
    this( name, Alignment.LEFT );
  }
  
  ColumnHeader( String name, Alignment align ) {
    setName( name );
    setAlign( align );
  }

  // -------------------------------------------------------------------------
  // Getters / Setters
  // -------------------------------------------------------------------------

  public String getName() {
    return name;
  }
  
  public void setName( String name ) {
    if ( name == null ) {
      throw new IllegalArgumentException( "Name can't be null!" );
    }
    this.name = name;
  }
  
  public Alignment getAlign() {
    return align;
  }
  
  public void setAlign( Alignment align ) {
    if ( align == null ) {
      throw new IllegalArgumentException( "Alignment can't be null!" );
    }
    this.align = align;
  }
  
  public int getWidth() {
    return width;
  }
  
  public void setWidth( int width ) {
    this.width = width;
  }
}