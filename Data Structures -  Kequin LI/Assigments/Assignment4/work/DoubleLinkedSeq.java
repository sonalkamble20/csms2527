
public class DoubleLinkedSeq implements Cloneable
{
   private DoubleNode head;
   private DoubleNode tail;
   private DoubleNode currentElement;

   public DoubleLinkedSeq( )
   {
      head = null;
      tail = null;
      currentElement = null;
   }

   public void addAfter(double element)
   {
      try
      {
         if(currentElement != null)
         {
            currentElement.setLink(new DoubleNode(element, currentElement.getLink()));
            currentElement = currentElement.getLink();
            if(currentElement.getLink() == null)
               tail = currentElement;
         }
         else
            currentElement = tail = head = new DoubleNode(element, head);
      }
      catch(OutOfMemoryError e)
      {
         throw new OutOfMemoryError("insufficient memory for a new node");
      }
   }

   public void addBefore(double element)
   {
      try
      {
         if(isCurrent() && currentElement == head)
           head = currentElement = new DoubleNode(element, currentElement);
         else
         {
            DoubleNode copyCurrentElement = new DoubleNode(element, currentElement);
            DoubleNode cursor = head;
            while(cursor != null && cursor.getLink() != currentElement)
                  cursor = cursor.getLink();

            cursor.setLink(copyCurrentElement);
            currentElement = copyCurrentElement;
         }
      }
      catch(OutOfMemoryError e)
      {
         throw new OutOfMemoryError("insufficient memory for a new node");
      }
   }

   public void addAll(DoubleLinkedSeq addend)
   {
      try
      {
         if(addend != null)
         {
            tail.setLink(addend.head);
            tail = addend.tail;
         }
         else throw new NullPointerException("addend is null");
      }catch(OutOfMemoryError e)
      {
         throw new OutOfMemoryError("insufficient memory to increase the size of this sequence");
      }
   }

   public void advance( )
   {
      try
      {
         if(isCurrent())
            if(currentElement.getLink() != null)
               currentElement = currentElement.getLink();
            else
               currentElement = null;
      }
      catch(IllegalStateException e)
      {
         throw new IllegalStateException("There is no current element");
      }
   }

   public Object clone( )
   {
      DoubleLinkedSeq copySeq = new DoubleLinkedSeq();

      copySeq.currentElement = copySeq.tail = copySeq.head = new DoubleNode(head.getData(), copySeq.head);

      for(DoubleNode cursor = head.getLink(); cursor!= null; cursor = cursor.getLink())
      {
         copySeq.tail = new DoubleNode(cursor.getData(), cursor.getLink());
         copySeq.currentElement.setLink(copySeq.tail);
         copySeq.currentElement = copySeq.tail;
      }
      copySeq.currentElement = DoubleNode.listSearch(copySeq.head, currentElement.getData());

      return copySeq;
   }

   public static DoubleLinkedSeq catenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2)
   {
      try
      {
         if(s1 != null && s2 != null)
         {
            DoubleLinkedSeq catenated = (DoubleLinkedSeq) s1.clone();
            catenated.addAll(s2);
            catenated.currentElement = null;

            return catenated;
         }
         else throw new NullPointerException("s1 or s2 is null");
      }
      catch(OutOfMemoryError e)
      {
         throw new OutOfMemoryError("insufficient memory for the new  sequence");
      }
   }

   public double getCurrent( )
   {
      if(isCurrent())
         return currentElement.getData();
      else
         throw new IllegalStateException("there is no current element");
   }

   public boolean isCurrent( )
   {
     if(currentElement != null)
        return true;
     else
      return false;
   }

   public void removeCurrent( )
   {
      DoubleNode copyCurrentElement = currentElement;
      try
      {
         if(isCurrent())
         {
            for(DoubleNode cursor = head; cursor != null && cursor!= currentElement; cursor = cursor.getLink())
               copyCurrentElement = cursor;
            copyCurrentElement.removeNodeAfter();
         }
         currentElement = copyCurrentElement.getLink();
      }
      catch (IllegalStateException e)
      {
         throw new IllegalStateException("there is no current element");
      }
   }

   public int size( )
   {
      DoubleNode cursor;
      int answer;

      answer = 0;
      for (cursor = head; cursor != null; cursor = cursor.getLink())
         answer++;

      return answer;
   }

   public void start( )
   {
      currentElement = head;
   }

   public void print()
   {
      System.out.println("   length = " + this.size());
      try
      {
         System.out.println("   current element = " + getCurrent());
      }catch (RuntimeException e)
      {
         System.out.println("   there is no current element");
      }
      System.out.print("   elements: ");
      for(DoubleNode cursor = head; cursor!= null; cursor = cursor.getLink())
         System.out.print(cursor.getData() + "  ");
      System.out.println();
      System.out.println();

   }
}

