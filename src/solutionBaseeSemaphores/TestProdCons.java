package solutionBaseeSemaphores;
import java.io.IOException;
import java.util.Properties;

public class TestProdCons
{
  public static void main( String[] args )
  {
    System.out.println( "[TEST]" );

    Properties properties = new Properties();
    try
    {
      properties.loadFromXML( TestProdCons.class.getClassLoader().getResourceAsStream( "options.xml" ) );
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }

    int producerNum = Integer.parseInt( properties.getProperty( "nProd" ) );
    int consumerNum = Integer.parseInt( properties.getProperty( "nCons" ) );
    int bufferSize  = Integer.parseInt( properties.getProperty( "bufSz" ) );
    int produceTime = Integer.parseInt( properties.getProperty( "prodTime" ) );
    int consumeTime = Integer.parseInt( properties.getProperty( "consTime" ) );
    int producerMin = Integer.parseInt( properties.getProperty( "minProd" ) );
    int producerMax = Integer.parseInt( properties.getProperty( "maxProd" ) );

    ProdConsBuffer buffer   = new ProdConsBuffer( bufferSize );
    Producer[]     producer = new Producer[ producerNum ];
    Consumer[]     consumer = new Consumer[ consumerNum ];

    for ( int i = 0; i < producerNum; i++ )
    {
      producer[ i ] = new Producer( "[P" + i + "]", buffer, produceTime, producerMin, producerMax);
    }

    for ( int i = 0; i < consumerNum; i++ )
    {
      consumer[ i ] = new Consumer( "[C" + i + "]", buffer, consumeTime );
    }

  }
}
