package observer;

public interface Subject {
    public void registerObserver(Observador o);
 //   public void removeObserver(Observer o);
    public void notifyObservers(); 
}
