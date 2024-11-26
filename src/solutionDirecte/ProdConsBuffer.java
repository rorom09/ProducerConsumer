package solutionDirecte;

public class ProdConsBuffer implements IProdConsBuffer
{
  private Message[] m_buffer;
  private int       m_in;         /// < Next case to put a message
  private int       m_out;        /// < Next case to get a message
  private int       m_msgTotalNum;

  public ProdConsBuffer( int size )
  {
    m_buffer = new Message[ size ];
    m_in = 0;
    m_out = 0;
  }

  /// < Produce
  @Override
  public synchronized void put( Message m ) throws InterruptedException
  {
    m_buffer[ m_in ] = m;
    m_in = ( m_in + 1 ) % m_buffer.length;
    m_msgTotalNum++ ;
  }

  /// < Consume
  @Override
  public synchronized Message get() throws InterruptedException
  {
    Message msg = m_buffer[ m_out ];
    m_out = ( m_out + 1 ) % m_buffer.length;

    return msg;
  }

  @Override
  public synchronized int nmsg()
  {
    int num = m_out - m_in;
    return ( num > 0 ) ? num : -num;
  }

  @Override
  public synchronized int totmsg()
  {
    return m_msgTotalNum;
  }
}
