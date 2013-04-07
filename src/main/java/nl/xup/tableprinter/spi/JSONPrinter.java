package nl.xup.tableprinter.spi;

import nl.xup.tableprinter.Row;
import nl.xup.tableprinter.TablePrinter;

/**
 * Prints tables in json format.
 * 
 * @author Minto van der Sluis
 */
public class JSONPrinter implements Printer {

  // -------------------------------------------------------------------------
  // Object Attributes
  // -------------------------------------------------------------------------

  private String rootElementName = "Table";

  // -------------------------------------------------------------------------
  // Constructors
  // -------------------------------------------------------------------------

  public JSONPrinter() {
  }

  public JSONPrinter( String rootElementName ) {
    this.rootElementName = rootElementName;
  }

  // -------------------------------------------------------------------------
  // Implementing Printer
  // -------------------------------------------------------------------------

  @Override
  public String print( TablePrinter table ) {
    StringBuffer printedTable = new StringBuffer();

    printedTable.append( "{\n" );
    printedTable.append( "  \"" + getRootElementName() + "\": [\n" );

    if( table != null && table.getColumnHeaders().size() > 0 ) {
      boolean firstRow = true;
      for( Row row : table.getRows() ) {
        if( !firstRow ) {
          printedTable.append( ",\n" );
        }
        printedTable.append( "    {\n" );

        int index = 0;
        for( String cell : row.getCells() ) {
          if( index != 0 ) {
            printedTable.append( ",\n" );
          }
          String columnName = table.getColumnHeaders().get( index ).getName();
          printedTable.append( "      \"" + columnName + "\": " );
          printedTable.append( "\"" + cell + "\"" );
          index++;
        }
        if( index > 0 ) {
          printedTable.append( "\n" );
        }
        printedTable.append( "    }" );
        firstRow = false;
      }
      if( !firstRow ) {
        printedTable.append( "\n" );
      }
    }

    printedTable.append( "  ]\n" );
    printedTable.append( "}" );

    return printedTable.toString();
  }

  // -------------------------------------------------------------------------
  // Getter / Setters
  // -------------------------------------------------------------------------
  
  public String getRootElementName() {
    return rootElementName;
  }
  
  public void setRootElementName( String rootElementName ) {
    this.rootElementName = rootElementName;
  }
}
