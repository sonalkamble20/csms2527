//Name: Sonal Shivaji Kamble
package Work;

public class DoubleArraySeqDemonstration
{
    public static void main(String[ ] args)
    {
        DoubleArraySeq s = new DoubleArraySeq(1);
        for (double element = 1.0; element < 11.0; element += 1.0)
            s.addAfter(element);
        System.out.println("sequence s");
        s.print();

        DoubleArraySeq t = s.clone();
        t.start();
        for (int i = 0; i < 4; i++)
            t.advance();
        t.addAfter(11.0); t.addAfter(12.0);
        System.out.println("sequence t after adding 11.0 and 12.0 to s");
        t.print();

        DoubleArraySeq u = t.clone();
        u.start();
        for (int i = 0; i < 5; i++)
            u.advance();
        u.removeCurrent(); u.removeCurrent();
        System.out.println("sequence u after removing 11.0 and 12.0 from t");
        u.print();

        DoubleArraySeq v = s.clone();
        v.start();
        for (int i = 0; i < 4; i++)
            v.advance();
        v.addBefore(11.0); v.addBefore(12.0);
        System.out.println("sequence v after adding 11.0 and 12.0 to s");
        v.print();
        v.trimToSize();
        System.out.println("sequence v after trim");
        v.print();
        v.addAll(s);
        System.out.println("sequence v = v + s");
        v.print();

        DoubleArraySeq w;
        w = DoubleArraySeq.concatenation(s,t);
        System.out.println("sequence w = s + t");
        w.print();

        DoubleArraySeq x = new DoubleArraySeq();
        System.out.println("sequence x is empty");
        x.print();
        System.out.println("Trying to get anything from x causes an exception\n");
        System.out.printf("%5.2f", x.getCurrent());
    }
}
class DoubleArraySeq implements Cloneable
{
    private double[ ] data;
    private int manyItems;
    private int currentIndex;

    public DoubleArraySeq( ) throws OutOfMemoryError
    {
        try {
            data = new double[10];
        } catch (OutOfMemoryError e) {
            throw new OutOfMemoryError("Insufficient memory for creating new object ob Work.DoubleArraySeq");
        }
    }

    public DoubleArraySeq(int initialCapacity) throws IllegalArgumentException, OutOfMemoryError
    {
        if(initialCapacity < 0)
            throw new IllegalArgumentException("initialCapacity cannot be negative");
        try {
            data = new double[initialCapacity];
        } catch (OutOfMemoryError e) {
            throw new OutOfMemoryError("Insufficient memory for creating new object ob Work.DoubleArraySeq");
        }
    }
    public void addAfter(double element) throws OutOfMemoryError, ArithmeticException
    {
        if(getCapacity() < (manyItems + 1))
            ensureCapacity(getCapacity() * 2);
        if(isCurrent())
        {
            if(getCurrent() < getCapacity())
            {
                if(currentIndex < (manyItems-1))
                {
                    int i = 0;
                    for(i = manyItems; i > currentIndex; i--)
                    {
                        if(manyItems <= getCapacity())
                            data[i+1] = data[i];
                        else
                            ensureCapacity(getCapacity() * 2);
                    }
                    data[currentIndex+1] = element;

                    manyItems++;
                    currentIndex++;
                }
                else
                    for(int i = manyItems; i < getCapacity(); i++)
                    {
                        if(manyItems <= getCapacity())
                        {
                            data[manyItems++] = element;
                            currentIndex = i;
                            break;
                        }
                    }
            }
        }
        else
            for(int i = 0; i < manyItems; i++)
            {
                data[i] = element;
                currentIndex = i;
                manyItems++;
            }
    }
    public void addBefore(double element)
    {
        if(getCapacity() < (manyItems + 1))
            ensureCapacity(getCapacity() * 2);
        if(isCurrent())
        {
            if(getCurrent() < getCapacity())
            {
                if(currentIndex < (manyItems-1))
                {
                    int i = 0;
                    for(i = manyItems; i > currentIndex - 1; i--)
                    {
                        if(manyItems <= getCapacity())
                            data[i+1] = data[i];
                        else
                            ensureCapacity(getCapacity() * 2);
                    }
                    data[currentIndex] = element;

                    manyItems++;
                }
                else
                    for(int i = manyItems; i < getCapacity(); i++)
                    {
                        if(manyItems <= getCapacity())
                        {
                            data[manyItems++] = element;
                            currentIndex = i;
                            break;
                        }
                    }
            }
        }
        else
            for(int i = 0; i < manyItems; i++)
            {
                data[i] = element;
                currentIndex = i;
                manyItems++;
            }
    }

    public void addAll(DoubleArraySeq addend)
    {
        if(addend != null)
        {
            try
            {
                if(addend.getCapacity() < (addend.manyItems + manyItems + 1))
                {
                    addend.trimToSize();
                    ensureCapacity(addend.manyItems + manyItems);
                    System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems);
                    manyItems += addend.manyItems;
                }

            } catch (OutOfMemoryError e) {
                throw new OutOfMemoryError("Insufficient memory to increase capacity");
            }
        }
        else throw new NullPointerException("addend cannot be null");
    }

    public void advance( )
    {
        if(isCurrent())
            if(currentIndex == (manyItems - 1))
                currentIndex = -1;
            else
            {
                currentIndex++;
            }
        else
            throw new IllegalStateException("there is no current element");
    }
    @Override
    public DoubleArraySeq clone()
    {
        DoubleArraySeq answer;

        try
        {
            answer = (DoubleArraySeq) super.clone( );
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException
                    ("This class does not implement Cloneable");
        }
        catch (OutOfMemoryError e)
        {
            throw new OutOfMemoryError("Indicates insufficient memory for creating the clone");
        }
        answer.data = data.clone( );
        return answer;
    }

    public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2)
    {
        if(s1 != null & s2 != null)
        {
            try
            {
                DoubleArraySeq temp = new DoubleArraySeq();
                temp.ensureCapacity(s1.manyItems + s2.manyItems);
                System.arraycopy(s1.data, 0, temp.data, 0, s1.manyItems);
                System.arraycopy(s2.data, 0, temp.data, s1.manyItems, s2.manyItems);

                temp.manyItems = s1.manyItems + s2.manyItems;
                temp.currentIndex = -1;
                return temp;
            }
            catch (OutOfMemoryError e)
            {
                throw new OutOfMemoryError("Insufficient memory to increase capacity");
            }
        }
        else throw new OutOfMemoryError("Insufficient memory to increase capacity");
    }

    public void ensureCapacity(int minimumCapacity)
    {
        if(getCapacity() < minimumCapacity)
        {
            try
            {
                double[] temp = new double[minimumCapacity];
                System.arraycopy(data, 0, temp, 0, manyItems);
                data=temp;
            } catch (OutOfMemoryError e) {
                throw new OutOfMemoryError("Insufficient memory to increase capacity");
            }
        }
    }

    public int getCapacity( )
    {
        return data.length;
    }

    public double getCurrent( )
    {
        if(!isCurrent())
            throw new IllegalStateException("There is no current element.");
        else
            return currentIndex;
    }

    public boolean isCurrent( )
    {
        if(currentIndex != -1)
            return true;
        else
            return false;
    }

    public void removeCurrent( )
    {
        if(isCurrent())
        {
            for(int i = currentIndex; i < manyItems; i++)
            {
                data[i] = data[i+1];
            }
            manyItems--;
        }
    }


    public int size( )
    {
        return manyItems;
    }
    public void start( )
    {
        if(manyItems > 0)
            currentIndex = 0;
        else
            currentIndex = -1;
    }

    public void trimToSize( )
    {
        double[ ] trimmedArray;

        if (data.length != manyItems)
        {
            trimmedArray = new double[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }


    public void print()
    {
        System.out.println("\tcapacity = " + data.length);
        System.out.println("\tlength = " + manyItems);

        try
        {
            int current = (int) getCurrent();
            if (current == 0)
                throw new RuntimeException("");
            System.out.println("\tcurrent element = " + data[current]);
        }
        catch (Exception e)
        {
            throw new IllegalStateException("There is no current element.");
        }

        System.out.print("\telements: ");

        for(int i = 0; i < manyItems; i++)
            System.out.print(data[i] + "    ");

        System.out.println("\n");
    }
}




