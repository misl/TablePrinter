package nl.xup.tableprinter.spi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import nl.xup.tableprinter.ColumnHeader;
import nl.xup.tableprinter.Row;
import nl.xup.tableprinter.TablePrinter;
import nl.xup.tableprinter.ColumnHeader.Alignment;

/**
 * Prints tables in xhtml.
 * 
 * @author Minto van der Sluis
 */
public class XHtmlPrinter implements Printer {

  // -------------------------------------------------------------------------
  // Class Attributes
  // -------------------------------------------------------------------------
  
  private static Map<Alignment, String> alignmentMap = new HashMap<Alignment, String>();
  
  static {
    alignmentMap.put( Alignment.LEFT, "left" );
    alignmentMap.put( Alignment.CENTER, "center" );
    alignmentMap.put( Alignment.RIGHT, "right" );
  }
  
  // -------------------------------------------------------------------------
  // Implementing Printer
  // -------------------------------------------------------------------------
  
  @Override
  public String print( TablePrinter table ) {
    StringBuffer printedTable = new StringBuffer();

    printedTable.append( "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" );
    printedTable.append( "<html  xmlns=\"http://www.w3.org/1999/xhtml\">\n" );
    printedTable.append( " <body>\n" );
    printedTable.append( "  <table>\n" );
    
    if( table != null && table.getColumnHeaders().size() > 0 ) {
      printedTable.append( headerLine( table.getColumnHeaders() ) );
      
      for( Row row : table.getRows() ) {
        printedTable.append( rowLine( table.getColumnHeaders(), row ) );
      }
    } else {
      printedTable.append( "(empty)" );
    }

    printedTable.append( "  </table>\n" );
    printedTable.append( " </body>\n" );
    printedTable.append( "</html>\n" );

    return printedTable.toString();
  }

  // -------------------------------------------------------------------------
  // Private methods
  // -------------------------------------------------------------------------

  private String headerLine( List<ColumnHeader> headers ) {
    StringBuilder builder = new StringBuilder();

    builder.append( "   <tr>\n" );
    for( ColumnHeader columnHeader : headers ) {
      builder.append( "    <th style=\"" + getAligmentStyle( columnHeader ) + "\">" );
      builder.append( StringEscapeUtils.escapeHtml( columnHeader.getName() ) );
      builder.append( "</th>\n" );
    }

    builder.append( "   <tr>\n" );
    return builder.toString();
  }

  private String rowLine( List<ColumnHeader> headers, Row row ) {
    StringBuilder builder = new StringBuilder();

    builder.append( "   <tr>\n" );
    int index = 0;
    for( String value : row.getCells() ) {
      if( value == null ) {
        value = "";
      }
      ColumnHeader header = headers.get( index );
      builder.append( "    <th style=\"" + getAligmentStyle( header ) + "\">" );
      builder.append( StringEscapeUtils.escapeHtml( value ) );
      builder.append( "</th>\n" );
      index++;
    }

    builder.append( "   <tr>\n" );
    return builder.toString();
  }
  
  private String getAligmentStyle( ColumnHeader columnHeader ) {
    return "text-align: " + alignmentMap.get( columnHeader.getAlign() ) + ";";
  }
}
