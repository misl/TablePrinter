package nl.xup.tableprinter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

public class RowTest {

  // ------------------------------------------------------------------------
  // Test cases
  // ------------------------------------------------------------------------

  @Test
  public void constuctor() {
    Row row = new Row();

    assertThat( row, is( notNullValue() ) );
    assertThat( row.getCells(), is( notNullValue() ) );
    assertThat( row.getCells().size(), is( 0 ) );
  }

  @Test
  public void addCell() {
    Row row = new Row();
    
    String cell = row.addCell( "a" );

    assertThat( cell, is( notNullValue() ) );
    assertThat( row.getCells(), is( notNullValue() ) );
    assertThat( row.getCells().size(), is( 1 ) );
    assertThat( row.getCells().get(0), is( cell ) );

    cell = row.addCell( null );
    assertThat( cell, is( nullValue() ) );
    assertThat( row.getCells().size(), is( 2 ) );
    assertThat( row.getCells().get(1), is( cell ) );
  }

}
