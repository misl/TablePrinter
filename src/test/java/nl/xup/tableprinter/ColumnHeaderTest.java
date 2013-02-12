package nl.xup.tableprinter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import nl.xup.tableprinter.ColumnHeader.Alignment;

import org.junit.Test;

public class ColumnHeaderTest {

  // ------------------------------------------------------------------------
  // Test cases
  // ------------------------------------------------------------------------

  @Test
  public void constuctorSingleArg() {
    ColumnHeader header = new ColumnHeader( "header" );

    assertThat( header, is( notNullValue() ) );
    assertThat( header.getName(), is( "header" ) );
    assertThat( header.getAlign(), is( Alignment.LEFT ) );
  }

  @Test
  public void constuctorDoubleArg() {
    ColumnHeader header = new ColumnHeader( "head", Alignment.CENTER );

    assertThat( header, is( notNullValue() ) );
    assertThat( header.getName(), is( "head" ) );
    assertThat( header.getAlign(), is( Alignment.CENTER ) );
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void constructorNullName() {
    new ColumnHeader( null, Alignment.CENTER );
  }

  @Test(expected=IllegalArgumentException.class)
  public void constructorNullAlign() {
    new ColumnHeader( "head", null );
  }

  @Test
  public void setNameValid() {
    ColumnHeader header = new ColumnHeader( "head", Alignment.CENTER );
    header.setName( "other" );
    
    assertThat( header.getName(), is( "other" ) );
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void setNameWithNull() {
    ColumnHeader header = new ColumnHeader( "head", Alignment.CENTER );
    
    header.setName( null );
  }

  @Test
  public void setAlignValid() {
    ColumnHeader header = new ColumnHeader( "head", Alignment.CENTER );
    header.setAlign( Alignment.RIGHT );
    
    assertThat( header.getAlign(), is( Alignment.RIGHT ) );
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void setAlignWithNull() {
    ColumnHeader header = new ColumnHeader( "head", Alignment.CENTER );
    
    header.setAlign( null );
  }

  // Getters have been tested implicitly.
}
