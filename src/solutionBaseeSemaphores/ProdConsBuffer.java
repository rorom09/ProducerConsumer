package solutionBaseeSemaphores;
import java.util.concurrent.Semaphore;



public class ProdConsBuffer implements IProdConsBuffer
{
  private Message[] m_buffer;
  private Semaphore m_notFull;      /// < Producer condition
  private Semaphore m_notEmpty;     /// < Consumer condition
  // avoir deux mutex permet de
  private Semaphore m_produceMutex; /// < Critical section for put
  private Semaphore m_consumeMutex; /// < Critical section for get
  private int       m_in;           /// < Next case to put a message
  private int       m_out;          /// < Next case to get a message
  private int       m_msgTotalNum;

  public ProdConsBuffer( int size )
  {
    m_buffer = new Message[ size ];
    m_notFull = new Semaphore( size );
    m_notEmpty = new Semaphore( 0 );
    m_consumeMutex = new Semaphore( 1 );
    m_produceMutex = new Semaphore( 1 );
    m_in = 0;
    m_out = 0;
  }

  /// < Produce
  @Override
  public synchronized void put( Message m ) throws InterruptedException
  {
    m_notFull.acquire();

    m_produceMutex.acquire();
    m_buffer[ m_in ] = m;
    m_in = ( m_in + 1 ) % m_buffer.length;
    m_msgTotalNum++ ;
    m_produceMutex.release();

    m_notEmpty.release();
  }

  /// < Consume
  @Override
  public synchronized Message get() throws InterruptedException
  {
    m_notEmpty.acquire();

    m_consumeMutex.acquire();
    Message msg = m_buffer[ m_out ];
    m_out = ( m_out + 1 ) % m_buffer.length;
    m_consumeMutex.release();

    m_notFull.release();

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
