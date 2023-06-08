package game;

import java.io.Serializable;

public class Pair implements Serializable{
    public int first;
    public int second;
    public Pair(int a,int b)
    {
        first=a;
        second=b;
    }
    @Override
    public int hashCode()
    {
        return (first*1000000009+second)%2000000000;
    }
    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Pair))return false;
        Pair other_ = (Pair)other;
        return (other_.first == this.first && other_.second == this.second);

    }
}
