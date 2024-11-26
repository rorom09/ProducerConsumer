package solutionDirecte;

public class Consumer extends Thread
{
  private ProdConsBuffer m_buffer;
  private int            m_time;
  private String         m_name;

  public Consumer( String name, ProdConsBuffer buffer, int time )
  {
    m_name = name;
    m_buffer = buffer;
    m_time = time;
    super.start();
  }

  @Override
  public void run()
  {
    try
    {
      Thread.sleep( m_time );
      Message message = m_buffer.get();
      System.out.println( m_name + "(get) " + message );
    }
    catch ( InterruptedException e )
    {
      e.printStackTrace();
    }
  }
}