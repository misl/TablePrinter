package nl.xup.tableprinter.spi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import nl.xup.tableprinter.ColumnHeader;
import nl.xup.tableprinter.ColumnHeader.Alignment;
import nl.xup.tableprinter.Row;
import nl.xup.tableprinter.TablePrinter;

public class AsciiPrinter implements Printer {

  // -------------------------------------------------------------------------
  // Constants
  // -------------------------------------------------------------------------

  private static final String NULL_VALUE = "null";
  private static final String NEWLINE = "\n";

  private static final char TABLE_INTERSECTION = '+';
  private static final char TABLE_LINE = '-';
  private static final char TABLE_COLUMN = '|';
  private static final char TABLE_PADDING = ' ';

  // -------------------------------------------------------------------------
  // Implementing Printer
  // -------------------------------------------------------------------------

  @Override
  public String print( TablePrinter table ) {
    StringBuffer printedTable = new StringBuffer();

    if( table != null && table.getColumnHeaders().size() > 0 ) {
      List<Integer> columnWidths = determineColumnWidths( table );
      String lineSeparator = lineSeparator( columnWidths );

      printedTable.append( lineSeparator );
      printedTable.append( headerLine( columnWidths, table.getColumnHeaders() ) );
      printedTable.append( lineSeparator );
      if( table.getRows().size() > 0 ) {
        for( Row row : table.getRows() ) {
          printedTable.append( rowLine( table.getColumnHeaders(), columnWidths, row ) );
        }
      } else {
        printedTable.append( TABLE_COLUMN );
        printedTable.append( prepareCell( "empty", lineSeparator.length() - 5, Alignment.LEFT ) );
        printedTable.append( TABLE_COLUMN ).append( NEWLINE );
      }
      printedTable.append( lineSeparator );
    } else {
      printedTable.append( "empty" );
    }

    return printedTable.toString();
  }

  // -------------------------------------------------------------------------
  // Private methods
  // -------------------------------------------------------------------------

  private List<Integer> determineColumnWidths( TablePrinter table ) {
    List<Integer> columnWidths = new ArrayList<>( table.getColumnHeaders().size() );

    int index = 0;
    for( ColumnHeader columnHeader : table.getColumnHeaders() ) {
      columnWidths.add( columnHeader.getName().length() );
      index++;
    }

    for( Row row : table.getRows() ) {
      index = 0;
      for( String cell : row.getCells() ) {
        if( cell == null ) {
          cell = NULL_VALUE;
        }
        int maxColumnWidth = Math.max( cell.length(), columnWidths.get( index ) );
        columnWidths.set( index, maxColumnWidth );
        index++;
      }
    }

    return columnWidths;
  }

  private String lineSeparator( List<Integer> columnWidths ) {
    StringBuilder builder = new StringBuilder();

    // Start with a column intersection.
    builder.append( "+" );
    for( Integer width : columnWidths ) {
      builder.append( StringUtils.leftPad( "", width + 2, TABLE_LINE ) );
      // Add another column intersection.
      builder.append( TABLE_INTERSECTION );
    }

    return builder.append( NEWLINE ).toString();
  }

  private String headerLine( List<Integer> columnWidths, List<ColumnHeader> headers ) {
    StringBuilder builder = new StringBuilder();

    builder.append( TABLE_COLUMN );
    int index = 0;
    for( ColumnHeader columnHeader : headers ) {
      builder.append( prepareCell( columnHeader.getName(), columnWidths.get( index ),
          columnHeader.getAlign() ) );
      builder.append( TABLE_COLUMN );
      index++;
    }

    return builder.append( NEWLINE ).toString();
  }

  private String rowLine( List<ColumnHeader> headers, List<Integer> columnWidths, Row row ) {
    StringBuilder builder = new StringBuilder();

    builder.append( TABLE_COLUMN );
    int index = 0;
    for( String value : row.getCells() ) {
      if( value == null ) {
        value = NULL_VALUE;
      }
      Alignment align = headers.get( index ).getAlign();
      builder.append( prepareCell( value, columnWidths.get( index ), align ) );
      builder.append( TABLE_COLUMN );
      index++;
    }

    return builder.append( NEWLINE ).toString();
  }

  private String prepareCell( String value, int width, Alignment align ) {
    StringBuilder cellContent = new StringBuilder();
    cellContent.append( TABLE_PADDING );
    if( Alignment.LEFT.equals( align ) ) {
      cellContent.append( StringUtils.rightPad( value, width, TABLE_PADDING ) );
    } else if( Alignment.CENTER.equals( align ) ) {
      cellContent.append( StringUtils.center( value, width, TABLE_PADDING ) );
    } else {
      cellContent.append( StringUtils.leftPad( value, width, TABLE_PADDING ) );
    }
    cellContent.append( TABLE_PADDING );
    return cellContent.toString();
  }
}
