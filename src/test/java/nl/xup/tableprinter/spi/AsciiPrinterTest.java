package nl.xup.tableprinter.spi;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import nl.xup.tableprinter.ColumnHeader.Alignment;
import nl.xup.tableprinter.Row;
import nl.xup.tableprinter.TablePrinter;

import org.junit.Test;

public class AsciiPrinterTest {

  // ------------------------------------------------------------------------
  // Test cases
  // ------------------------------------------------------------------------

  @Test
  public void nullTablePrinter() {
    String printedTable = new AsciiPrinter().print( null );
    
    assertThat( printedTable, is( "empty" ) );
  }

  @Test
  public void emptyTablePrinter() {
    String printedTable = new AsciiPrinter().print( new TablePrinter() );
    
    assertThat( printedTable, is( "empty" ) );
  }

  @Test
  public void headerOnlyTablePrinter() {
    TablePrinter table = new TablePrinter();
    table.createColumnHeader( "a", Alignment.LEFT );
    table.createColumnHeader( "b", Alignment.CENTER );
    table.createColumnHeader( "c", Alignment.RIGHT );
    String printedTable = new AsciiPrinter().print( table );
    
    StringBuilder expectedTable = new StringBuilder();
    expectedTable.append( "+---+---+---+\n" );
    expectedTable.append( "| a | b | c |\n" );
    expectedTable.append( "+---+---+---+\n" );
    expectedTable.append( "| empty     |\n" );
    expectedTable.append( "+---+---+---+\n" );
    assertThat( printedTable, is( expectedTable.toString() ) );
  }

  @Test
  public void twoRowTablePrinter() {
    TablePrinter table = new TablePrinter();
    table.createColumnHeader( "a", Alignment.LEFT );
    table.createColumnHeader( "b", Alignment.CENTER );
    table.createColumnHeader( "c", Alignment.RIGHT );
    Row row = table.createRow();
    row.addCell( "aap" );
    row.addCell( "noot" );
    row.addCell( "mies" );
    row = table.createRow();
    row.addCell( "1" );
    row.addCell( "2" );
    row.addCell( "3" );
    String printedTable = new AsciiPrinter().print( table );
    
    StringBuilder expectedTable = new StringBuilder();
    expectedTable.append( "+-----+------+------+\n" );
    expectedTable.append( "| a   |  b   |    c |\n" );
    expectedTable.append( "+-----+------+------+\n" );
    expectedTable.append( "| aap | noot | mies |\n" );
    expectedTable.append( "| 1   |  2   |    3 |\n" );
    expectedTable.append( "+-----+------+------+\n" );
    assertThat( printedTable, is( expectedTable.toString() ) );
  }
}
