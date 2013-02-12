package nl.xup.tableprinter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import nl.xup.tableprinter.ColumnHeader.Alignment;
import nl.xup.tableprinter.spi.Printer;

import org.junit.Test;

public class TablePrinterTest {

  // ------------------------------------------------------------------------
  // Test cases
  // ------------------------------------------------------------------------

  @Test
  public void constuctor() {
    TablePrinter table = new TablePrinter();

    assertThat( table, is( notNullValue() ) );
    assertThat( table.getColumnHeaders(), is( notNullValue() ) );
    assertThat( table.getColumnHeaders().size(), is( 0 ) );
    assertThat( table.getRows(), is( notNullValue() ) );
    assertThat( table.getRows().size(), is( 0 ) );
    assertThat( table.getPrinter(), is( notNullValue() ) );
  }

  @Test
  public void createColumnHeaderSingleArg() {
    TablePrinter table = new TablePrinter();
    
    ColumnHeader header = table.createColumnHeader( "a" );

    assertThat( header, is( notNullValue() ) );
    assertThat( header.getName(), is( "a" ) );
    assertThat( table.getColumnHeaders().size(), is( 1 ) );
    assertThat( table.getColumnHeaders().get(0), is( header ) );

    header = table.createColumnHeader( "b" );
    assertThat( header.getName(), is( "b" ) );
    assertThat( table.getColumnHeaders().size(), is( 2 ) );
    assertThat( table.getColumnHeaders().get(1), is( header ) );
  }

  @Test
  public void createColumnHeaderDoubleArg() {
    TablePrinter table = new TablePrinter();
    
    ColumnHeader header = table.createColumnHeader( "a", Alignment.LEFT );

    assertThat( header, is( notNullValue() ) );
    assertThat( header.getName(), is( "a" ) );
    assertThat( header.getAlign(), is( Alignment.LEFT ) );
    assertThat( table.getColumnHeaders().size(), is( 1 ) );
    assertThat( table.getColumnHeaders().get(0), is( header ) );

    header = table.createColumnHeader( "b", Alignment.RIGHT );
    assertThat( header.getName(), is( "b" ) );
    assertThat( header.getAlign(), is( Alignment.RIGHT ) );
    assertThat( table.getColumnHeaders().size(), is( 2 ) );
    assertThat( table.getColumnHeaders().get(1), is( header ) );
  }
  
  @Test
  public void createRow() {
    TablePrinter table = new TablePrinter();
    
    Row row = table.createRow();

    assertThat( row, is( notNullValue() ) );
    assertThat( table.getRows(), is( notNullValue() ) );
    assertThat( table.getRows().size(), is( 1 ) );
    assertThat( table.getRows().get(0), is( row ) );

    row = table.createRow();
    assertThat( table.getRows().size(), is( 2 ) );
    assertThat( table.getRows().get(1), is( row ) );
  }

  @Test
  public void setPrinter() {
    TablePrinter table = new TablePrinter();
    table.setPrinter( new Printer() {
      
      @Override
      public String print( TablePrinter table ) {
        return "0";
      }
    } );
    
    assertThat( table.getPrinter(), is( notNullValue() ) );
    assertThat( table.toString(), is( "0" ) );
  }  
}
