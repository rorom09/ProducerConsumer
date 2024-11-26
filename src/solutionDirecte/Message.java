package solutionDirecte;

import java.util.UUID;

public class Message
{
  private String m_text;

  public Message( String text )
  {
    m_text = text;
  }

  @Override
  public String toString()
  {
    return m_text;
  }

  public static Message getRandom()
  {
    String uuid = UUID.randomUUID().toString();
    return new Message( uuid.replace( "-", "" ) );
  }
}
