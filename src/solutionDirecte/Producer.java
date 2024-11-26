package solutionDirecte;

public class Producer extends Thread
{
  private ProdConsBuffer m_buffer;
  private int            m_time;
  private String         m_name;
  private int			 m_min;
  private int			 m_max;

  public Producer( String name, ProdConsBuffer buffer, int time, int min, int max)
  {
    m_name = name;
    m_buffer = buffer;
    m_time = time;
    m_min = min;
    m_max = max;
    super.start();
  }

  @Override
  public void run()
  {
    try
    {
      Thread.sleep( m_time );
      Message message = Message.getRandom(m_min, m_max);
      m_buffer.put( message );
      System.out.println( m_name + "(put) " + message );
    }
    catch ( InterruptedException e )
    {
      e.printStackTrace();
    }
  }
}
