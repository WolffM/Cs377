Class Monitor{
    Public Monitor{
      int count = 0;
      int last = 0;
      item[] buffer;
      int size = buffer.size();

public void Append(item){
  lock.Acquire();
  if(count == buffer.N){
    empty.Wait(lock);
  }
  buffer[last] = item;
  last = (last+1)%
  count++;
  full.Signal();
  lock.Release();
}
public void Remove(item){
  lock.Acquire();
  if(count == 0)
    full.Wait(lock);
  item = buffer[(last-count)%N];
  count--;
  empty.Signal();
  lock.Release();
}
