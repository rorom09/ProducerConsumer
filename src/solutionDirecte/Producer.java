package solutionDirecte;

import java.util.Random;

public class Producer extends Thread
{
  private ProdConsBuffer m_buffer;
  private int            m_time;
  private String         m_name;
  private int            m_nbMessages;

  public Producer( String name, ProdConsBuffer buffer, int time, int min, int max )
  {
    m_name = name;
    m_buffer = buffer;
    m_time = time;
    m_nbMessages = new Random().nextInt( min, max );
    super.start();
  }

  @Override
  public void run()
  {
    for ( int i = 0; i < m_nbMessages; i++ )
    {
      try
      {
        Thread.sleep( m_time );
        Message message = Message.getRandom();
        m_buffer.put( message );
        System.out.println( m_name + "(put) " + message );
      }
      catch ( InterruptedException e )
      {
        e.printStackTrace();
      }
    }
  }
}
