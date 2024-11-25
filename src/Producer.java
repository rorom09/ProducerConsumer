
public class Producer extends Thread
{
  private ProdConsBuffer m_buffer;
  private int            m_time;
  private String         m_name;

  public Producer( String name, ProdConsBuffer buffer, int time )
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
